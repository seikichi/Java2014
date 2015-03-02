package ch21.ex21_02;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

public class DataHandlerTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    private File file, otherFile;

    @Before
    public void setup() throws Exception {
        file = folder.newFile("file.txt");
        PrintWriter writer = new PrintWriter(new FileWriter(file, true));
        writer.println("Hello, world!");
        writer.close();

        otherFile = folder.newFile("otherFile.txt");
        PrintWriter otherWriter = new PrintWriter(new FileWriter(otherFile, true));
        otherWriter.println("こんにちは世界");
        otherWriter.close();
    }

    @Test
    public void readFileがファイル内容を読み取って返すこと() throws Exception {
        byte[] expected = "Hello, world!\n".getBytes("UTF-8");
        byte[] actual = new DataHandler().readFile(file);

        assertThat(actual, is(expected));
    }

    @Test
    public void 前回の結果の参照が残っている場合にreadFileがキャッシュされた値を返すこと() throws Exception {
        DataHandler handler = new DataHandler();
        byte[] first = handler.readFile(file);
        byte[] second = handler.readFile(file);

        assertThat(first, is(sameInstance(second)));
    }

    @Test
    public void 複数のファイル内容をキャッシュできること() throws Exception {
        DataHandler handler = new DataHandler();

        byte[] first = handler.readFile(file);
        byte[] otherFirst = handler.readFile(otherFile);

        byte[] second = handler.readFile(file);
        byte[] otherSecond = handler.readFile(otherFile);

        assertThat(first, is(sameInstance(second)));
        assertThat(otherFirst, is(sameInstance(otherSecond)));
    }
}
package ch21.ex21_02;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DataHandlerTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    private File file;

    @Before
    public void setup() throws Exception {
        file = folder.newFile("file.txt");
        PrintWriter writer = new PrintWriter(new FileWriter(file, true));
        writer.println("Hello, world!");
        writer.close();
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

        int firstHashCode = Arrays.hashCode(first);
        int secondHashCode = Arrays.hashCode(second);
        assertThat(firstHashCode, is(secondHashCode));
    }
}
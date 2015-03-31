package ch23.ex23_03;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CommandExecutorTest {
    private ByteArrayOutputStream outputStream;

    @Before
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void tearDown() {
        System.setOut(null);
    }

    @Test
    public void execWithLineNumの出力に行番号が適切に表示されていること() throws Exception {
        CommandExecutor.exec(new String[]{"echo", String.format("hello%n" + "world")}, "keyword");
        assertThat(outputStream.toString(), is(String.format("0001 hello%n0002 world%n")));
    }

    @Test
    public void 特定の文字列が出現した場合コマンドを終了すること() throws Exception {
        CommandExecutor.exec(new String[]{"echo", String.format("hello%n" + "world")}, "hello");
        assertThat(outputStream.toString(), is(""));
    }
}
package ch23.ex23_02;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
        CommandExecutor.execWithLineNum(new String[]{"echo", String.format("hello%n" + "world")});
        assertThat(outputStream.toString(), is(String.format("0001 hello%n0002 world%n")));
    }
}
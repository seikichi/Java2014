package ch23.ex23_01;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UserProgExecutorTest {
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
    public void echo_Helloを実行するとHelloが出力されること() throws Exception {
        UserProgExecutor.userProg("echo Hello").waitFor();
        Thread.sleep(100);
        assertThat(outputStream.toString(), is(String.format("Hello%n")));
    }
}
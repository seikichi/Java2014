package ch24.ex24_01;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;

import static java.util.Locale.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class GlobalHelloTest {
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
    public void ja_JPの場合() throws Exception {
        setDefault(new Locale("ja", "JP"));
        GlobalHello.main(new String[]{});

        assertThat(outputStream.toString(), is(String.format("こんにちは%n")));
    }

    @Test
    public void enの場合() throws Exception {
        setDefault(new Locale("en"));
        GlobalHello.main(new String[]{});

        assertThat(outputStream.toString(), is(String.format("Hello%n")));
    }

    @Test
    public void en_AUの場合() throws Exception {
        setDefault(new Locale("en", "AU"));
        GlobalHello.main(new String[]{});

        assertThat(outputStream.toString(), is(String.format("G'day%n")));
    }

    @Test
    public void ja_JP_osakaの場合() throws Exception {
        setDefault(new Locale("ja", "JP", "osaka"));
        GlobalHello.main(new String[]{});

        assertThat(outputStream.toString(), is(String.format("まいど！%n")));
    }
}
package ch20.ex20_01;

import java.io.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.*;
import org.junit.rules.*;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.*;
import org.junit.experimental.theories.*;

public class ByteTranslatorTest {
  @Test
  public void translateがバイト値を別の値に変換すること() throws Exception {
    ByteTranslator sut = new ByteTranslator();
    InputStream in = new ByteArrayInputStream("abracadabra!".getBytes());
    OutputStream out = new ByteArrayOutputStream();
    sut.translate(in, out, (byte)'b', (byte)'B');

    assertThat(out.toString(), is("aBracadaBra!"));
  }
}

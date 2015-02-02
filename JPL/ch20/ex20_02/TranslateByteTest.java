package ch20.ex20_02;

import java.io.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.*;
import org.junit.rules.*;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.*;
import org.junit.experimental.theories.*;

public class TranslateByteTest {
  @Test
  public void TranslateByteはバイト値を別の値に変換するフィルターであること() throws Exception {
    Reader in = new StringReader("abracadabra!");
    TranslateByte out = new TranslateByte(in, (byte)'b', (byte)'B');

    StringBuilder builder = new StringBuilder();
    int r;
    do {
      r = out.read();
      if (r > 0) {
        builder.append((char)r);
      }
    } while (r > 0);

    assertThat(builder.toString(), is("aBracadaBra!"));
  }
}

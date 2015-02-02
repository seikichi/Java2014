package ch20.ex20_03;

import java.io.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.*;
import org.junit.rules.*;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.*;
import org.junit.experimental.theories.*;

public class EncryptInputStreamTest {
  @Test
  public void EncryptInputStreamはバイトを暗号化すること() throws Exception {
    InputStream in = new ByteArrayInputStream("abracadabra!".getBytes());
    InputStream out = new EncryptInputStream(in);

    StringBuilder builder = new StringBuilder();
    int r;
    do {
      r = out.read();
      if (r > 0) { builder.append((char)r); }
    } while (r > 0);

    assertThat(builder.toString(), is(not("abracadabra!")));
  }

  @Test
  public void 暗号化したバイトをDecryptInputStreamで復号できること() throws Exception {
    InputStream in = new ByteArrayInputStream("abracadabra!".getBytes());
    InputStream out = new DecryptInputStream(new EncryptInputStream(in));

    StringBuilder builder = new StringBuilder();
    int r;
    do {
      r = out.read();
      if (r > 0) { builder.append((char)r); }
    } while (r > 0);

    assertThat(builder.toString(), is("abracadabra!"));
  }
}

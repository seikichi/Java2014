package ch20.ex20_03;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class EncryptInputStream extends FilterInputStream {
  private final byte xorByte = 42;

  public EncryptInputStream(InputStream in) {
    super(in);
  }

  public int read() throws IOException {
    int c = super.read();
    return c == -1 ? c : (c ^ xorByte);
  }
}

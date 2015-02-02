package ch20.ex20_02;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class TranslateByte extends FilterReader {
  private final byte from, to;

  public TranslateByte(Reader in, byte from, byte to) {
    super(in);
    this.from = from;
    this.to = to;
  }

  public int read() throws IOException {
    int c = super.read();
    return c == from ? to : c;
  }
}

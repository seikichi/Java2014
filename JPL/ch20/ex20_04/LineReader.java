package ch20.ex20_04;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class LineReader extends FilterReader {
  public LineReader(Reader in) { super(in); }

  public String readLine() throws IOException {
    String separator = System.lineSeparator();
    StringBuilder builder = new StringBuilder();

    int r;
    for (;;) {
      r = this.read();
      if (r <= 0) { break; }
      builder.append((char)r);

      if (builder.substring(builder.length() - separator.length()).equals(separator)) {
        builder.setLength(builder.length() - separator.length());
        break;
      }
    }

    if (r <= 0 && builder.length() <= 0) { return null; }
    return builder.toString();
  }
}

package ch20.ex20_01;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class ByteTranslator {
  void translate(InputStream in, OutputStream out, byte from, byte to) throws IOException {
    int b;
    while ((b = in.read()) != -1) {
      out.write(b == from ? to : b);
    }
  }

  public static void main(String[] args) {
    byte from = (byte) args[0].charAt(0);
    byte to = (byte) args[1].charAt(0);

    try {
      new ByteTranslator().translate(System.in, System.out, from, to);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

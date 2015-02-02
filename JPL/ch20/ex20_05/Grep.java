package ch20.ex20_05;

import java.io.*;

public class Grep {
  public static void grep(Reader reader, String word) throws IOException {
    grep(reader, word, "%d: %s", new PrintWriter(System.out, true));
  }

  public static void grep(Reader reader, String word, String format, PrintWriter writer) throws IOException {
    LineNumberReader in = new LineNumberReader(reader);
    for (;;) {
      String line = in.readLine();
      if (line == null) { break; }
      if (line.contains(word)) {
        writer.println(String.format(format, in.getLineNumber(), line));
      }
    }
  }

  public static void main(String[] args) throws IOException {
    if (args.length != 2) {
      throw new IllegalArgumentException("need word and file");
    }
    FileReader reader = new FileReader(args[0]);
    String word = args[1];
    grep(reader, word);
  }
}

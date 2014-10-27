import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public final class Converter {
  public static void main(String[] args) {
    BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder b = new StringBuilder();
    String line;

    try {
      while ((line = r.readLine()) != null) {
        for (int i = 0; i < line.length(); i++) {
          b.append(String.format("\\u%04X", Character.codePointAt(line, i)));
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }

    System.out.println(b);
  }
}

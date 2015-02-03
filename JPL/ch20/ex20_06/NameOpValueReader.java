package ch20.ex20_06;

import java.util.HashMap;
import java.util.Map;
import java.util.IllegalFormatException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.io.IOException;

class NameOpValueReader {
  public Map<String, Double> read(Reader input, String v1, String v2, String v3) throws IOException {
    HashMap<String, Double> values = new HashMap<String, Double>() {{
      put(v1, 0.0);
      put(v2, 0.0);
      put(v3, 0.0);
    }};

    StreamTokenizer in = new StreamTokenizer(input);
    in.commentChar('#');

    while (in.nextToken() != StreamTokenizer.TT_EOF) {
      if (in.ttype != StreamTokenizer.TT_WORD) {
        throw new IOException("invalie name");
      }
      String name = in.sval;
      if (!values.containsKey(name)) {
        throw new IOException("invalid name");
      }

      in.nextToken();
      if (in.ttype != '=' && in.ttype != '+' && in.ttype != '-') {
        throw new IOException("invalid op");
      }
      char op = (char)in.ttype;

      in.nextToken();
      if (in.ttype != StreamTokenizer.TT_NUMBER) {
        throw new IOException("invalid value");
      }
      double value = in.nval;

      if (op == '=') {
        values.put(name, value);
      } else if (op == '+') {
        values.put(name, values.get(name) + value);
      } else if (op == '-') {
        values.put(name, values.get(name) - value);
      }
    }

    return values;
  }

  public static void main(String[] args) throws IOException {
    String input = "x = 10\n" +
      "# comment\n" +
      "y + 1" +
      "z + 2" +
      "y = 20" +
      "\n";

    Map<String, Double> values = new NameOpValueReader().read(new StringReader(input), "x", "y", "z");
    System.out.println(values.get("x"));
    System.out.println(values.get("y"));
    System.out.println(values.get("z"));
  }
}

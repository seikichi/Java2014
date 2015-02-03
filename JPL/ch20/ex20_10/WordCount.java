package ch20.ex20_10;

import java.util.HashMap;
import java.util.Map;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;

public class WordCount {
  public static void main(String[] args) throws IOException {
    if (args.length != 1) {
      return;
    }
    File file = new File(args[0]);

    HashMap<String, Integer> count = new HashMap<>();
    try (BufferedReader r = new BufferedReader(new FileReader(file))) {
      for (;;) {
        String line = r.readLine();
        if (line == null) { break; }
        for (String word : line.split("\\s+")) {
          if (!count.containsKey(word)) {
            count.put(word, 0);
          }
          count.put(word, count.get(word) + 1);
        }
      }
    }
    for (Map.Entry<String, Integer> e : count.entrySet()) {
      System.out.println(String.format("%s: %d", e.getKey(), e.getValue()));
    }
  }
}

package ch13.ex13_06;

import java.util.ArrayList;
import java.util.regex.Pattern;

public final class StringUtils {
  public static int countMatches(String str, int ch) {
    if (str == null) {
      return 0;
    }

    int index = str.indexOf(ch, 0), count = 0;
    while (index >= 0) {
      count++;
      index = str.indexOf(ch, index + 1);
    }
    return count;
  }

  public static int countMatches(String str, String target) {
    if (str == null) {
      return 0;
    }

    int index = str.indexOf(target, 0), count = 0;
    while (index >= 0) {
      count++;
      index = str.indexOf(target, index + 1);
    }
    return count;
  }

  public static String[] delimitedString(String from, char start, char end) {
    if (from == null) { return new String[]{}; }

    ArrayList<String> result = new ArrayList<>();
    for (int startPos = from.indexOf(start); startPos != -1; startPos = from.indexOf(start, startPos + 1)) {
      for (int endPos = from.indexOf(end, startPos + 1); endPos != -1; endPos = from.indexOf(end, endPos + 1)) {
        result.add(from.substring(startPos, endPos + 1));
      }
    }
    return result.toArray(new String[result.size()]);
  }

  public static String formatNumber(String number) {
    return formatNumber(number, 3, ",");
  }

  public static String formatNumber(String number, int digits) {
    return formatNumber(number, digits, ",");
  }

  public static String formatNumber(String number, int digits, String delimiter) {
    if (Pattern.compile("\\D").matcher(number).find()) {
      throw new IllegalArgumentException(String.format("Argument `%s` contains a not number character.", number));
    }
    String reversed = new StringBuilder(number).reverse().toString();
    String replaced = reversed.replaceAll(String.format("(\\d{%d})", digits), String.format("$1%s", delimiter));
    if (replaced.endsWith(delimiter)) {
      replaced = replaced.substring(0, replaced.length() - delimiter.length());
    }
    return new StringBuilder(replaced).reverse().toString();
  }
}

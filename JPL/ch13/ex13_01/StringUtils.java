package ch13.ex13_01;

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
}

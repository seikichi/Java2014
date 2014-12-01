package ch13.ex13_02;

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
}

package ch10.ex10_02;

public final class SpecialCharacterReplacer {
  public String replace(String s) {
    StringBuilder b = new StringBuilder();
    for (char c : s.toCharArray()) {
      switch (c) {
      case '\n': b.append("\\n"); break;
      case '\t': b.append("\\t"); break;
      case '\b': b.append("\\b"); break;
      case '\r': b.append("\\r"); break;
      case '\f': b.append("\\f"); break;
      case '\\': b.append("\\\\"); break;
      case '\'': b.append("\\\'"); break;
      case '\"': b.append("\\\""); break;
      default: b.append(c); break;
      }
    }
    return b.toString();
  }
}

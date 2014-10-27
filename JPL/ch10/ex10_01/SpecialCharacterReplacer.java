package ch10.ex10_01;

public final class SpecialCharacterReplacer {
  public final String replace(String s) {
    StringBuilder b = new StringBuilder();
    for (char c : s.toCharArray()) {
      if (c == '\n') { b.append("\\n"); }
      else if (c == '\t') { b.append("\\t"); }
      else if (c == '\b') { b.append("\\b"); }
      else if (c == '\r') { b.append("\\r"); }
      else if (c == '\f') { b.append("\\f"); }
      else if (c == '\\') { b.append("\\\\"); }
      else if (c == '\'') { b.append("\\\'"); }
      else if (c == '\"') { b.append("\\\""); }
      else { b.append(c); }
    }
    return b.toString();
  }
}

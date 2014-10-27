package ch07.ex07_02;

public final class NumberLiteral {
  char c;
  byte b;
  short s;
  int i;
  long l;
  float f;
  double d;

  // Literals: 'Q', 29, 1.0f, 1.0;

  private void assignToChar() {
    c = 'Q';
    c = 1;
    // c = -1;
    // c = 1000000;
    // c = 2.0f;
    // c = 2.0;
  }

  private void assignToByte() {
    b = 'Q';
    b = 1;
    b = -1;
    // b = 1000000;
    // b = -1000000;
    // b = 2.0f;
    // b = 2.0;
  }

  private void assignToShort() {
    s = 'Q';
    s = 1;
    s = -1;
    // s = 1000000;
    // s = -1000000;
    // s = 2.0f;
    // s = 2.0;
  }

  private void assignToInt() {
    i = 'Q';
    i = 1;
    i = -1;
    // i = +100000000000;
    // i = -100000000000;
    // i = 2.0f;
    // i = 2.0;
  }

  private void assignToLong() {
    l = 'Q';
    l = 1;
    l = -1;
    // l = +100000000000;
    // l = -100000000000;
    // l = 2.0f;
    // l = 2.0;
  }

  private void assignToFloat() {
    f = 'Q';
    f = 1;
    f = -1;
    // f = +100000000000;
    // f = -100000000000;
    f = 2.0f;
    // f = 2.0;
  }

  private void assignToDouble() {
    d = 'Q';
    d = 1;
    d = -1;
    // d = +100000000000;
    // d = -100000000000;
    d = 2.0f;
    d = 2.0;
  }

  public static void main(String[] args) {}
}

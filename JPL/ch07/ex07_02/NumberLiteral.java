package ch07.ex07_02;

public final class NumberLiteral {
  static char c;
  static byte b;
  static short s;
  static int i;
  static long l;
  static float f;
  static double d;

  // Literals: 'Q', 29, 1.0f, 1.0;

  private static void assignToChar() {
    c = 'Q';
    c = 1;
    // c = -1;
    // c = 1000000;
    // c = 2.0f;
    // c = 2.0;
  }

  private static void assignToByte() {
    b = 'Q';
    b = 1;
    b = -1;
    // b = 1000000;
    // b = -1000000;
    // b = 2.0f;
    // b = 2.0;
  }

  private static void assignToShort() {
    s = 'Q';
    s = 1;
    s = -1;
    // s = 1000000;
    // s = -1000000;
    // s = 2.0f;
    // s = 2.0;
  }

  private static void assignToInt() {
    i = 'Q';
    i = 1;
    i = -1;
    // i = +100000000000;
    // i = -100000000000;
    // i = 2.0f;
    // i = 2.0;
  }

  private static void assignToLong() {
    l = 'Q';
    l = 1;
    l = -1;
    // l = +100000000000;
    // l = -100000000000;
    // l = 2.0f;
    // l = 2.0;
  }

  private static void assignToFloat() {
    f = 'Q';
    f = 1;
    f = -1;
    // f = +100000000000;
    // f = -100000000000;
    f = 2.0f;
    // f = 2.0;
  }

  private static void assignToDouble() {
    d = 'Q';
    d = 1;
    d = -1;
    // d = +100000000000;
    // d = -100000000000;
    d = 2.0f;
    d = 2.0;
  }

  public static void main(String[] args) {
    assignToChar();
    assignToByte();
    assignToShort();
    assignToInt();
    assignToLong();
    assignToFloat();
    assignToDouble();
  }
}

package ch09.ex09_02;

public final class BuiltinBitCounter implements BitCounter {
  public int bitCount(int value) {
    return Integer.bitCount(value);
  }
}

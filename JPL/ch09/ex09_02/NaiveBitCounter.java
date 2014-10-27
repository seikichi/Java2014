package ch09.ex09_02;

public final class NaiveBitCounter implements BitCounter {
  public int bitCount(int value) {
    int count = 0;
    for (int v = value; v != 0; v &= v - 1) { count++; }
    return count;
  }
}

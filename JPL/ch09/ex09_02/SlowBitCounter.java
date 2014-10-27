package ch09.ex09_02;

public final class SlowBitCounter implements BitCounter {
  public int bitCount(int value) {
    int count = 0;
    for (int v = value; v != 0; v >>>= 1) { count += v & 1; }
    return count;
  }
}

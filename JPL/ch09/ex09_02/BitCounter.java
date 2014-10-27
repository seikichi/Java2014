package ch09.ex09_02;

import java.util.Random;

public interface BitCounter {
  int bitCount(int value);

  public static void main(String[] args) {
    int dataSize = 100000;
    if (args.length >= 1) {
      dataSize = Integer.parseInt(args[0]);
    }

    BitCounter counters[] = {
      new SlowBitCounter(),
      new NaiveBitCounter(),
      new BuiltinBitCounter(),
      new OptimizedBitCounter(),
    };

    int dataset[] = new int[dataSize];

    Random r = new Random();
    for (int i = 0; i < dataSize; i++) {
      dataset[i] = r.nextInt();
    }

    for (BitCounter c : counters) {
      int sum = 0;
      long time = 0;
      for (int d : dataset) {
        long start = System.currentTimeMillis();
        sum += c.bitCount(d);
        long stop = System.currentTimeMillis();
        time += stop - start;
      }

      System.out.printf("%s: %d%n", c, time);
    }
  }
}

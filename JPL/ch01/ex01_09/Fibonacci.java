package ch01.ex01_09;

class Fibonacci {
  private static final int MAX_OUTPUT_NUM = 10;

  public static void main(String[] args) {
    int lo = 1;
    int hi = 1;
    final int[] fibonacci = new int[MAX_OUTPUT_NUM];

    for (int i = 0; i < MAX_OUTPUT_NUM; ++i) {
      fibonacci[i] = lo;
      hi = lo + hi;
      lo = hi - lo;
    }

    for (int i = 0; i < MAX_OUTPUT_NUM; ++i) {
      System.out.println(fibonacci[i]);
    }
  }
}

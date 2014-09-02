package ch01.ex01_09;

class Fibonacci {
  public static final int MAX = 10;

  public static void main(String[] args) {
    int lo = 1;
    int hi = 1;
    final int[] fibonacci = new int[MAX];

    for (int i = 0; i < MAX; ++i) {
      fibonacci[i] = lo;
      hi = lo + hi;
      lo = hi - lo;
    }

    for (int i = 0; i < MAX; ++i) {
      System.out.println(fibonacci[i]);
    }
  }
}

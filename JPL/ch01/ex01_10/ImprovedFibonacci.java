package ch01.ex01_10;

class FibonacciValue {
  public final int value;
  public final boolean isEven;

  FibonacciValue(int value) {
    this.value = value;
    this.isEven = value % 2 == 0;
  }
}

class ImprovedFibonacci {
  static final int MAX_OUTPUT_NUM = 9;

  public static void main(String[] args) {
    int lo = 1;
    int hi = 1;

    FibonacciValue[] fibonacci = new FibonacciValue[MAX_OUTPUT_NUM];

    fibonacci[0] = new FibonacciValue(lo);
    for (int i = 1; i < MAX_OUTPUT_NUM; i++) {
      fibonacci[i] = new FibonacciValue(hi);
      hi = lo + hi;
      lo = hi - lo;
    }

    for (int i = 0; i < MAX_OUTPUT_NUM; i++) {
      String mark = fibonacci[i].isEven ? " *" : "";
      System.out.println((i + 1) + ": " + fibonacci[i].value + mark);
    }
  }
}

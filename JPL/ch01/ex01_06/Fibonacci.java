package ch01.ex01_06;

class Fibonacci {
  private static final String TITLE = "!!!!! Fibonacci !!!!!";

  public static void main(String[] args) {
    System.out.println(TITLE);

    int lo = 1;
    int hi = 1;
    System.out.println(lo);
    while (hi < 50) {
      System.out.println(hi);
      hi = lo + hi;
      lo = hi - lo;
    }
  }
}

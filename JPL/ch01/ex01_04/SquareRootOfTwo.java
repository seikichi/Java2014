package ch01.ex01_04;

import java.math.BigDecimal;

public class SquareRootOfTwo {
  private static final int OUTPUT_NUM = 10;

  public static void main(String[] args) {
    // http://en.wikipedia.org/wiki/Methods_of_computing_square_roots#Decimal_.28base_10.29
    final int target = 2;

    BigDecimal P = BigDecimal.valueOf(0);
    BigDecimal C =  BigDecimal.valueOf(target);
    final BigDecimal TWENTY =  BigDecimal.valueOf(20);
    final BigDecimal TEN =  BigDecimal.valueOf(10);
    final BigDecimal HUNDRED =  BigDecimal.valueOf(100);

    for (int i = 0; i < OUTPUT_NUM; ++i) {
      BigDecimal X =  BigDecimal.valueOf(0);
      for (int x = 0; x < 10; ++x) {
        BigDecimal X_plus_1 = BigDecimal.valueOf(x + 1);
        if (P.multiply(TWENTY).add(X_plus_1).multiply(X_plus_1).compareTo(C) > 0) {
          X = BigDecimal.valueOf(x);
          break;
        }
      }

      System.out.println(X);

      BigDecimal Y = P.multiply(TWENTY).add(X).multiply(X);
      C = C.subtract(Y).multiply(HUNDRED);
      P = P.multiply(TEN).add(X);
    }
  }
}

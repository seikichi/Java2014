package ch01.ex01_04;

import java.math.BigDecimal;

public class SquareRootOfTwo {
  public static void main(String[] args) {
    BigDecimal n = new BigDecimal(2), s = new BigDecimal(0);

    int j;
    for (int i = 0; i < 10; ++i) {
      for (j = 1; j < 10; ++j) {
        // if (j * (s * 10 + j) >= n) { break; }
        if (s.multiply(BigDecimal.TEN).add(BigDecimal.valueOf(j)).multiply(BigDecimal.valueOf(j)).compareTo(n) >= 0) {
          break;
        }
      }
      j -= 1;
      System.out.println(j);

      // n = (n - j * (s * 10 + j)) * 100;
      // s = s * 10 + 2 * j;
      n = n.subtract(s.multiply(BigDecimal.TEN).add(BigDecimal.valueOf(j)).multiply(BigDecimal.valueOf(j))).multiply(BigDecimal.valueOf(100));
      s = s.multiply(BigDecimal.TEN).add(BigDecimal.valueOf(2 * j));
    }
  }
}

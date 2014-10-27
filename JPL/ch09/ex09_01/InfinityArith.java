package ch09.ex09_01;

public final class InfinityArith {
  public static void main(String[] args) {
    double posInf = +1 / 0.0;
    double negInf = -1 / 0.0;

    double ops[][] = {
      {posInf, posInf},
      {posInf, negInf},
      {negInf, posInf},
      {negInf, negInf}
    };

    for (double[] op : ops) {
      System.out.printf("%+f + %+f = %+f%n", op[0], op[1], op[0] + op[1]);
      System.out.printf("%+f - %+f = %+f%n", op[0], op[1], op[0] - op[1]);
      System.out.printf("%+f * %+f = %+f%n", op[0], op[1], op[0] * op[1]);
      System.out.printf("%+f / %+f = %+f%n", op[0], op[1], op[0] / op[1]);
    }
  }
}

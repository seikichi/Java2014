package ch03.ex03_03;

public class X {
  {
    printMask("フィールドにデフォルト値が設定された");
  }

  protected int xMask = 0x00ff;

  {
    printMask("Xのフィールドが初期化された");
  }

  public X() {
    printMask("Xのコンストラクタが実行された");

    System.out.printf("mask(0xffff) returns 0x%04x in X() %n", mask(0xffff));
  }

  protected int fullMask() {
    return xMask;
  }

  public final int mask(int orig) {
    return (orig & fullMask());
  }

  public void printMask(final String info) {
    System.out.printf("0x%04x 0x%04x (%s)%n", xMask, fullMask(), info);
  }
}

package ch03.ex03_03;

public class Y extends X {
  {
    printMask("Yのフィールドが初期化された");
  }

  public Y() {
    printMask("Yのコンストラクタが実行された");

    System.out.printf("mask(0xffff) returns 0x%04x in Y() %n", mask(0xffff));
  }

  private int yMask() {
    return 0xff00;
  }

  @Override
  public void printMask(final String info) {
    System.out.printf("0x%04x 0x%04x 0x%04x (%s)%n", xMask, yMask(), fullMask(), info);
  }

  @Override
  protected int fullMask() {
    return super.fullMask() | yMask();
  }

  public static void main(String[] args) {
    new Y();
  }
}

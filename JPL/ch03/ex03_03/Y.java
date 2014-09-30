// package ch03.ex03_02;

public class Y extends X {
  private int yMask = 0xff00;

  {
    printMask("Yのフィールドが初期化された");
  }

  public Y() {
    printMask("Yのコンストラクタが実行された");

    System.out.printf("mask(0xffff) returns 0x%04x in Y() %n", mask(0xffff));
  }

  @Override
  public void printMask(final String info) {
    System.out.printf("0x%04x 0x%04x 0x%04x (%s)%n", xMask, yMask, fullMask, info);
  }

  @Override
  protected void initializeFullMask() {
    super.initializeFullMask();
    this.fullMask |= yMask;
  }

  public static void main(String[] args) {
    Y y = new Y();
  }
}

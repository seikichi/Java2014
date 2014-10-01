package ch03.ex03_02;

public class Y extends X {
  protected int yMask = 0xff00;

  {
    printMask("Yのフィールドが初期化された");
  }

  public Y() {
    fullMask |= yMask;
    printMask("Yのコンストラクタが実行された");
  }

  @Override
  public void printMask(final String info) {
    System.out.printf("0x%04x 0x%04x 0x%04x (%s)%n", xMask, yMask, fullMask, info);
  }

  public static void main(String[] args) {
    new Y();
  }
}

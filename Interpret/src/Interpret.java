class TestData {
  public final int x1 = 0;
  private int x2 = 0;
  private final int x3 = 0;

  public static int x4 = 0;
  private static int x5 = 0;
  private final static Integer x6 = 0;

  private static final TestData data = new TestData();
}

public final class Interpret {
  public static void main(String[] args) {
    new InterpretPresenter(new InterpretModel());
  }
}

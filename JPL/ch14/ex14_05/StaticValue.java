package ch14.ex14_05;

public final class StaticValue {
  private static int value = 0;

  public static int get() {
    synchronized (StaticValue.class) { return value; }
  }
  public static void set(int x) {
    synchronized (StaticValue.class) { value = x; }
  }
  public static void add(int x) {
    synchronized (StaticValue.class) { value += x; }
  }
  public static void sub(int x) {
    synchronized (StaticValue.class) { value -= x; }
  }
}

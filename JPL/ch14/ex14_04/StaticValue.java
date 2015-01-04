package ch14.ex14_04;

public final class StaticValue {
  private static int value = 0;
  public static synchronized int get() { return value; }
  public static synchronized void set(int x) { value = x; }
  public static synchronized void add(int x) { value += x; }
}

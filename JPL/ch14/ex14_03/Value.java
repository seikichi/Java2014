package ch14.ex14_03;

public final class Value {
  private int value = 0;
  public synchronized int get() { return value; }
  public synchronized void add(int x) { value += x; }
}

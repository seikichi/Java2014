package ch03.ex03_07;

public class Attr {
  private final String name;
  private Object value = null;

  public Attr(String name) {
    this.name = name;
  }

  public Attr(String name, Object value) {
    this.name = name;
    this.value = value;
  }

  public final String getName() {
    return name;
  }

  public final Object getValue() {
    return value;
  }

  public Object setValue(Object newValue) {
    Object oldValue = value;
    value = newValue;
    return oldValue;
  }

  @Override
  public String toString() {
    return name + "='" + value + "'";
  }
}

package ch20.ex20_07;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

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

  public Attr(DataInputStream in) throws IOException {
    this.name = in.readUTF();
    String className = in.readUTF();
    Class<?> klass = null;
    try {
      klass = Class.forName(className);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    try {
      this.value = klass.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
    }
  }

  public void writeTo(DataOutputStream out) throws IOException {
    out.writeUTF(name);
    out.writeUTF(value.getClass().getName());
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

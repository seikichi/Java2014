package ch11.ex11_03;

import java.util.*;

public final class AttributedImpl<E> implements Attributed<E> {
  HashMap<String, Attr<E>> attrTable = new HashMap<>();

  public void add(Attr<E> newAttr) {
    attrTable.put(newAttr.getName(), newAttr);
  }

  public Attr<E> find(String attrName) {
    return attrTable.get(attrName);
  }

  public Attr<E> remove(String attrName) {
    return attrTable.remove(attrName);
  }

  public Iterator<Attr<E>> attrs() {
    return attrTable.values().iterator();
  }

  public static void main(String[] args) {
    Attributed<Object> attributed = new AttributedImpl<Object>();
    attributed.add(new Attr<Object>("answer", 42));
    attributed.add(new Attr<Object>("hello", "world"));

    Attr<Object> attr = attributed.find("answer");
    Object value = attr.getValue();
    System.out.println(value);
  }
}

package ch11.ex11_03;

public interface Attributed<E> {
  void add(Attr<? extends E> newAttr);
  Attr<? extends E> find(String attrName);
  Attr<? extends E> remove(String attrName);
  java.util.Iterator<Attr<? extends E>> attrs();
}

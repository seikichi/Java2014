package ch04.ex04_03;

public interface LinkedList extends Cloneable {
  Object getElement();
  LinkedList getNext();
  int countElements();
  LinkedList clone();
}

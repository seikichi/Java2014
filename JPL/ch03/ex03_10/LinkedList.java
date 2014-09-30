package ch03.ex03_10;

public class LinkedList implements Cloneable {
  private Object element;
  private LinkedList next;

  LinkedList(Object element) {
    this.element = element;
    this.next = null;
  }

  LinkedList(Object element, LinkedList next) {
    this(element);
    this.next = next;
  }

  public final Object getElement() { return element; }
  public final LinkedList getNext() { return next; }

  public final int countElements() {
    return 1 + (next != null ? next.countElements() : 0);
  }

  @Override
  public String toString() {
    return "LinkedList(element=" + element.toString() +
      ", next=" + (next != null ? next.toString() : "null") + ")";
  }

  @Override
  public LinkedList clone() {
    if (next == null) {
      return new LinkedList(element);
    }
    return new LinkedList(element, next.clone());
  }

  static LinkedList fromElements(Object... elements) {
    LinkedList prev = null, first = null;
    for (Object element: elements) {
      LinkedList list = new LinkedList(element);
      if (prev == null) {
        first = list;
      } else {
        prev.next = list;
      }
      prev = list;
    }
    return first;
  }
}

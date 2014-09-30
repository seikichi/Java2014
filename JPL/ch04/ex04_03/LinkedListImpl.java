package ch04.ex04_03;

public class LinkedListImpl implements LinkedList {
  private Object element;
  private LinkedListImpl next;

  LinkedListImpl(Object element) {
    this.element = element;
    this.next = null;
  }

  LinkedListImpl(Object element, LinkedListImpl next) {
    this(element);
    this.next = next;
  }

  public final Object getElement() { return element; }
  public final LinkedListImpl getNext() { return next; }

  public final int countElements() {
    return 1 + (next != null ? next.countElements() : 0);
  }

  @Override
  public String toString() {
    return "LinkedList(element=" + element.toString() +
      ", next=" + (next != null ? next.toString() : "null") + ")";
  }

  @Override
  public LinkedListImpl clone() {
    if (next == null) {
      return new LinkedListImpl(element);
    }
    return new LinkedListImpl(element, next.clone());
  }

  static LinkedListImpl fromElements(Object... elements) {
    LinkedListImpl prev = null, first = null;
    for (Object element: elements) {
      LinkedListImpl list = new LinkedListImpl(element);
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

package ch11.ex11_01;

public final class LinkedList<E> implements Cloneable {
  private E element;
  private LinkedList<E> next;

  LinkedList(E element) {
    this.element = element;
    this.next = null;
  }

  LinkedList(E element, LinkedList<E> next) {
    this(element);
    this.next = next;
  }

  public final E getElement() { return element; }
  public final LinkedList<E> getNext() { return next; }

  public final int countElements() {
    int count = 0;
    for (LinkedList<E> next = this; next != null; next = next.getNext()) {
      count++;
    }
    return count;
  }

  @Override
  public String toString() {
    return "LinkedList(element=" + element.toString() +
      ", next=" + (next != null ? next.toString() : "null") + ")";
  }

  @Override
  public LinkedList<E> clone() {
    if (next == null) {
      return new LinkedList<>(element);
    }
    return new LinkedList<>(element, next.clone());
  }

  @SafeVarargs
  static <E> LinkedList<E> fromElements(E... elements) {
    LinkedList<E> prev = null, first = null;
    for (E element: elements) {
      LinkedList<E> list = new LinkedList<>(element);
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

package ch19.ex19_01;

/**
 * An <code>LinkedList</code> object defines
 * a singly linked linear lists.
 *
 * @version 1.0
 * @author seikichi@kmc.gr.jp
 * @since 1.0
 */
public class LinkedList {
  /** The first element of this list. */
  private Object element;
  /** The rest of this list. */
  private LinkedList next;

  /**
   * Creates a new singly linked linear list with the
   * given element.
   *
   * @see LinkedList#LinkedList(Object, LinkedList)
   */
  LinkedList(Object element) {
    this.element = element;
    this.next = null;
  }

  /**
   * Creates a new singly linked linear list with the
   * given element and next list.
   *
   * @see LinkedList#LinkedList(Object)
   */
  LinkedList(Object element, LinkedList next) {
    this(element);
    this.next = next;
  }

  /** Returns this list's first element. */
  public Object getElement() { return element; }

  /** Returns this list's rest list. */
  public LinkedList getNext() { return next; }

  /** Counts the number of elements in this list. */
  public int countElements() {
    int count = 0;
    for (LinkedList iter = this; iter != null; iter = iter.next) {
      count++;
    }
    return count;
  }

  /** Returns a string of this list. */
  @Override
  public String toString() {
    return "LinkedList(element=" + element.toString() +
      ", next=" + (next != null ? next.toString() : "null") + ")";
  }

  /** Creates a new list from given elements. */
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

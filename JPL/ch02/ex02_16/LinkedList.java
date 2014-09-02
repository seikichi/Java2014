package ch02.ex02_16;

public class LinkedList {
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

  public Object getElement() { return element; }
  public LinkedList getNext() { return next; }

  public int countElements() {
    return 1 + (next != null ? next.countElements() : 0);
  }

  @Override
  public String toString() {
    return "LinkedList(element=" + element.toString() +
      ", next=" + (next != null ? next.toString() : "null") + ")";
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

  public static void main(String[] args) {
    Vehicle bycicle = new Vehicle("近藤誠一");
    Vehicle car = new Vehicle("Seiichi KONDO");
    LinkedList list = LinkedList.fromElements(bycicle, car);
    System.out.println(list);
  }
}

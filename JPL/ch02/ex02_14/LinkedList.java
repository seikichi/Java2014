// package ch02.ex02_14;

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
    bycicle.setSpeed(1.0);
    bycicle.setAngle(90);

    Vehicle car = new Vehicle("Seiichi KONDO");
    car.setSpeed(100);
    car.setAngle(30);

    LinkedList list = LinkedList.fromElements(bycicle, car);
    System.out.println(list);
  }
}

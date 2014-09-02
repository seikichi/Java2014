package ch02.ex02_11;

public class LinkedList {
  Object element;
  LinkedList next;

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

  public static void main(String[] args) {
    Vehicle bycicle = new Vehicle("近藤誠一");
    bycicle.speed = 1.0;
    bycicle.angle = 90;

    Vehicle car = new Vehicle("Seiichi KONDO");
    car.speed = 100;
    car.angle = 30;

    LinkedList second = new LinkedList(car);
    LinkedList first = new LinkedList(bycicle, second);

    System.out.println(first);
  }
}

package ch02.ex02_08;

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

  public static void main(String[] args) {
    Vehicle bycicle = new Vehicle("近藤誠一");
    bycicle.speed = 1.0;
    bycicle.angle = 90;

    Vehicle car = new Vehicle("Seiichi KONDO");
    car.speed = 100;
    car.angle = 30;

    LinkedList second = new LinkedList(car);
    LinkedList first = new LinkedList(bycicle, second);

    LinkedList iter = first;
    while (iter != null) {
      Vehicle vehicle = (Vehicle)iter.element; 
      System.out.println("Vehicle(id=" + vehicle.id +
                         ", speed=" + vehicle.speed +
                         ", angle=" + vehicle.angle +
                         ", ownerName=" + vehicle.ownerName + ")");
      iter = iter.next;
    }
  }
}

package ch02.ex02_06;

public class LinkedList {
  Object element;
  LinkedList next;

  public static void main(String[] args) {
    Vehicle bycicle = new Vehicle();
    bycicle.id = Vehicle.nextID++;
    bycicle.speed = 1.0;
    bycicle.angle = 90;
    bycicle.ownerName = "近藤誠一";

    Vehicle car = new Vehicle();
    car.id = Vehicle.nextID++;
    car.speed = 100;
    car.angle = 30;
    car.ownerName = "Seiichi KONDO";

    LinkedList first = new LinkedList();
    LinkedList second = new LinkedList();
    first.element = bycicle;
    first.next = second;
    second.element = car;
    second.next = null;

    LinkedList iter = first;
    while (iter != null) {
      Vehicle vehicle = (Vehicle)iter.element;
      System.out.printf("Vehicle(id=%d, speed=%f, angle=%f, ownerName=%s)\n",
                        vehicle.id,
                        vehicle.speed,
                        vehicle.angle,
                        vehicle.ownerName);
      iter = iter.next;
    }
  }
}

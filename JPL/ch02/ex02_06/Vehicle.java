package ch02.ex02_06;

import java.util.Arrays;

public class Vehicle {
  public static long nextID = 0;

  public long id;
  public double speed;
  public double angle;
  public String ownerName;

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

    for (Vehicle vehicle: Arrays.asList(bycicle, car)) {
      System.out.println("Vehicle(id=" + vehicle.id +
                         ", speed=" + vehicle.speed +
                         ", angle=" + vehicle.angle +
                         ", ownerName=" + vehicle.ownerName + ")");
    }
  }
}

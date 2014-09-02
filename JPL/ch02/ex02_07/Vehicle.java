package ch02.ex02_07;

import java.util.Arrays;

public class Vehicle {
  public static long nextID = 0;

  public final long id;
  public double speed;
  public double angle;
  public String ownerName;

  Vehicle() {
    this.id = nextID++;
  }

  Vehicle(final String ownerName) {
    this();
    this.ownerName = ownerName;
  }

  public static void main(String[] args) {
    Vehicle bycicle = new Vehicle("近藤誠一");
    bycicle.speed = 1.0;
    bycicle.angle = 90;

    Vehicle car = new Vehicle("Seiichi KONDO");
    car.speed = 100;
    car.angle = 30;

    for (Vehicle vehicle: Arrays.asList(bycicle, car)) {
      System.out.println("Vehicle(id=" + vehicle.id +
                         ", speed=" + vehicle.speed +
                         ", angle=" + vehicle.angle +
                         ", ownerName=" + vehicle.ownerName + ")");
    }
  }
}

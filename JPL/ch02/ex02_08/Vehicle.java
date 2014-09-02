package ch02.ex02_08;

import java.util.Arrays;

public class Vehicle {
  public final long id;
  public double speed;
  public double angle;
  public String ownerName;

  public static long nextID = 0;

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
      System.out.printf("Vehicle(id=%d, speed=%f, angle=%f, ownerName=%s)\n",
                        vehicle.id,
                        vehicle.speed,
                        vehicle.angle,
                        vehicle.ownerName);
    }
  }
}

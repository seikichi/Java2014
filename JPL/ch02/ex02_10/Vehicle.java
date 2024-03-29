package ch02.ex02_10;

import java.util.Arrays;

public class Vehicle {
  private static long nextID = 0;

  public final long id;
  public double speed;
  public double angle;
  public String ownerName;

  public static long getMaximumID() { return nextID - 1; }

  {
    this.id = nextID++;
  }

  Vehicle() { }

  Vehicle(final String ownerName) {
    this.ownerName = ownerName;
  }

  @Override
  public String toString() {
    return "Vehicle(id=" + id +
      ", speed=" + speed +
      ", angle=" + angle +
      ", ownerName=" + ownerName + ")";
  }

  public static void main(String[] args) {
    Vehicle bycicle = new Vehicle("近藤誠一");
    bycicle.speed = 1.0;
    bycicle.angle = 90;

    Vehicle car = new Vehicle("Seiichi KONDO");
    car.speed = 100;
    car.angle = 30;

    for (Vehicle vehicle: Arrays.asList(bycicle, car)) {
      System.out.println(vehicle);
    }
  }
}

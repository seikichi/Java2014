package ch02.ex02_16;

import java.util.Arrays;

public class Vehicle {
  private final long id;
  private double speed;
  private double angle;
  private String ownerName;

  private static long nextID = 0;
  static long getMaximumID() { return nextID - 1; }

  {
    this.id = nextID++;
  }

  Vehicle() { }

  Vehicle(final String ownerName) {
    this.ownerName = ownerName;
  }

  public long getID() { return id; }
  public double getSpeed() { return speed; }
  public double getAngle() { return angle; }
  public String getOwnerName() { return ownerName; }

  public void setSpeed(double newSpeed) { speed = newSpeed; }
  public void setAngle(double newAngle) { angle = newAngle; }
  public void setOwnerName(String newOwnerName) { ownerName = newOwnerName; }

  public void stop() { this.setSpeed(0.0); }
  public void changeSpeed(double speed) { this.setSpeed(speed); }

  @Override
  public String toString() {
    return "Vehicle(id=" + id +
      ", speed=" + speed +
      ", angle=" + angle +
      ", ownerName=" + ownerName + ")";
  }

  public static void main(String[] args) {
    Vehicle bycicle = new Vehicle("近藤誠一");
    bycicle.setSpeed(1.0);
    bycicle.setAngle(90.0);

    Vehicle car = new Vehicle("Seiichi KONDO");
    car.setSpeed(100.0);
    car.setAngle(30.0);

    for (Vehicle vehicle: Arrays.asList(bycicle, car)) {
      System.out.println(vehicle);
    }
  }
}

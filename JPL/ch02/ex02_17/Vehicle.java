package ch02.ex02_17;

import java.util.Arrays;

public class Vehicle {
  private static long nextID = 0;

  private final long id;
  private double speed;
  private double angle;
  private String ownerName;

  public enum Turn { Left, Right }
  public static Turn TURN_LEFT = Turn.Left;
  public static Turn TURN_RIGHT = Turn.Right;

  public static long getMaximumID() { return nextID - 1; }

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

  public void setOwnerName(String newOwnerName) {
    ownerName = newOwnerName;
  }

  public void stop() { this.speed = 0.0; }
  public void changeSpeed(double newSpeed) {
    this.speed = newSpeed;
  }
  public void turn(double angleInDegree) {
    this.angle += angleInDegree;
  }
  public void turn(Turn turn) {
    if (turn == Turn.Left) { this.turn(+90.0); }
    if (turn == Turn.Right) { this.turn(-90.0); }
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
    Vehicle car = new Vehicle("Seiichi KONDO");

    for (Vehicle vehicle: Arrays.asList(bycicle, car)) {
      System.out.println(vehicle);
    }
  }
}

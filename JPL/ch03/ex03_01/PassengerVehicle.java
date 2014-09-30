package ch03.ex03_01;

public class PassengerVehicle extends Vehicle {
  private int seatNum;
  private int occupiedSeatNum;

  public PassengerVehicle(String ownerName, int seatNum) {
    super(ownerName);
    this.seatNum = seatNum;
    this.occupiedSeatNum = 0;
  }

  public int getSeatNum() { return seatNum; }
  public int getOccupiedSeatNum() { return occupiedSeatNum; }

  public void setOccupiedSeatNum(int occupiedSeatNum) {
    this.occupiedSeatNum = occupiedSeatNum;
  }

  @Override
  public String toString() {
    return "PassengerVehicle(id=" + getID() +
      ", speed=" + getSpeed() +
      ", angle=" + getAngle() +
      ", ownerName=" + getOwnerName() +
      ", seatNum=" + getSeatNum() +
      ", occupiedSeatNum=" + getOccupiedSeatNum() + ")";
  }

  public static void main(String[] args) {
    PassengerVehicle vehicle01 = new PassengerVehicle("vehicle01", 4);
    PassengerVehicle vehicle02 = new PassengerVehicle("vehicle02", 3);
    vehicle01.setOccupiedSeatNum(2);
    vehicle02.setOccupiedSeatNum(1);
    System.out.println(vehicle01);
    System.out.println(vehicle02);
  }
}

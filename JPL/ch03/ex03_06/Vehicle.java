package ch03.ex03_06;

public class Vehicle {
  private static long nextID = 0;
  private static double startSpeed = 1.0;

  private final long id;
  private double speed;
  private double angle;
  private String ownerName;
  private EnergySource energySource;

  public enum Turn { Left, Right }
  public static Turn TURN_LEFT = Turn.Left;
  public static Turn TURN_RIGHT = Turn.Right;

  public static long getMaximumID() { return nextID - 1; }

  {
    this.id = nextID++;
  }

  Vehicle(final String ownerName, final EnergySource energySource) {
    this.ownerName = ownerName;
    this.energySource = energySource;
  }

  public final long getID() { return id; }
  public final double getSpeed() { return speed; }
  public final double getAngle() { return angle; }
  public final String getOwnerName() { return ownerName; }
  public final EnergySource getEnerySource() { return energySource; }

  public final void setOwnerName(final String newOwnerName) {
    ownerName = newOwnerName;
  }

  public final void start() {
    if (this.energySource.empty()) { return; }
    this.speed = startSpeed;
  }
  public final void stop() { this.speed = 0.0; }

  public final void turn(final double angleInDegree) {
    this.angle += angleInDegree;
  }
  public final void turn(final Turn turn) {
    if (turn == Turn.Left) { this.turn(+90.0); }
    if (turn == Turn.Right) { this.turn(-90.0); }
  }

  @Override
  public String toString() {
    return "Vehicle(id=" + getID() +
      ", speed=" + getSpeed() +
      ", angle=" + getAngle() +
      ", ownerName=" + getOwnerName() +
      ", energySource=" + getEnerySource() + ")";
  }

  public static void main(String[] args) {
    if (args.length != 1) {
      System.err.println("Usage: java Vehicle OWNER_NAME");
      return;
    }
    final String ownerName = args[0];
    System.out.println(new Vehicle(ownerName, new Battery(100)));
  }
}

package ch04.ex04_01;

public class GasTank implements EnergySource {
  private int tank;

  GasTank(final int tank) {
    this.tank = tank;
  }

  public boolean empty() { return tank <= 0; }

  public String toString() {
    return "GasTank(" + "tank=" + tank + ")";
  }
}

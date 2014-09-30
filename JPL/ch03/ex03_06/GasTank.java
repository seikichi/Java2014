package ch03.ex03_06;

public class GasTank extends EnergySource {
  private int tank;

  GasTank(final int tank) {
    this.tank = tank;
  }

  public boolean empty() { return tank <= 0; }

  @Override
  public String toString() {
    return "GasTank(" + "tank=" + tank + ")";
  }
}

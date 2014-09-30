package ch03.ex03_06;

public class Battery extends EnergySource {
  private int charge;

  Battery(final int charge) {
    this.charge = charge;
  }

  public boolean empty() { return charge <= 0; }

  @Override
  public String toString() {
    return "Battery(" + "charge=" + charge + ")";
  }
}

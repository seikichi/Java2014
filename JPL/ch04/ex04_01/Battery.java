package ch04.ex04_01;

public class Battery implements EnergySource {
  private int charge;

  Battery(final int charge) {
    this.charge = charge;
  }

  public boolean empty() { return charge <= 0; }

  public String toString() {
    return "Battery(" + "charge=" + charge + ")";
  }
}

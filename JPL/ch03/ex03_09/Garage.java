package ch03.ex03_09;

public class Garage implements Cloneable {
  private Vehicle[] vehicles;

  public final Vehicle get(int index) {
    return vehicles[index];
  }

  public final Vehicle set(int index, final Vehicle newVehicle) {
    Vehicle old = vehicles[index];
    vehicles[index] = newVehicle;
    return old;
  };

  public final int size() {
    return vehicles.length;
  }

  public Garage(int size) {
    vehicles = new Vehicle[size];
  }

  @Override
  public Garage clone() {
    try {
      Garage garage = (Garage) super.clone();
      garage.vehicles = new Vehicle[vehicles.length];
      for (int i = 0; i < vehicles.length; i++) {
        garage.vehicles[i] = vehicles[i].clone();
      }
      return garage;
    } catch (CloneNotSupportedException e) {
      // does not happen
      throw new InternalError(e.toString());
    }
  }
}

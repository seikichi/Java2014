package ch03.ex03_06;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class VehicleTest {
  class EmptyEnergy extends EnergySource {
    public boolean empty() { return true; }
  }

  class NonEmptyEnergy extends EnergySource {
    public boolean empty() { return false; }
  }

  private static final double EPS = 1e-10;
  private Vehicle vehicle, vehicleWithEmptyEnergy;

  @Before
  public void prepareVehicles() {
    vehicle = new Vehicle("seikichi", new NonEmptyEnergy());
    vehicleWithEmptyEnergy = new Vehicle("seikichi", new EmptyEnergy());
  }

  @Test
  public void getSpeed() {
    assertThat(vehicle.getSpeed(), is(closeTo(0.0, EPS)));
  }

  @Test
  public void getAngle() {
    assertThat(vehicle.getAngle(), is(closeTo(0.0, EPS)));
  }

  @Test
  public void vehicleHasOwnerName() {
    vehicle.setOwnerName("seikichi");
    assertThat(vehicle.getOwnerName(), is("seikichi"));
  }

  @Test
  public void eachVehicleHasUniqueId() {
    assertThat(vehicle.getID(),
               is(not(equalTo(vehicleWithEmptyEnergy.getID()))));
  }

  @Test
  public void startWithNonEmptyEnergy() {
    vehicle.start();
    assertThat(vehicle.getSpeed(), is(greaterThan(0.0)));
  }

  @Test
  public void startWithEmptyEnergy() {
    vehicleWithEmptyEnergy.start();
    assertThat(vehicleWithEmptyEnergy.getSpeed(), is(closeTo(0.0, EPS)));
  }

  @Test
  public void stop() {
    vehicle.stop();
    assertThat(vehicle.getSpeed(), is(closeTo(0.0, EPS)));
  }

  @Test
  public void turn() {
    double oldAngle = vehicle.getAngle();
    vehicle.turn(15.0);
    assertThat(vehicle.getAngle(), is(closeTo(oldAngle + 15, EPS)));
  }

  @Test
  public void turnLeft() {
    double oldAngle = vehicle.getAngle();
    vehicle.turn(Vehicle.TURN_LEFT);
    assertThat(vehicle.getAngle(), is(closeTo(oldAngle + 90, EPS)));
  }

  @Test
  public void turnRight() {
    double oldAngle = vehicle.getAngle();
    vehicle.turn(Vehicle.TURN_RIGHT);
    assertThat(vehicle.getAngle(), is(closeTo(oldAngle - 90, EPS)));
  }

  @Test
  public void vehicleToString() {
    vehicle.setOwnerName("kondo");
    assertThat(vehicle, hasToString(startsWith("Vehicle(id=")));
    assertThat(vehicle, hasToString(containsString("kondo")));
    assertThat(vehicle, hasToString(containsString("0.0")));
    assertThat(vehicle, hasToString(containsString("0")));
  }

  @Test
  public void getMaximumID() {
    long maximumID = Math.max(vehicle.getID(),
                              vehicleWithEmptyEnergy.getID());
    assertThat(Vehicle.getMaximumID(), is(maximumID));
  }
}

package ch02.ex02_03;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;

public class VehicleTest {
  private static final double EPS = 1e-10;
  private Vehicle vehicle;

  @Before
  public void prepareVehicle() {
    vehicle = new Vehicle();
  }

  @Test
  public void vehicleHasSpeed() {
    vehicle.speed = 1.0;
    assertThat(vehicle.speed, is(closeTo(1.0, EPS)));
  }

  @Test
  public void vehicleHasAngle() {
    vehicle.angle = 90.0;
    assertThat(vehicle.angle, is(closeTo(90.0, EPS)));
  }

  @Test
  public void vehicleHasOwnerName() {
    vehicle.ownerName = "seikichi";
    assertThat(vehicle.ownerName, is("seikichi"));
  }

  @Test
  public void vehicleHasId() {
    vehicle.id = 42L;
    assertThat(vehicle.id, is(42L));
  }

  @Test
  public void vehicleClassHasNonNegativeNextID() {
    assertThat(Vehicle.nextID, is(greaterThanOrEqualTo(0L)));
  }
}

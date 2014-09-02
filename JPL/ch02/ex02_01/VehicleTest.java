package ch02.ex02_01;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class VehicleTest {
  private Vehicle vehicle;
  private static double EPS = 1e-10;

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
}

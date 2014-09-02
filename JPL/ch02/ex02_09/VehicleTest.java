package ch02.ex02_09;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class VehicleTest {
  private Vehicle vehicle;
  private Vehicle otherVehicle1;
  private Vehicle otherVehicle2;

  private static double EPS = 1e-10;

  @Before
  public void prepareVehicle() {
    vehicle = new Vehicle();
    otherVehicle1 = new Vehicle("Seiichi KONDO");
    otherVehicle2 = new Vehicle();
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
  public void eachVehicleHasUniqueId() {
    assertThat(vehicle.id, is(not(equalTo(otherVehicle1.id))));
    assertThat(vehicle.id, is(not(equalTo(otherVehicle2.id))));
    assertThat(otherVehicle1.id, is(not(equalTo(otherVehicle2.id))));
  }

  @Test
  public void vehicleCanBeCreatedWithOwnerName() {
    assertThat(otherVehicle1.ownerName, is("Seiichi KONDO"));
  }

  @Test
  public void getMaximumID() {
    assertThat(Vehicle.getMaximumID(),
               is(Math.max(vehicle.id,
                           Math.max(otherVehicle1.id, otherVehicle2.id))));
  }
}

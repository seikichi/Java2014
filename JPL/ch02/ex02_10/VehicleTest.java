package ch02.ex02_10;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class VehicleTest {
  private static final double EPS = 1e-10;

  private Vehicle vehicle;
  private Vehicle otherVehicle1;
  private Vehicle otherVehicle2;
  private ByteArrayOutputStream outputStream;

  @Before
  public void prepareVehicles() {
    vehicle = new Vehicle();
    otherVehicle1 = new Vehicle("Seiichi KONDO");
    otherVehicle2 = new Vehicle();
  }

  @Before
  public void setUpStreams() {
    outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));
  }

  @After
  public void cleanUpStreams() {
    System.setOut(null);
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
  public void vehicleToString() {
    vehicle.speed = 1.0;
    vehicle.angle = 42;
    vehicle.ownerName = "kondo";
    assertThat(vehicle, hasToString(startsWith("Vehicle(id=")));
    assertThat(vehicle, hasToString(containsString("kondo")));
    assertThat(vehicle, hasToString(containsString("1.0")));
    assertThat(vehicle, hasToString(containsString("42")));
  }

  @Test
  public void getMaximumID() {
    long maximumID = Math.max(vehicle.id,
                              Math.max(otherVehicle1.id,
                                       otherVehicle2.id));
    assertThat(Vehicle.getMaximumID(), is(maximumID));
  }

  @Test
  public void vehicleMainOutputsFormattedVehicles() {
    Vehicle.main(new String[0]);
    String[] outputLines = outputStream.toString()
      .split(System.getProperty("line.separator"));

    assertThat(outputLines.length, greaterThan(1));
    for (String line: outputLines) {
      assertThat(line, startsWith("Vehicle(id="));
    }
  }
}

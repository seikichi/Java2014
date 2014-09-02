package ch02.ex02_07;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class VehicleTest {
  private static final double EPS = 1e-10;

  private Vehicle vehicle;
  private ByteArrayOutputStream outputStream;

  @Before
  public void prepareVehicle() {
    vehicle = new Vehicle();
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
  public void vehicleClassHasNonNegativeNextID() {
    assertThat(Vehicle.nextID, is(greaterThanOrEqualTo(0L)));
  }

  @Test
  public void eachVehicleHasUniqueId() {
    Vehicle v1 = new Vehicle();
    Vehicle v2 = new Vehicle();
    Vehicle v3 = new Vehicle();
    assertThat(v1.id, is(not(equalTo(v2.id))));
    assertThat(v1.id, is(not(equalTo(v3.id))));
    assertThat(v2.id, is(not(equalTo(v3.id))));
  }

  @Test
  public void vehicleCanBeCreatedWithOwnerName() {
    String ownerName = "Seiichi KONDO";
    Vehicle v = new Vehicle(ownerName);
    assertThat(v.ownerName, is(ownerName));
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

package ch02.ex02_13;

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
               is(not(equalTo(otherVehicle1.getID()))));
    assertThat(vehicle.getID(),
               is(not(equalTo(otherVehicle2.getID()))));
    assertThat(otherVehicle1.getID(),
               is(not(equalTo(otherVehicle2.getID()))));
  }

  @Test
  public void vehicleCanBeCreatedWithOwnerName() {
    assertThat(otherVehicle1.getOwnerName(), is("Seiichi KONDO"));
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
                              Math.max(otherVehicle1.getID(),
                                       otherVehicle2.getID()));
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

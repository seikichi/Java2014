package ch03.ex03_08;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PassengerVehicleTest {
  private static int seatNum = 10;
  private PassengerVehicle passengerVehicle;

  @Before
  public void preparePassengerVehicle() {
    passengerVehicle = new PassengerVehicle("FooBar", seatNum);
  }

  @Test
  public void cloneIsCorrectlyImplemented() {
    PassengerVehicle cloned = passengerVehicle.clone();
    assertThat(cloned.getSpeed(), is(passengerVehicle.getSpeed()));
    assertThat(cloned.getAngle(), is(passengerVehicle.getAngle()));
    assertThat(cloned.getOwnerName(), is(passengerVehicle.getOwnerName()));
    assertThat(cloned.getSeatNum(), is(passengerVehicle.getSeatNum()));
    assertThat(cloned.getOccupiedSeatNum(), is(passengerVehicle.getOccupiedSeatNum()));
    assertThat(cloned.getID(), is(not(passengerVehicle.getID())));
  }

  @Test
  public void getSeatNum() {
    assertThat(passengerVehicle.getSeatNum(), is(seatNum));
  }

  @Test
  public void getOccupiedSeatNum() {
    final int newOccupiedSeat = 5;
    passengerVehicle.setOccupiedSeatNum(newOccupiedSeat);
    assertThat(passengerVehicle.getOccupiedSeatNum(), is(newOccupiedSeat));
  }
}

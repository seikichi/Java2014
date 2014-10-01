package ch03.ex03_01;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;

public class PassengerVehicleTest {
  private static final int seatNum = 10;
  private PassengerVehicle passengerVehicle;

  @Before
  public void preparePassengerVehicle() {
    passengerVehicle = new PassengerVehicle("FooBar", seatNum);
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

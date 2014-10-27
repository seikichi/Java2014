package ch10.ex10_03;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.experimental.theories.*;

@RunWith(Theories.class)
public final class WeekdayTest {

  static final class Data {
    public final DayOfWeek input;
    public final boolean output;
    Data(DayOfWeek input, boolean output) {
      this.input = input;
      this.output = output;
    }
  }

  @DataPoints
  public static Data[] data = {
    new Data(DayOfWeek.MONDAY, true),
    new Data(DayOfWeek.TUESDAY, true),
    new Data(DayOfWeek.WEDNESDAY, true),
    new Data(DayOfWeek.THURSDAY, true),
    new Data(DayOfWeek.FRIDAY, true),
    new Data(DayOfWeek.SATURDAY, false),
    new Data(DayOfWeek.SUNDAY, false),
  };

  @Theory
  public void isWeekday(Data data) {
    assertThat(Weekday.isWeekday(data.input), is(data.output));
  }
}

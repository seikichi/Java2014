package ch06.ex06_05;

import java.awt.Color;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;

public class TrafficLightColorTest {
  @Test
  public void getColor() {
    assertThat(TrafficLightColor.RED.getColor(), is(Color.RED));
    assertThat(TrafficLightColor.YELLOW.getColor(), is(Color.YELLOW));
    assertThat(TrafficLightColor.BLUE.getColor(), is(Color.BLUE));
  }
}

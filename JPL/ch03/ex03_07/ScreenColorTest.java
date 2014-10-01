package ch03.ex03_07;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;

public class ScreenColorTest {
  private ScreenColor red;
  private ScreenColor green;
  private ScreenColor blue;
  private ScreenColor transparent;

  @Before
  public void setupColors() {
    red = new ScreenColor("red");
    blue = new ScreenColor("blue");
    green = new ScreenColor("green");
    transparent = new ScreenColor("transparent");
  }

  @Test
  public void equalsReturnsTrueForSameColor() {
    assertThat(red.equals(new ScreenColor("red")), is(true));
    assertThat(blue.equals(new ScreenColor("blue")), is(true));
    assertThat(green.equals(new ScreenColor("green")), is(true));
    assertThat(transparent.equals(new ScreenColor("transparent")), is(true));
  }

  @Test
  public void equalsReturnsFalseForDifferentColor() {
    assertThat(red.equals(blue), is(false));
    assertThat(red.equals(green), is(false));
    assertThat(blue.equals(green), is(false));
  }
}


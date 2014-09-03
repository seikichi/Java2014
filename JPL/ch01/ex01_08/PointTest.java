package ch01.ex01_08;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;

public class PointTest {
  private static final double EPS = 1e-10;

  @Test
  public void pointCanMoveToTheOtherPoint() {
    Point p1 = new Point();
    Point p2 = new Point();
    p2.move(9.0, 15.0);

    p1.move(p2);
    assertThat(p1.x, is(closeTo(9.0, EPS)));
    assertThat(p1.y, is(closeTo(15.0, EPS)));
  }
}

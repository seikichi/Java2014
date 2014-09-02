package ch01.ex01_08;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class PointTest {
  private static final double EPS = 1e-10;

  @Test
  public void pointCanMoveToTheOtherPoint() {
    Point p1 = new Point();
    Point p2 = new Point();
    p2.move(9.0, 15.0);

    p1.move(p2);
    assertThat(p1.x, is(closeTo(EPS, 9.0)));
    assertThat(p1.y, is(closeTo(EPS, 15.0)));
  }
}

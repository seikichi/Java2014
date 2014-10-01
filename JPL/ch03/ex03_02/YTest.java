package ch03.ex03_02;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;

public class YTest {
  private Y y;

  @Before
  public void prepareY() {
    y = new Y();
  }

  @Test
  public void mask() {
    assertThat(y.mask(0xABCD), is(0xABCD));
  }
}

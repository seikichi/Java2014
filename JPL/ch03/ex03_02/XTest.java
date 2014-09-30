package ch03.ex03_02;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class XTest {
  private X x;

  @Before
  public void prepareX() {
    x = new X();
  }

  @Test
  public void mask() {
    assertThat(x.mask(0xABCD), is(0x00CD));
  }
}

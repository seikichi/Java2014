package ch01.ex01_03;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class FibonacciTest {
  private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(outputStream));
  }

  @After
  public void cleanUpStreams() {
    System.setOut(null);
  }

  @Test
  public void fibonacciMain() {
    Fibonacci.main(new String[0]);
    assertThat(outputStream, hasToString(startsWith("!!!! Fibonacci !!!!\n")));
  }
}

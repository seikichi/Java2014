package ch01.ex01_03;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class FibonacciTest {
  private ByteArrayOutputStream outputStream;

  @Before
  public void setUpStreams() {
    outputStream = new ByteArrayOutputStream();
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

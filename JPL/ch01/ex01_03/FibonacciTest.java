package ch01.ex01_03;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
  public void testFibonacci() {
    Fibonacci.main(new String[0]);
    assertThat(outputStream.toString(), startsWith("!!!! Fibonacci !!!!\n"));
  }
}

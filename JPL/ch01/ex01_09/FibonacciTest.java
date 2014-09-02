package ch01.ex01_09;

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

    String[] lines = outputStream.toString()
      .split(System.getProperty("line.separator"));

    assertThat(lines[0], is("1"));
    assertThat(lines[1], is("1"));
    for (int i = 2; i < lines.length; ++i) {
      assertThat(Integer.parseInt(lines[i]),
                 is(Integer.parseInt(lines[i - 1]) +
                    Integer.parseInt(lines[i - 2])));
    }
  }
}

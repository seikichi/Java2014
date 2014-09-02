package ch01.ex01_07;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class ImprovedFibonacciTest {
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
  public void improvedFibonacciMain() {
    ImprovedFibonacci.main(new String[0]);
    String[] lines = outputStream.toString()
      .split(System.getProperty("line.separator"));
    for (int i = 0; i < lines.length; ++i) {
      assertThat(lines[i], startsWith((i + 1) + ": "));
    }
  }
}

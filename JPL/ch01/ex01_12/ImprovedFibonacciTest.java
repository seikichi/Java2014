package ch01.ex01_12;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ImprovedFibonacciTest {
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
  public void improvedFibonacciMain() {
    ImprovedFibonacci.main(new String[0]);
    String[] lines = outputStream.toString()
      .split(System.getProperty("line.separator"));

    for (int i = 0; i < lines.length; ++i) {
      assertThat(lines[i], startsWith((i + 1) + ": "));
    }

    for (int i = 2; i < lines.length; ++i) {
      assertThat(Integer.parseInt(lines[i].split(" ")[1]),
                 is(Integer.parseInt(lines[i - 1].split(" ")[1]) +
                    Integer.parseInt(lines[i - 2].split(" ")[1])));
    }
  }
}

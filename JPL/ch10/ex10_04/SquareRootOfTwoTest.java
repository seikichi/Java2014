package ch10.ex10_04;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SquareRootOfTwoTest {
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
  public void squareRootOfTwoMain() {
    SquareRootOfTwo.main(new String[0]);
    final String outputWithoutNewLines = outputStream
      .toString().replace(System.getProperty("line.separator"), "");
    assertThat(outputWithoutNewLines, startsWith("141421356"));
  }
}

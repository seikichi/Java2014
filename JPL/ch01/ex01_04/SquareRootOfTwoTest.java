package ch01.ex01_04;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class SquareRootOfTwoTest {
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
  public void squareRootOfTwoMain() {
    SquareRootOfTwo.main(new String[0]);
    assertThat(outputStream.toString(),
               is(equalToIgnoringWhiteSpace("1 4 1 4 2 1 3 5 6 2")));
  }
}

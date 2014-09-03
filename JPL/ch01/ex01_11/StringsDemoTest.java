package ch01.ex01_11;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class StringsDemoTest {
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
  public void stringsDemoMain() {
    StringsDemo.main(new String[0]);
    final String output = outputStream.toString();
    assertThat(output, allOf(containsString("KONDO"),
                             containsString("Seiichi"),
                             containsString("Ricoh")));
  }
}

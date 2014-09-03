package ch01.ex01_01;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class HelloWorldTest {
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
  public void helloWorldMain() {
    HelloWorld.main(new String[0]);

    final String outputLower = outputStream.toString().toLowerCase();
    assertThat(outputLower, allOf(containsString("hello"), containsString("world")));
  }
}

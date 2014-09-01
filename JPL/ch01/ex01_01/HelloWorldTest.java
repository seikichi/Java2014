package ch01.ex01_01;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class HelloWorldTest {
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
  public void helloWorldMain() {
    HelloWorld.main(new String[0]);
    assertThat(outputStream.toString(), is("Hello, world\n"));
  }
}

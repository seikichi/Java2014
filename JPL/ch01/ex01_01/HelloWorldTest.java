package ch01.ex01_01;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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
  public void testHelloWorld() {
    HelloWorld.main(new String[0]);
    assertThat(outputStream.toString(), is("Hello, world\n"));
  }
}

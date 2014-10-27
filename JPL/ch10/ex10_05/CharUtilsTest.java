package ch10.ex10_05;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.experimental.theories.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@RunWith(Theories.class)
public final class CharUtilsTest {
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

  static final class Data {
    public final char first, last;
    public final String output;
    Data(char first, char last, String output) {
      this.first = first;
      this.last = last;
      this.output = output;
    }
  }

  @DataPoints
  public static Data[] data = {
    new Data('a', 'b', "ab"),
    new Data('A', 'C', "ABC"),
    new Data('C', 'A', ""),
    new Data('A', 'A', "A"),
  };

  @Theory
  public void printBetween(Data data) {
    CharUtils.printBetween(data.first, data.last);
    assertThat(outputStream, hasToString(data.output + "\n"));
  }
}

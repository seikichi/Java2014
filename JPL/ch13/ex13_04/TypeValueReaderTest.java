package ch13.ex13_04;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.*;
import org.junit.experimental.theories.*;

@RunWith(Theories.class)
public final class TypeValueReaderTest {
  static class Data {
    public final String input;
    public final ArrayList<Object> output;
    Data(String input, ArrayList<Object> output) {
      this.input = input;
      this.output = output;
    }
  }

  @DataPoints
  public static Data[] data = {
    new Data("", new ArrayList<>()),
    new Data("Boolean true", new ArrayList<Object>() {{
      add(new Boolean("true"));
    }}),
    new Data("Boolean true\nInteger 10", new ArrayList<Object>() {{
      add(new Boolean("true"));
      add(new Integer("10"));
    }}),
    new Data("Boolean true\nInteger 10\nByte 5", new ArrayList<Object>() {{
      add(new Boolean("true"));
      add(new Integer("10"));
      add(new Byte("5"));
    }}),
    new Data("Short 1\nLong 2\nFloat 3\nDouble 4", new ArrayList<Object>() {{
      add(new Short("1"));
      add(new Long("2"));
      add(new Float("3"));
      add(new Double("4"));
    }}),
  };

  @Theory
  public void read(Data data) throws Exception {
    assertThat(TypeValueReader.read(data.input), is(data.output));
  }
}

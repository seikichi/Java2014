package ch20.ex20_04;

import java.io.*;
import java.util.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.*;
import org.junit.rules.*;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.*;
import org.junit.experimental.theories.*;

@RunWith(Theories.class)
public class LineReaderTest {
  static final class Data {
    public final String input;
    public final ArrayList<String> output;
    Data(String input, String[] output) {
      this.input = input;
      this.output = new ArrayList<>();
      for (String s : output) { this.output.add(s); }
    }
  }

  @DataPoints
  public static Data[] data = {
    new Data("", new String[]{}),
    new Data("aaa", new String[]{"aaa"}),
    new Data(String.format("aaa%nbbb"), new String[]{"aaa", "bbb"}),
    new Data(String.format("aaa%nbbb%n"), new String[]{"aaa", "bbb"}),
    new Data(String.format("%nbbb"), new String[]{"", "bbb"}),
    new Data(String.format("%nbbb%n"), new String[]{"", "bbb"}),
    new Data(String.format("%n%n"), new String[]{"", ""}),
    new Data(String.format("a%n%na%n"), new String[]{"a", "", "a"}),
    new Data(String.format("こんにちは%n世界%n"), new String[]{"こんにちは", "世界"}),
  };

  @Theory
  public void readLineは入力を改行区切りで読み込むこと(Data data) throws Exception {
    LineReader reader = new LineReader(new StringReader(data.input));

    ArrayList<String> lineList = new ArrayList<>();
    String line;
    while ((line = reader.readLine()) != null) {
      lineList.add(line);
    }

    assertThat(lineList, is(data.output));
  }
}

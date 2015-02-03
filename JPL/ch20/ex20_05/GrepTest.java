package ch20.ex20_05;

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
public class GrepTest {
  static final class Data {
    public String input;
    public String word;
    public String output;
    Data(String[] input, String word, String[] output) {
      this.input = "";
      for (String s : input) {
        this.input = this.input + String.format("%s%n", s);
      }
      this.word = word;
      this.output = "";
      for (String s : output) {
        this.output = this.output + String.format("%s%n", s);
      }
    }
  }

  @DataPoints
  public static Data[] data = {
    new Data(new String[]{}, "word", new String[]{}),
    new Data(new String[]{"!word!"}, "word",
             new String[]{"1: !word!"}),
    new Data(new String[]{"word", "wordword", "wordwordword"}, "word",
             new String[]{
               "1: word",
               "2: wordword",
               "3: wordwordword",
             }),
    new Data(new String[]{"ab", "bc", "ca"}, "a",
             new String[]{"1: ab", "3: ca"}),
  };

  @Theory
  public void grepは単語の検索と行の表示を行うこと(Data data) throws Exception {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);

    Grep.grep(new StringReader(data.input), data.word, "%d: %s", pw);

    assertThat(sw.toString(), is(data.output));
  }
}

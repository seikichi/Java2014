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
    public final String input;
    public final String word;
    public final String output;
    Data(String input, String word, String output) {
      this.input = input;
      this.word = word;
      this.output = output;
    }
  }

  @DataPoints
  public static Data[] data = {
    new Data("", "word", ""),
    new Data("ab\n" +
             "bc\n" +
             "ca",
             "a",
             "1: ab\n" +
             "3: ca\n"),
    new Data("aaaaa\n" +
             "bbbbb\n" +
             "cccccaaaaa\n" +
             "ddddd\n",
             "aaa",
             "1: aaaaa\n" +
             "3: cccccaaaaa\n"),
  };

  @Theory
  public void grepは単語の検索と行の表示を行うこと(Data data) throws Exception {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);

    Grep.grep(new StringReader(data.input), data.word, "%d: %s", pw);

    assertThat(sw.toString(), is(data.output));
  }
}

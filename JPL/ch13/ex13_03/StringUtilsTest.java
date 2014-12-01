package ch13.ex13_03;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.*;
import org.junit.experimental.theories.*;

@RunWith(Enclosed.class)
public class StringUtilsTest {

  @RunWith(Theories.class)
  public static class CountMatchesChar {
    static class Data {
      public final String str; public final int ch, count;
      Data(String str, int ch, int count) {
        this.str = str; this.ch = ch; this.count = count;
      }
    }

    @DataPoints
    public static Data[] data = {
      new Data(null, 'a', 0),
      new Data("abc", 'a', 1),
      new Data("aba", 'a', 2),
      new Data("aaa", 'a', 3),
      new Data("XXX", 'a', 0),
    };

    @Theory
    public void countMatches(Data data) throws Exception {
      assertThat(StringUtils.countMatches(data.str, data.ch), is(data.count));
    }
  }

  @RunWith(Theories.class)
  public static class CountMatchesString {
    static class Data {
      public final String str, target; public final int count;
      Data(String str, String target, int count) {
        this.str = str; this.target = target; this.count = count;
      }
    }

    @DataPoints
    public static Data[] data = {
      new Data(null, "a", 0),
      new Data("abc", "a", 1),
      new Data("aba", "a", 2),
      new Data("aaa", "a", 3),
      new Data("XXX", "a", 0),
      new Data("XXX", "XX", 2),
      new Data("Abc", "a", 0),
      new Data("abracadabra", "abra", 2),
    };

    @Theory
    public void countMatches(Data data) throws Exception {
      assertThat(StringUtils.countMatches(data.str, data.target), is(data.count));
    }
  }

  @RunWith(Theories.class)
  public static class DelimitedString {
    static class Data {
      public final String str;
      public final char start, end;
      public final String[] expected;
      Data(String str, char start, char end, String[] expected) {
        this.str = str;
        this.start = start;
        this.end = end;
        this.expected = expected;
      }
    }

    @DataPoints
    public static Data[] data = {
      new Data(null, '<', '>', new String[]{}),
      new Data("#include <stdio.h>", '<', '>', new String[]{"<stdio.h>"}),
      new Data("(+ (+ 1 2) 3)", '(', ')', new String[]{
          "(+ (+ 1 2)",
          "(+ (+ 1 2) 3)",
          "(+ 1 2)",
          "(+ 1 2) 3)",
        }),
      new Data("|a|bc|", '|', '|', new String[]{
          "|a|",
          "|a|bc|",
          "|bc|",
        }),
    };

    @Theory
    public void countMatches(Data data) throws Exception {
      assertThat(StringUtils.delimitedString(data.str, data.start, data.end), is(data.expected));
    }
  }
}

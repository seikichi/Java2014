package ch13.ex13_06;

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

  @RunWith(Theories.class)
  public static class StringFormat {
    static class Data {
      public final String input, output;
      Data(String input, String output) {
        this.input = input;
        this.output = output;
      }
    }

    @DataPoints
    public static Data[] data = {
      new Data("1000", "1,000"),
      new Data("123", "123"),
      new Data("123456789", "123,456,789"),
      new Data("22333", "22,333"),
    };

    @Theory
    public void formatNumber(Data data) throws Exception {
      assertThat(StringUtils.formatNumber(data.input), is(data.output));
    }
  }

  public static class StringFormatWithInvalidData {
    @Test(expected = IllegalArgumentException.class)
    public void formatNumber() throws Exception {
      StringUtils.formatNumber("AAAAA");
    }
  }

  @RunWith(Theories.class)
  public static class StringFormatWithDigitsAndDelimiter {
    static class Data {
      public final String input, delimiter, output;
      public final int digits;
      Data(String input, int digits, String delimiter, String output) {
        this.input = input;
        this.digits = digits;
        this.delimiter = delimiter;
        this.output = output;
      }
    }

    @DataPoints
    public static Data[] data = {
      new Data("1000", 3, ",", "1,000"),
      new Data("1000", 2, ",", "10,00"),
      new Data("1000", 1, ",", "1,0,0,0"),
      new Data("12345", 2, "!!", "1!!23!!45"),
      new Data("12345", 1, "0", "102030405"),
    };

    @Theory
    public void formatNumber(Data data) throws Exception {
      assertThat(StringUtils.formatNumber(data.input, data.digits, data.delimiter), is(data.output));
    }
  }
}

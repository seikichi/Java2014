package ch13.ex13_01;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.*;
import org.junit.experimental.theories.*;

@RunWith(Enclosed.class)
public class StringUtilsTest {

  @RunWith(Theories.class)
  public static class CountMatches {
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
}

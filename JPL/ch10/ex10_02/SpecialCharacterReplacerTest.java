package ch10.ex10_02;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.experimental.theories.*;

@RunWith(Theories.class)
public final class SpecialCharacterReplacerTest {
  static SpecialCharacterReplacer r = new SpecialCharacterReplacer();

  static final class Data {
    public final String input;
    public final String output;
    Data(String input, String output) {
      this.input = input;
      this.output = output;
    }
  }

  @DataPoints
  public static Data[] data = {
    new Data("Hello!\n", "Hello!\\n"),
    new Data("1\t2\t3", "1\\t2\\t3"),
    new Data("\bhoge", "\\bhoge"),
    new Data("\fhoge", "\\fhoge"),
    new Data("hoge\\fuga", "hoge\\\\fuga"),
    new Data("hoge\'fuga", "hoge\\\'fuga"),
    new Data("hoge\"fuga", "hoge\\\"fuga"),
  };

  @Theory
  public void replace(Data data) {
    assertThat(r.replace(data.input), is(data.output));
  }
}

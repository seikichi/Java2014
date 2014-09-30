package ch03.ex03_07;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ColorAttrTest {
  private ColorAttr attr;
  private static final String name = "hoge";
  private static final String color = "red";
  private static final String anotherName = "fuga";
  private static final String anotherColor = "blue";

  @Before
  public void setupColorAttrs() {
    attr = new ColorAttr(name, color);
  }

  @Test
  public void equalsReturnsTrueForSameNameAndColor() {
    assertThat(attr, is(new ColorAttr(name, color)));
  }

  @Test
  public void equalsReturnsTrueForDifferentNameOrColor() {
    assertThat(attr, is(not(new ColorAttr(anotherName, color))));
    assertThat(attr, is(not(new ColorAttr(name, anotherColor))));
  }

  @Test
  public void hashCodeReturnsSameHashValueForSameNameAndColor() {
    assertThat(attr.hashCode(), is(new ColorAttr(name, color).hashCode()));
  }

  @Test
  public void hashCodeReturnsSameHashValueForDifferentNameOrColor() {
    assertThat(attr.hashCode(), is(not(new ColorAttr(anotherName, color).hashCode())));
    assertThat(attr.hashCode(), is(not(new ColorAttr(name, anotherColor).hashCode())));
  }
}


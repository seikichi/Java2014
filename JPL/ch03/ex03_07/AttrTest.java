package ch03.ex03_07;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;

public class AttrTest {
  private static final String name = "hoge";
  private static final Object value = "fuga";
  private Attr attr = null;

  @Before
  public void prepareAttr() {
    attr = new Attr(name, value);
  }

  @Test
  public void getName() {
    assertThat(attr.getName(), is(name));
  }

  @Test
  public void getValue() {
    assertThat(attr.getValue(), is(value));
  }

  @Test
  public void setValueReturnsOldValue() {
    assertThat(attr.setValue("newValue!"), is(value));
  }

  @Test
  public void setValueReplaceOldValue() {
    final Object newValue = "newValue";
    attr.setValue(newValue);
    assertThat(attr.getValue(), is(newValue));
  }
}


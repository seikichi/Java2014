package ch11.ex11_03;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.*;

public class AttrTest {
  public final String name = "answer";
  public final Integer value = 42;
  public Attr<Integer> attr = null;

  @Before
  public void setup() {
    attr = new Attr<>(name, value);
  }

  @Test
  public void attrHasName() {
    assertThat(attr.getName(), is(name));
  }

  @Test
  public void attrHasValue() {
    assertThat(attr.getValue(), is(value));
  }

  @Test
  public void attrCanChangeValue() {
    attr.setValue(0);
    assertThat(attr.getValue(), is(0));
  }

  @Test
  public void attrReturnsOldValueWhenValueChanged() {
    assertThat(attr.setValue(0), is(value));
  }

  @Test
  public void attrToString() {
    assertThat(attr.toString(), startsWith(name));
  }

  @Test(expected = NullPointerException.class)
  public void ctorThrowsExceptionWhenNameIsNull() {
    new Attr(null);
  }
}

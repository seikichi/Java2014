package ch01.ex01_15;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class SimpleLookupTest {
  private Lookup lookup;

  @Before
  public void createSimpleLookup() {
    lookup = new SimpleLookup();
  }

  @Test
  public void findReturnsTheObjectIfNameExist() {
    lookup.add("A", 100);
    assertThat(lookup.find("A"), is(100));
  }

  @Test
  public void findReturnsNullIfNameIfNameNotExist() {
    lookup.add("A", 100);
    lookup.remove("A");
    assertThat(lookup.find("A"), is(nullValue()));
  }

  @Test
  public void addReturnsNullIfNameNotExist() {
    assertThat(lookup.add("A", 100), is(nullValue()));
  }

  @Test
  public void addOverWriteTheOldObjectIfNameExist() {
    lookup.add("A", 100);
    lookup.add("A", 200);
    assertThat(lookup.find("A"), is(200));
  }

  @Test
  public void addReturnsOldObjectIfNameExist() {
    lookup.add("A", 100);
    assertThat(lookup.add("A", 200), is(100));
  }

  @Test
  public void removeReturnsNullIfNameNotExist() {
    assertThat(lookup.remove("A"), is(nullValue()));
  }

  @Test
  public void removeReturnsOldObjectIfNameExist() {
    lookup.add("A", 100);
    assertThat(lookup.remove("A"), is(100));
  }
}

package ch16.ex16_01;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.*;
import org.junit.experimental.theories.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TypeDescTest {
  private ByteArrayOutputStream outputStream;

  @Before
  public void setUp() {
    outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));
  }

  @After
  public void tearDown() {
    System.setOut(null);
  }

  @Test
  public void mainはObjectクラスに関しては何も表示しないこと() throws Exception {
    TypeDesc.main(new String[] {"java.util.HashMap", "java.lang.String"});
    String expected = 
      "class java.util.HashMap<K, V>\n" +
      " implements java.util.Map<K, V>\n" +
      " implements java.lang.Cloneable\n" +
      " implements java.io.Serializable\n" +
      " extends java.util.AbstractMap<K, V>\n" +
      "  implements java.util.Map<K, V>\n" +
      "class java.lang.String\n" +
      " implements java.io.Serializable\n" +
      " implements java.lang.Comparable<T>\n" +
      " implements java.lang.CharSequence\n";
    assertThat(outputStream.toString(), is(expected));
  }
}

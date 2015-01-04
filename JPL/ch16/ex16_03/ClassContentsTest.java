package ch16.ex16_03;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.*;
import org.junit.experimental.theories.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ClassContentsTest {
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
  public void 宣言されているメンバーまたは継承されているpublicなメンバーを表示すること() throws Exception {
    ClassContents.main(new String[]{"ch16.ex16_03.Attr"});
    String expected =
      "class ch16.ex16_03.Attr\n" +
      " private final String ch16.ex16_03.Attr.name\n" +
      " private Object ch16.ex16_03.Attr.value\n" +
      " public ch16.ex16_03.Attr(String)\n" +
      " public ch16.ex16_03.Attr(String,Object)\n" +
      " public String ch16.ex16_03.Attr.toString()\n" +
      " public final String ch16.ex16_03.Attr.getName()\n" +
      " public final Object ch16.ex16_03.Attr.getValue()\n" +
      " public Object ch16.ex16_03.Attr.setValue(Object)\n";
    assertThat(outputStream.toString(), is(expected));
  }
}

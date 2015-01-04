package ch16.ex16_05;

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

  public static class TestType {
    @Deprecated
    public void oldMethod() {}

    public void newMethod() {}
  }

  @Test
  public void 宣言されているメンバーまたは継承されているpublicなメンバーをアノテーション情報と共に表示すること() throws Exception {
    ClassContents.main(new String[]{"ch16.ex16_05.ClassContentsTest$TestType"});
    String expected =
      "class ch16.ex16_05.ClassContentsTest$TestType\n" +
      " public ch16.ex16_05.ClassContentsTest$TestType()\n" +
      " public void ch16.ex16_05.ClassContentsTest$TestType.newMethod()\n" +
      " @Deprecated()\n" +
      " public void ch16.ex16_05.ClassContentsTest$TestType.oldMethod()\n";
    assertThat(outputStream.toString(), is(expected));
  }
}

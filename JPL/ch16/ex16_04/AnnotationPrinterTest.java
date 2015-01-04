package ch16.ex16_04;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.*;
import org.junit.experimental.theories.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.annotation.*;

public class AnnotationPrinterTest {
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

  @Retention(RetentionPolicy.RUNTIME)
  @interface NameAndAgeAnnotation {
    String name();
    int age();
  }

  @Retention(RetentionPolicy.RUNTIME)
  @interface Hoge {
    String value();
  }

  @NameAndAgeAnnotation(name="seikichi", age=25)
  @Hoge("Hello, world!")
  public static class TestType {}

  @Test
  public void すべてのアノテーションが表示されること() throws Exception {
    AnnotationPrinter.main(new String[]{"ch16.ex16_04.AnnotationPrinterTest$TestType"});
    String expected =
      "@ch16.ex16_04.AnnotationPrinterTest$NameAndAgeAnnotation(name=seikichi, age=25)\n" +
      "@ch16.ex16_04.AnnotationPrinterTest$Hoge(value=Hello, world!)\n";
    assertThat(outputStream.toString(), is(expected));
  }
}

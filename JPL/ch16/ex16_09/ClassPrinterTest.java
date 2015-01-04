package ch16.ex16_09;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.*;
import org.junit.experimental.theories.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.annotation.*;

class Test1 {}
class Test2 { public static void m1() throws Exception {} }
class Test3 { final java.util.ArrayList<Integer> list = null; }
class Test4 { Test4(int x, String hoge) throws Exception {} }
class Test5<K> { K k; }
class Test6 extends Test5<java.util.Set<Integer>> {}
@Annotation1 class Test7 {
  @Annotation1 public Test7() {}
  @Annotation1 public int value;
  @Annotation1 public void m() {};
}
class Test8 {
  public static <T> T id(T t) { return t; }
  public <T> void m(T t) {  }
}

@Retention(RetentionPolicy.RUNTIME)
@interface Annotation1 { }

@RunWith(Theories.class)
public class ClassPrinterTest {
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

  private static String join(String... lines) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < lines.length; i++) {
      builder.append(lines[i]);
      builder.append("\n");
    }
    return builder.toString();
  }

  static class Data {
    Class<?> input;
    String output;
    Data(Class<?> input, String output) { this.input = input; this.output = output; }
  }

  @DataPoints
  public static Data[] data = {
    new Data(Test1.class,
             join("package ch16.ex16_09;",
                  "",
                  "class Test1 {",
                  "  Test1();",
                  "}")),
    new Data(Test2.class,
             join("package ch16.ex16_09;",
                  "",
                  "class Test2 {",
                  "  Test2();",
                  "  public static void m1() throws Exception;",
                  "}")),
    new Data(Test3.class,
             join("package ch16.ex16_09;",
                  "",
                  "class Test3 {",
                  "  Test3();",
                  "  final java.util.ArrayList<Integer> list;",
                  "}")),
    new Data(Test4.class,
             join("package ch16.ex16_09;",
                  "",
                  "class Test4 {",
                  "  Test4(int,String) throws Exception;",
                  "}")),
    new Data(Test5.class,
             join("package ch16.ex16_09;",
                  "",
                  "class Test5<K> {",
                  "  Test5();",
                  "  K k;",
                  "}")),
    new Data(Test6.class,
             join("package ch16.ex16_09;",
                  "",
                  "class Test6",
                  "    extends Test5<java.util.Set<Integer>> {",
                  "  Test6();",
                  "}")),
    new Data(Test7.class,
             join("package ch16.ex16_09;",
                  "",
                  "@Annotation1()",
                  "class Test7 {",
                  "  @Annotation1()",
                  "  public Test7();",
                  "  @Annotation1()",
                  "  public int value;",
                  "  @Annotation1()",
                  "  public void m();",
                  "}")),
    new Data(Test8.class,
             join("package ch16.ex16_09;",
                  "",
                  "class Test8 {",
                  "  Test8();",
                  "  public <T> void m(T);",
                  "  public static <T> T id(T);",
                  "}")),
  };

  @Theory
  public void printTest(Data data) {
    new ClassPrinter(data.input).print();
    assertThat(outputStream.toString(), is(data.output));
  }
}

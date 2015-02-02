package ch17.ex17_02;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.*;
import org.junit.rules.*;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.*;
import org.junit.experimental.theories.*;

public class DataHandlerTest {
  @Rule
  public TemporaryFolder folder = new TemporaryFolder();

  private File file;

  @Before
  public void setup() throws Exception {
    file = folder.newFile("file.txt");
    PrintWriter writer = new PrintWriter(new FileWriter(file, true));
    writer.println("Hello, world!");
    writer.close();
  }

  @Test
  public void readFileがファイル内容を読み取って返すこと() throws Exception {
    byte[] expected = "Hello, world!\n".getBytes("UTF-8");
    byte[] actual = new DataHandler().readFile(file);

    assertThat(actual, is(expected));
  }

  @Test
  public void 前回の結果の参照が残っている場合にreadFileがキャッシュされた値を返すこと() throws Exception {
    DataHandler handler = new DataHandler();
    byte[] first = handler.readFile(file);
    byte[] second = handler.readFile(file);

    int firstHashCode = first.hashCode();
    int secondHashCode = second.hashCode();
    assertThat(firstHashCode, is(secondHashCode));
  }

  @Test
  public void 前回の結果の参照が残っていない場合はreadFileはキャッシュされた値を返さないこと() throws Exception {
    DataHandler handler = new DataHandler();
    byte[] first = handler.readFile(file);
    int firstHashCode = first.hashCode();

    first = null;
    System.runFinalization();
    System.gc();

    byte[] second = handler.readFile(file);
    int secondHashCode = second.hashCode();

    assertThat(firstHashCode, is(not(secondHashCode)));
  }
}

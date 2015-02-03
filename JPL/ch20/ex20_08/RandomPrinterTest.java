package ch20.ex20_08;

import java.io.*;
import java.util.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.*;
import org.junit.rules.*;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.*;
import org.junit.experimental.theories.*;

public class RandomPrinterTest {
  @Rule
  public TemporaryFolder testFolder = new TemporaryFolder();

  private File file;

  @Before
  public void setUp() throws Exception {
    file = testFolder.newFile("file.txt");
    try (PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
      w.println("%% ほげほげ");
      w.println("ふがふが");
      w.println("%% ぱやぱや");
      w.println("もにょもにょ");
      w.println("ひええ");
    }
  }

  @Test
  public void エントリー数を適切に取得できていること() throws Exception {
    RandomPrinter sut = new RandomPrinter(file);
    assertThat(sut.getNumOfEntries(), is(2));
  }

  @Test
  public void エントリーを指定して出力できること_1番目のエントリ() throws Exception {
    RandomPrinter sut = new RandomPrinter(file);
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    sut.printEntry(0, pw);

    assertThat(sw.toString(), is(String.format("%%%% ほげほげ%nふがふが%n")));
  }

  @Test
  public void エントリーを指定して出力できること_2番目のエントリ() throws Exception {
    RandomPrinter sut = new RandomPrinter(file);
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    sut.printEntry(1, pw);

    assertThat(sw.toString(), is(String.format("%%%% ぱやぱや%nもにょもにょ%nひええ%n")));
  }
}

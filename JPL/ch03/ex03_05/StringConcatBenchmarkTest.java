package ch03.ex03_05;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class StringConcatBenchmarkTest {
  private StringConcatBenchmark benchmark;

  @Before
  public void prepareBanchmark() {
    benchmark = new StringConcatBenchmark();
  }

  @Test
  public void repeatReturnsPositiveValue() {
    final long time = benchmark.repeat(100);
    assertThat(time, is(greaterThanOrEqualTo(0L)));
  }
}

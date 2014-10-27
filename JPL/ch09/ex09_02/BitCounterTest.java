package ch09.ex09_02;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.experimental.theories.*;

@RunWith(Theories.class)
public final class BitCounterTest {
  @DataPoint public static BitCounter naiveBitCounter = new NaiveBitCounter();
  @DataPoint public static BitCounter slowBitCounter = new SlowBitCounter();
  @DataPoint public static BitCounter builtinBitCounter = new BuiltinBitCounter();
  @DataPoint public static BitCounter optimizedBitCounter = new OptimizedBitCounter();

  @Theory
  public void bitCountReturns0For0b0(BitCounter counter) {
    assertThat(counter.bitCount(0b0), is(0));
  }

  @Theory
  public void bitCountReturns2For0b0110(BitCounter counter) {
    assertThat(counter.bitCount(0b0110), is(2));
  }

  @Theory
  public void bitCountReturns4For0b11011(BitCounter counter) {
    assertThat(counter.bitCount(0b011011), is(4));
  }

  @Theory
  public void bitCountReturns1For0b10000(BitCounter counter) {
    assertThat(counter.bitCount(0b10000), is(1));
  }
}

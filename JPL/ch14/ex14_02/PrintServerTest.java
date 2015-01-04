package ch14.ex14_02;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.*;
import org.junit.experimental.theories.*;

public class PrintServerTest {
  private PrintServer sut;

  @Before public void setUp() { sut = new PrintServer(); }
  @After public void tearDown() { sut.stop(); }

  @Test(expected = IllegalThreadStateException.class)
  public void runを呼び出すと例外をスローすること() throws Exception {
    sut.run();
  }
}

package ch14.ex14_03;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.*;
import org.junit.experimental.theories.*;

import java.util.Random;

public class ValueTest {
  @Test
  public void 複数スレッドからaddを呼び出しても期待通りに動作すること() throws Exception {
    // Setup
    Value value = new Value();
    Thread[] threads = new Thread[2048];
    Random random = new Random();

    // Exercise: 2048 スレッドから add(1) を呼び出す
    for (int i = 0; i < threads.length; i++) {
      threads[i] = new Thread(() -> {
        try {
          Thread.sleep(random.nextInt(50));
        } catch (InterruptedException e) { return; }
        value.add(1);
      });
      threads[i].start();
    }
    for (Thread t : threads) { t.join(); }

    // Verify
    assertThat(value.get(), is(threads.length));
  }
}

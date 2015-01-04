package ch14.ex14_05;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.*;
import org.junit.experimental.theories.*;

import java.util.Random;

public class StaticValueTest {
  private int oldValue;
  @Before public void setUp() { oldValue = StaticValue.get(); }
  @After public void tearDown() { StaticValue.set(oldValue); }

  @Test
  public void 複数スレッドからaddとsubを呼び出しても期待通りに動作すること() throws Exception {
    // Setup
    Thread[] addThreads = new Thread[2000];
    Thread[] subThreads = new Thread[1999];
    Random random = new Random();

    // Exercise: 2000 スレッドから add(1) を，1999 スレッドからは sub(1) を呼び出す
    for (int i = 0; i < addThreads.length; i++) {
      addThreads[i] = new Thread(() -> {
        try { Thread.sleep(random.nextInt(50)); } catch (InterruptedException e) { return; }
        StaticValue.add(1);
      });
      addThreads[i].start();
    }
    for (int i = 0; i < subThreads.length; i++) {
      subThreads[i] = new Thread(() -> {
        try { Thread.sleep(random.nextInt(50)); } catch (InterruptedException e) { return; }
        StaticValue.sub(1);
      });
      subThreads[i].start();
    }
    for (Thread t : addThreads) { t.join(); }
    for (Thread t : subThreads) { t.join(); }

    // Verify
    assertThat(StaticValue.get(), is(1));
  }

  @Test
  public void 複数スレッドからaddを呼び出しても期待通りに動作すること() throws Exception {
    // Setup
    Thread[] threads = new Thread[2048];
    Random random = new Random();

    // Exercise: 2048 スレッドから add(1) を呼び出す
    for (int i = 0; i < threads.length; i++) {
      threads[i] = new Thread(() -> {
        try {
          Thread.sleep(random.nextInt(50));
        } catch (InterruptedException e) { return; }
        StaticValue.add(1);
      });
      threads[i].start();
    }
    for (Thread t : threads) { t.join(); }

    // Verify
    assertThat(StaticValue.get(), is(threads.length));
  }
}

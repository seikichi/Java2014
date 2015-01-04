package ch14.ex14_06;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.*;
import org.junit.experimental.theories.*;

import java.time.Duration;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@RunWith(Enclosed.class)
public class PrinterTest {
  public static class createMessagePrinterThreadが返すスレッド {
    private ByteArrayOutputStream outputStream;

    @Before
    public void setUpStreams() {
      outputStream = new ByteArrayOutputStream();
      System.setOut(new PrintStream(outputStream));
    }
    @After
    public void cleanUpStreams() {
      System.setOut(null);
    }

    @Test
    public void 通知を受け取り一定間隔でメッセージを表示すること() throws Exception {
      Object lock = new Object();
      String message = "Hello!";
      int messageIntervalMillis = 50;
      Duration duration = Duration.ofMillis(messageIntervalMillis);
      Thread thread = Printer.createMessagePrinterThread(message, duration, lock);

      // Exercise
      thread.start();
      for (int i = 0; i < 10; i++ ) { synchronized (lock) { lock.notifyAll();  } }
      Thread.sleep(10 + messageIntervalMillis);
      for (int i = 0; i < 10; i++ ) { synchronized (lock) { lock.notifyAll();  } }
      Thread.sleep(10 + messageIntervalMillis);

      // Verify
      assertThat(outputStream.toString(), is(String.format("%s\n", message)));
      // Teardown
      thread.interrupt();
    }
  }

  public static class createTimePrinterThreadが返すスレッド {
    private ByteArrayOutputStream outputStream;

    @Before
    public void setUpStreams() {
      outputStream = new ByteArrayOutputStream();
      System.setOut(new PrintStream(outputStream));
    }
    @After
    public void cleanUpStreams() {
      System.setOut(null);
    }

    @Test
    public void 一定間隔毎に通知を行うこと() throws Exception {
      // Setup: wait を2回行って終了するスレッドを3つ作成
      int notifyIntervalMillis = 50;
      Object lock = new Object();
      Thread thread = Printer.createTimePrinterThread(notifyIntervalMillis, lock);

      Thread[] notifiedThreads = new Thread[3];
      for (int i = 0; i < notifiedThreads.length; i++) {
        notifiedThreads[i] = new Thread(() -> {
          synchronized(lock) {
            try { lock.wait(); lock.wait(); } catch (InterruptedException e) { }
          }
        });
        notifiedThreads[i].start();
      }

      // Exercise: 通知の間隔 (50 ms.) の3倍 sleep
      thread.start();
      Thread.sleep(3 * notifyIntervalMillis);

      // Verify
      for (int i = 0; i < notifiedThreads.length; i++) {
        assertThat(notifiedThreads[i].isAlive(), is(false));
      }

      // Teardown
      thread.interrupt();
      for (int i = 0; i < notifiedThreads.length; i++) {
        notifiedThreads[i].interrupt();
      }
    }
  }
}

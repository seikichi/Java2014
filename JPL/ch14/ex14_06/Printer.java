package ch14.ex14_06;

import java.time.Instant;
import java.time.Duration;

public final class Printer {
  public static Thread createMessagePrinterThread(String message,
                                                  Duration interval,
                                                  Object lock) {
    return new Thread(() -> {
      Instant time = Instant.now();
      for (;;) {
        synchronized (lock) {
          while (Duration.between(time, Instant.now()).compareTo(interval) < 0) {
            try {
              lock.wait();
            } catch (InterruptedException e) {
              return;
            }
          }
        }
        time = Instant.now();
        System.out.println(message);
       }
    });
  }

  public static Thread createTimePrinterThread(int notifyIntervalMillis,
                                               Object lock) {
    return new Thread(() -> {
      Instant startTime = Instant.now();
      for (;;) {
        synchronized (lock) {
          try {
            lock.wait(notifyIntervalMillis);
          } catch (InterruptedException e) {
            return;
          }
          lock.notifyAll();
        }
        Duration elapsed = Duration.between(startTime, Instant.now());
        System.out.printf("%s sec. elapsed.\n", elapsed.getSeconds());
      }
    });
  }

  public static void main(String[] args) {
    Object lock = new Object();
    createTimePrinterThread(1000, lock).start();
    createMessagePrinterThread("Hello! (from 15 sec. interval thread)",
                               Duration.ofSeconds(15), lock).start();
    createMessagePrinterThread("Aloha! (from 7 sec. interval thread)",
                               Duration.ofSeconds(7), lock).start();
  }
}

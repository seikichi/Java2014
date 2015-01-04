package ch14.ex14_09;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.*;
import org.junit.experimental.theories.*;

import java.time.Duration;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ThreadHierarchyPrinterTest {
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
  public void 定期的にスレッドグループとスレッドの階層を表示すること() throws Exception {
    ThreadGroup group1 = new ThreadGroup("group1");
    ThreadGroup group2 = new ThreadGroup(group1, "group2");
    ThreadGroup group3 = new ThreadGroup(group1, "group3");
    ThreadGroup group4 = new ThreadGroup(group3, "group4");

    Thread thread1 = new Thread(group1, () -> { try { Thread.sleep(500); } catch (InterruptedException e) { } },
                                "thread1");
    Thread thread2 = new Thread(group1, () -> { try { Thread.sleep(500); } catch (InterruptedException e) { } },
                                "thread2");
    Thread thread3 = new Thread(group2, () -> { try { Thread.sleep(500); } catch (InterruptedException e) { } },
                                "thread3");
    Thread thread4 = new Thread(group4, () -> { try { Thread.sleep(500); } catch (InterruptedException e) { } },
                                "thread4");
    thread1.start();
    thread2.start();
    thread3.start();
    thread4.start();

    Thread thread = ThreadHierarchyPrinter.create(group1, 100);
    thread.start();
    Thread.sleep(120);

    String expected =
      "ThreadGroup(name=\"group1\")\n" +
      "  Thread(name=\"thread1\")\n" +
      "  Thread(name=\"thread2\")\n" + 
      "  ThreadGroup(name=\"group2\")\n" +
      "    Thread(name=\"thread3\")\n" +
      "  ThreadGroup(name=\"group3\")\n" +
      "    ThreadGroup(name=\"group4\")\n" +
      "      Thread(name=\"thread4\")\n";
    assertThat(outputStream.toString(), is(expected));
    thread.interrupt();
    group1.interrupt();
  }
}

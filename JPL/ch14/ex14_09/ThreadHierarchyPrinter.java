package ch14.ex14_09;

import java.util.Arrays;
import java.time.Duration;

public final class ThreadHierarchyPrinter {
  Thread thread;

  private ThreadHierarchyPrinter(ThreadGroup group, int intervalMillis) {
    thread = new Thread(() -> {
      for (;;) {
        try {
          Thread.sleep(intervalMillis);
        } catch (InterruptedException e) { return; }
        print(group, 0);
      }
    });
  }

  private void indent(int depth) {
    for (int i = 0; i < depth; i++) { System.out.print(" "); }
  }

  private void print(ThreadGroup group, int indentDepth) {
    indent(indentDepth);
    System.out.println(String.format("ThreadGroup(name=\"%s\")", group.getName()));
    Thread[] threads = new Thread[group.activeCount()];
    ThreadGroup[] groups = new ThreadGroup[group.activeGroupCount()];

    int threadNum = group.enumerate(threads, false);
    int groupNum = group.enumerate(groups, false);

    threads = Arrays.copyOfRange(threads, 0, threadNum);
    groups = Arrays.copyOfRange(groups, 0, groupNum);

    Arrays.sort(threads, (lhs, rhs) -> lhs.getName().compareTo(rhs.getName()));
    Arrays.sort(groups, (lhs, rhs) -> lhs.getName().compareTo(rhs.getName()));

    for (Thread t : threads) {
      indent(indentDepth + 2);
      System.out.println(String.format("Thread(name=\"%s\")", t.getName()));
    }

    for (ThreadGroup g : groups) {
      print(g, indentDepth + 2);
    }
  }

  public static Thread create(ThreadGroup group, int intervalMillis) {
    ThreadHierarchyPrinter printer = new ThreadHierarchyPrinter(group, intervalMillis);
    return printer.thread;
  }

  public static void main(String[] args) {
    ThreadGroup group = Thread.currentThread().getThreadGroup();
    Thread thread = ThreadHierarchyPrinter.create(group, 1000);
    thread.start();
  }
}

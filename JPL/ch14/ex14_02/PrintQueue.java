package ch14.ex14_02;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public final class PrintQueue {
  private BlockingQueue<PrintJob> queue = new LinkedBlockingQueue<PrintJob>();

  public PrintJob remove() throws InterruptedException {
    return queue.take();
  }
  public void add(PrintJob job) { queue.add(job); }
}

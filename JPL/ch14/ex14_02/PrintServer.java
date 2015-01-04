package ch14.ex14_02;

class PrintServer implements Runnable {
  private final Thread worker;
  private final PrintQueue requests = new PrintQueue();

  public PrintServer() {
    worker = new Thread(this);
    worker.start();
  }
  public void print(PrintJob job) {
    requests.add(job);
  }
  public void run() {
    if (!Thread.currentThread().equals(worker)) {
      throw new IllegalThreadStateException("PrintServer.run was " +
                                            "called from the invalid thread");
    }
    for (;;) {
      try {
        realPrint(requests.remove());
      } catch (InterruptedException e) {
        return;
      }
    }
  }
  private void realPrint(PrintJob job) {
    System.out.printf("process PrintJob: %s\n", job);
  }
  public void stop() {
    worker.interrupt();
  }

  public static void main(String[] args) {
    PrintServer server = new PrintServer();
    server.print(new PrintJob("test job 01"));
    // server.run();
    server.stop();
  }
}

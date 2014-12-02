public final class DigitalClock implements Runnable {
  public static void main(String[] args) {
    new Thread(new DigitalClock()).start();
  }

  @Override public final void run() {
    DigitalClockPresenter presenter = new DigitalClockPresenter();
    while (true) {
      presenter.repaint();
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e){
        break;
      }
    }
  }
}

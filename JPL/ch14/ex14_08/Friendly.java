package ch14.ex14_08;

class Friendly {
  private Friendly partner;
  private String name;

  public Friendly(String name) {
    this.name = name;
  }

  public synchronized void hug() {
    System.out.println(Thread.currentThread().getName() +
                       " in " + name + ".hug() trying to invoke " +
                       partner.name + ".hugBack()");
    partner.hugBack();
  }

  private synchronized void hugBack() {
    System.out.println(Thread.currentThread().getName() +
                       " in " + name + ".hugBack()");
  }

  public void becomeFriend(Friendly partner) {
    this.partner = partner;
  }

  public static void main(String[] args) {
    final Friendly jareth = new Friendly("jareth");
    final Friendly cory = new Friendly("cory");

    jareth.becomeFriend(cory);
    cory.becomeFriend(jareth);

    new Thread(() -> { jareth.hug(); }, "Thread1").start();
    new Thread(() -> { cory.hug(); }, "Thread2").start();
  }
}

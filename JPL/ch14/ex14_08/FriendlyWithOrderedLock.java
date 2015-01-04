package ch14.ex14_08;

class FriendlyWithOrderedLock {
  private FriendlyWithOrderedLock partner;
  private String name;

  public FriendlyWithOrderedLock(String name) {
    this.name = name;
  }

  public void hug() {
    FriendlyWithOrderedLock first = this, second = partner;
    if (this.name.compareTo(second.name) > 0) {
      first = partner;
      second = this;
    }

    synchronized (first) {
      synchronized (second) {
        System.out.println(Thread.currentThread().getName() +
                           " in " + name + ".hug() trying to invoke " +
                           partner.name + ".hugBack()");
        partner.hugBack();
      }
    }
  }

  private void hugBack() {
    System.out.println(Thread.currentThread().getName() +
                       " in " + name + ".hugBack()");
  }

  public void becomeFriend(FriendlyWithOrderedLock partner) {
    this.partner = partner;
  }

  public static void main(String[] args) {
    final FriendlyWithOrderedLock jareth = new FriendlyWithOrderedLock("jareth");
    final FriendlyWithOrderedLock cory = new FriendlyWithOrderedLock("cory");

    jareth.becomeFriend(cory);
    cory.becomeFriend(jareth);

    new Thread(() -> { jareth.hug(); }, "Thread1").start();
    new Thread(() -> { cory.hug(); }, "Thread2").start();
  }
}

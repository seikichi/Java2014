package ch05.ex05_02;

import java.util.LinkedList;
import java.util.Queue;

public final class BankAccount {
  private final long number;
  private long balance;
  private Queue<Action> actions;

  public static class Action {
    private final String action;
    private final long amount;

    Action(final String action, long amount) {
      this.action = action;
      this.amount = amount;
    }

    public String getAction() { return action; }
    public long getAmount() { return amount; }

    public String toString() {
      return "BankAccount.Action(action=" + action +
        ", amount=" + amount + ")";
    }
  }

  public static class History {
    private int index;
    private Action[] actions;

    public Action next() {
      if (index >= actions.length) { return null; }
      return actions[index++];
    }

    public History(final Action[] actions) {
      this.index = 0;
      this.actions = actions;
    }
  }

  private void addAction(final Action action) {
    actions.add(action);
    while (actions.size() > 10) {
      actions.poll();
    }
  }

  public void deposit(long amount) {
    balance += amount;
    addAction(new Action("deposit", amount));
  }

  public void withdraw(long amount) {
    balance -= amount;
    addAction(new Action("withdraw", amount));
  }

  public History history() {
    return new History((Action[]) actions.toArray());
  }

  public BankAccount(long number) {
    this.number = number;
    this.actions = new LinkedList<Action>();
  }

  public BankAccount(long number, long balance) {
    this(number);
    this.balance = balance;
  }
}


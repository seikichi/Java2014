package ch05.ex05_02;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BankAccountTest {
  private static final long number = 1234567890;
  private BankAccount bankAccount;

  @Before
  public void setUpBankAccount() {
    bankAccount = new BankAccount(number);
  }

  @Test
  public void getNumber() {
    assertThat(bankAccount.getNumber(), is(number));
  }

  @Test
  public void getBalanceReturnsZeroAtFirst() {
    assertThat(bankAccount.getBalance(), is(0L));
  }

  @Test
  public void depositIncreseAmount() {
    final long amount = 100;
    bankAccount.deposit(amount);
    assertThat(bankAccount.getBalance(), is(amount));
  }

  @Test
  public void withdrawDecreseAmount() {
    final long amount = 100;
    bankAccount.withdraw(amount);
    assertThat(bankAccount.getBalance(), is(-amount));
  }

  @Test
  public void historyWithEmptyAction() {
    BankAccount.History history = bankAccount.history();
    assertThat(history.next(), is(nullValue()));
  }

  @Test
  public void historyWithMultipleActions() {
    bankAccount.deposit(100);
    bankAccount.withdraw(50);
    BankAccount.History history = bankAccount.history();
    BankAccount.Action a1 = history.next();
    BankAccount.Action a2 = history.next();
    BankAccount.Action a3 = history.next();
    assertThat(a1.getAction(), is("deposit"));
    assertThat(a1.getAmount(), is(100L));
    assertThat(a2.getAction(), is("withdraw"));
    assertThat(a2.getAmount(), is(50L));
    assertThat(a3, is(nullValue()));
  }

  @Test
  public void historyNextRetunrsTenActionsAtMost() {
    for (int i = 0; i < 20; i++) {
      bankAccount.deposit(100);
    }
    BankAccount.History history = bankAccount.history();
    for (int i = 0; i < 10; i++) {
      assertThat(history.next(), is(not(nullValue())));
    }
    assertThat(history.next(), is(nullValue()));
  }
}

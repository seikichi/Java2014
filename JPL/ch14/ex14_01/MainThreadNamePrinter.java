package ch14.ex14_01;

public final class MainThreadNamePrinter {
  public static void main(String[] args) {
    System.out.println(Thread.currentThread().getName());
  }
}

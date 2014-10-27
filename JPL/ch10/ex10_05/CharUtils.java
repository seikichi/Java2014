package ch10.ex10_05;

public final class CharUtils {
  public static void printBetween(char first, char last) {
    for (char c = first; c <= last; c++) {
      System.out.print(c);
    }
    System.out.println("");
  }
}

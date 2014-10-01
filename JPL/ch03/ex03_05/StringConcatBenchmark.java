package ch03.ex03_05;

public class StringConcatBenchmark extends Benchmark {
  private String string = "";

  void benchmark() {
    string = string + "a";
  }

  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Usage: java StringConcatBenchmark [count]%n");
      return;
    }
    final int count = Integer.parseInt(args[0]);
    final long time = new StringConcatBenchmark().repeat(count);
    System.out.printf("%d methods in %d nanoseconds.%n", count, time);
  }
}

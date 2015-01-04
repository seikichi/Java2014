package ch14.ex14_02;

public final class PrintJob {
  private final String name;

  public PrintJob(String name) { this.name = name; }
  public String getName() { return this.name; }

  @Override
  public String toString() {
    return String.format("PrintJob(name=%s)",this.name);
  }
}

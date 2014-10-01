package ch04.ex04_02;

abstract class SortHarness<T extends Comparable<T>> {
  private T[] values;
  private final SortMetrics curMetrics = new SortMetrics();

  public final SortMetrics sort(T[] data) {
    values = data;
    curMetrics.init();
    doSort();
    return getMetrics();
  }

  public final SortMetrics getMetrics() {
    return curMetrics.clone();
  }

  protected final int getDataLength() {
    return values.length;
  }

  protected final int compare(int i, int j) {
    curMetrics.compareCnt++;
    T c1 = values[i];
    T c2 = values[j];
    return c1.compareTo(c2);
  }

  protected final void swap(int i, int j) {
    curMetrics.swapCnt++;
    T tmp = values[i];
    values[i] = values[j];
    values[j] = tmp;
  }
  protected abstract void doSort();
}

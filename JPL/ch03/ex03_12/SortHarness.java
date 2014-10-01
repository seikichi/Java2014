package ch03.ex03_12;

abstract class SortHarness {
  private Comparable[] values;
  private final SortMetrics curMetrics = new SortMetrics();

  public final SortMetrics sort(Comparable[] data) {
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
    Comparable c1 = values[i];
    Comparable c2 = values[j];
    return c1.compareTo(c2);
  }

  protected final void swap(int i, int j) {
    curMetrics.swapCnt++;
    Comparable tmp = values[i];
    values[i] = values[j];
    values[j] = tmp;
  }
  protected abstract void doSort();
}

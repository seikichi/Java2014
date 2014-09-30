package ch03.ex03_11;

public class SimpleSortDouble extends SortDouble {
  protected void doSort() {
    for (int i = 0; i < getDataLength(); i++) {
      for (int j = i + 1; j < getDataLength(); j++) {
        if (compare(i, j) > 0) { swap(i, j); }
      }
    }
  }

  public static void main(String[] args) {
    double[] testData = {0.3, 1.3e-2, 7.9, 3.17};
    SortDouble bsort = new SimpleSortDouble();
    SortMetrics metrics = bsort.sort(testData);
    System.out.println("Metrics:" + metrics);
    for (int i = 0; i < testData.length; i++) {
      System.out.println("\t" + testData[i]);
    }
  }
}

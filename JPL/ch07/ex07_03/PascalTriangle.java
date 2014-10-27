package ch07.ex07_03;

public final class PascalTriangle {
  public static void main(String[] args) {
    new PascalTriangle(6).print();
    new PascalTriangle(12).print();
  }

  private int[][] triangle;

  public PascalTriangle(int size) {
    triangle = new int[size][];
    for (int i = 0; i < size; i++) {
      triangle[i] = new int[i + 1];

      if (i == 0) {
        triangle[0][0] = 1;
        continue;
      }

      for (int j = 0; j <= i; j++) {
        triangle[i][j] = 0;
        if (j - 1 >= 0) { triangle[i][j] += triangle[i-1][j-1]; }
        if (j < i) { triangle[i][j] += triangle[i-1][j]; }
      }
    }
  }

  private void print() {
    if (triangle.length == 0) { return; }

    int maxLen = 1;
    for (int v : triangle[triangle.length - 1]) {
      maxLen = Math.max(maxLen, String.valueOf(v).length());
    }

    int width = maxLen % 2 == 1 ? maxLen : maxLen + 1;
    String format = "%" + width + "d";
    for (int i = 0; i < triangle.length; i++) {
      int row_offset = (1 + width / 2) * (triangle.length - i - 1);
      for (int j = 0; j < row_offset; j++) { System.out.printf(" "); }
      for (int j = 0; j < triangle[i].length; j++) {
        if (j != 0) { System.out.printf(" "); }
        System.out.printf(format, triangle[i][j]);
      }
      System.out.println();
    }
  }
}

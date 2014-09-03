package ch01.ex01_08;

public class Point {
  public static final Point origin = new Point();

  public double x, y;

  public void clear() {
    x = 0.0;
    y = 0.0;
  }

  public double distance(Point that) {
    double xdiff = x - that.x;
    double ydiff = y - that.y;
    return Math.sqrt(xdiff * xdiff + ydiff * ydiff);
  }

  public void move(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public void move(Point that) {
    x = that.x;
    y = that.y;
  }

  public static void main(String[] args) {
    Point lowerLeft = new Point();
    Point upperRight = new Point();
    Point middlePoint = new Point();

    lowerLeft.x = 0.0;
    lowerLeft.y = 0.0;

    upperRight.x = 1280.0;
    upperRight.y = 1024.0;

    middlePoint.x = 640.0;
    middlePoint.y = 521.0;
  }
}

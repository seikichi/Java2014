package ch03.ex03_07;

public class ScreenColor {
  enum Color {
    RED,
    GREEN,
    BLUE,
    TRANSPARENT
  }

  private Color color;

  ScreenColor(Object color) {
    this.color = Color.valueOf(color.toString().toUpperCase());
  }

  @Override
  public boolean equals(Object obj) {
    if (color == null) {
      return obj == null;
    } else if (obj instanceof ScreenColor) {
      ScreenColor rhs = (ScreenColor)obj;
      return color.equals(rhs.color);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return color.hashCode();
  }

  @Override
  public String toString() {
    return color.toString().toLowerCase();
  }
}

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.util.Observable;
import java.util.Set;
import java.util.HashMap;

final class DigitalClockModel extends Observable {
  private int fontSize;
  private String fontName;
  private Color fontColor;
  private Color backgroundColor;

  public DigitalClockModel() {
    fontSize = 48;
    fontName = Font.DIALOG;
    fontColor = Color.BLACK;
    backgroundColor = Color.WHITE;
  }

  public void notifyObservers() {
    setChanged();
    notifyObservers(this);
  }

  public Font getFont() { return new Font(this.getFontName(), Font.PLAIN, this.getFontSize()); }
  public int getFontSize() { return fontSize; }
  public String getFontName() { return fontName; }
  public Color getFontColor() { return fontColor; }
  public Color getBackgroundColor() { return backgroundColor; }

  public void setFontSize(int newFontSize) {
    fontSize = newFontSize;
    notifyObservers();
  }
  public void setFontName(String newFontName) {
    fontName = newFontName;
    notifyObservers();
  }
  public void setFontColorName(String newFontColorName) {
    fontColor = colors.get(newFontColorName);
    notifyObservers();
  }
  public void setBackgroundColorName(String newBackgroundColorName) {
    backgroundColor = colors.get(newBackgroundColorName);
    notifyObservers();
  }

  private static final HashMap<String, Color> colors = new HashMap<String, Color>();
  public Set<String> getColorNames() { return colors.keySet(); }
  static {
    colors.put("BLACK", Color.BLACK);
    colors.put("BLUE", Color.BLUE);
    colors.put("CYAN", Color.CYAN);
    colors.put("GRAY", Color.DARK_GRAY);
    colors.put("GRAY", Color.GRAY);
    colors.put("GREEN", Color.GREEN);
    colors.put("GRAY", Color.LIGHT_GRAY);
    colors.put("MAGENTA", Color.MAGENTA);
    colors.put("ORANGE", Color.ORANGE);
    colors.put("PINK", Color.PINK);
    colors.put("RED", Color.RED);
    colors.put("YELLOW", Color.YELLOW);
    colors.put("WHITE", Color.WHITE);
  }

  public String getColorName(Color color) {
    for (String name : colors.keySet()) {
      if (colors.get(name).equals(color)) {
        return name;
      }
    }
    return "WHITE";
  }
}

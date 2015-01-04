import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.Point;
import java.util.Observable;
import java.util.Set;
import java.util.HashMap;
import java.util.prefs.Preferences;
import java.util.prefs.BackingStoreException;

final class DigitalClockModel extends Observable {
  private int fontSize;
  private String fontName;
  private Color fontColor;
  private Color backgroundColor;
  private Point location;

  private int prevFontSize;
  private String prevFontName;
  private Color prevFontColor;
  private Color prevBackgroundColor;
  private Point prevLocation;

  public DigitalClockModel() {
    Preferences prefs = Preferences.userNodeForPackage(this.getClass());
    prevFontSize = fontSize = Integer.parseInt(prefs.get("fontSize", "48"));
    prevFontName = fontName = prefs.get("fontName", Font.DIALOG);
    prevFontColor = fontColor = colors.get(prefs.get("fontColorName", getColorName(Color.BLACK)));
    prevBackgroundColor = backgroundColor = colors.get(prefs.get("backgroundColorName", getColorName(Color.WHITE)));
    prevLocation = location = new Point(Integer.parseInt(prefs.get("locationX", String.valueOf(-1))),
                                        Integer.parseInt(prefs.get("locationY", String.valueOf(-1))));
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
  public Point getLocation() { return location; }

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
  public void setLocation(Point newLocation) {
    location = newLocation;
    notifyObservers();
  }

  public void backup() {
    prevFontSize = fontSize;
    prevFontName = fontName;
    prevFontColor = fontColor;
    prevBackgroundColor = backgroundColor;
    prevLocation = location;
  }

  public void restore() {
    fontSize = prevFontSize;
    fontName = prevFontName;
    fontColor = prevFontColor;
    backgroundColor = prevBackgroundColor;
    location = prevLocation;
    notifyObservers();
  }

  public void save() {
    Preferences prefs = Preferences.userNodeForPackage(this.getClass());
    prefs.put("fontSize", String.valueOf(fontSize));
    prefs.put("fontName", String.valueOf(fontName));
    prefs.put("fontColorName", getColorName(fontColor));
    prefs.put("backgroundColorName", getColorName(backgroundColor));
    prefs.put("locationX", String.valueOf(location.x));
    prefs.put("locationY", String.valueOf(location.y));
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

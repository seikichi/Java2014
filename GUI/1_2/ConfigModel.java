import java.awt.Color;
import java.awt.Font;
import java.util.Observable;

final class Config extends Observable {
  private int fontSize;
  private String fontName;
  private Color fontColor;
  private Color backgroundColor;

  public Config() {
    fontSize = 48;
    fontName = Font.DIALOG;
    fontColor = Color.BLACK;
    backgroundColor = Color.WHITE;
  }

  public Font getFont() {
    return new Font(this.getFontName(), Font.PLAIN, this.getFontSize());
  }

  public void notifyObservers() {
    setChanged();
    notifyObservers(this);
  }

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
  public void setFontColor(Color newFontColor) {
    fontColor = newFontColor;
    notifyObservers();
  }
  public void setBackgroundColor(Color newBackgroundColor) {
    backgroundColor = newBackgroundColor;
    notifyObservers();
  }
}

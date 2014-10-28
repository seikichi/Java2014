import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Set;
import java.util.Observable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ScheduledFuture;

final class DigitalClockModel extends Observable {
  private static String timeFormat = "yyyy/MM/dd HH:mm:ss";

  private ScheduledExecutorService scheduler;
  private ScheduledFuture<?> future;

  private Date currentDate;
  private int fontSize;
  private String fontName;
  private Color fontColor;
  private Color backgroundColor;

  HashMap<String, Color> colors;

  public DigitalClockModel() {
    fontSize = 48;
    fontName = Font.DIALOG;
    fontColor = Color.BLACK;
    backgroundColor = Color.WHITE;
    currentDate = new GregorianCalendar().getTime();
    scheduler = Executors.newSingleThreadScheduledExecutor();

    startClock();
  }

  public Font[] getAllFonts() {
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    return ge.getAllFonts();
  }

  public HashMap<String, Color> getColorHash() {
    HashMap<String, Color> colors = new HashMap<String, Color>();
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
    return colors;
  }

  public String getColorName(Color color) {
    HashMap<String, Color> colors = getColorHash();
    for (String name : colors.keySet()) {
      if (colors.get(name).equals(color)) {
        return name;
      }
    }
    return "WHITE";
  }

  private void startClock() {
    stopClock();
    future = scheduler.scheduleWithFixedDelay(() -> {
      setCurrentDate(new GregorianCalendar().getTime());
    }, 0, 1000, TimeUnit.MILLISECONDS);
  }

  private void stopClock() {
    if (future != null) { future.cancel(true); }
  }

  public void notifyObservers() {
    setChanged();
    notifyObservers(this);
  }

  public Font getFont() {
    return new Font(this.getFontName(), Font.PLAIN, this.getFontSize());
  }
  public String getClockText() {
    final SimpleDateFormat formatter = new SimpleDateFormat(timeFormat);
    return formatter.format(currentDate);
  }
  public int getFontSize() { return fontSize; }
  public String getFontName() { return fontName; }
  public Color getFontColor() { return fontColor; }
  public Color getBackgroundColor() { return backgroundColor; }

  private void setCurrentDate(Date newDate) {
    currentDate = newDate;
    notifyObservers();
  }
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

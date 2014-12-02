import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.PopupMenu;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.Window;
import java.util.HashMap;

public final class PopupMenuPresenter {
  final PopupMenu popup = new PopupMenu();
  final DigitalClockModel model;

  HashMap<String, Color> colors = new HashMap<String, Color>();

  PopupMenuPresenter(Window owner, DigitalClockModel model) {
    this.model = model;
    initializeColors();

    addFontMenu();
    addFontSizeMenu();
    addFontColorMenu();
    addBackgroundColorMenu();
    addCloseMenu();

    owner.add(popup);
  }

  public void show(Component origin, int x, int y) {
    popup.show(origin, x, y);
  }

  private void addFontMenu() {
    Menu menu = new Menu("Font");
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    for (Font font : ge.getAllFonts()) {
      MenuItem item = new MenuItem(font.getName());
      item.addActionListener(e -> {
        model.setFontName(e.getActionCommand().toString());
      });
      menu.add(item);
    }
    popup.add(menu);
  }

  private void addFontSizeMenu() {
    Menu menu = new Menu("Font Size");
    int[] fontSizes = {
      8, 9, 10, 11, 12,
      14, 16, 18, 20,
      24, 28, 32, 36, 40, 44, 48,
      54, 60, 66, 72,
      80, 88, 96,
    };
    for (int size : fontSizes) {
      MenuItem item = new MenuItem(String.valueOf(size));
      item.addActionListener(e -> {
        model.setFontSize(Integer.parseInt(e.getActionCommand().toString()));
      });
      menu.add(item);
    }
    popup.add(menu);
  }

  private void addFontColorMenu() {
    Menu menu = new Menu("Font Color");
    for (String colorName : colors.keySet()) {
      MenuItem item = new MenuItem(colorName);
      item.addActionListener(e -> {
         String name = e.getActionCommand().toString();
         model.setFontColor(colors.get(name));
      });
      menu.add(item);
    }
    popup.add(menu);
  }

  private void addBackgroundColorMenu() {
    Menu menu = new Menu("Background Color");
    for (String colorName : colors.keySet()) {
      MenuItem item = new MenuItem(colorName);
      item.addActionListener(e -> {
         String name = e.getActionCommand().toString();
         model.setBackgroundColor(colors.get(name));
      });
      menu.add(item);
    }
    popup.add(menu);
  }

  private void addCloseMenu() {
    MenuItem item = new MenuItem("Close");
    item.addActionListener(e -> System.exit(0));
    popup.add(item);
  }

  public void initializeColors() {
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
}

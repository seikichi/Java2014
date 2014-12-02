import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.PopupMenu;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.Window;

public final class PopupMenuPresenter {
  final PopupMenu popup;

  PopupMenuPresenter(Window owner) {
    popup = new PopupMenu();

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
      menu.add(item);
    }
    popup.add(menu);
  }

  private void addFontColorMenu() {
    Menu menu = new Menu("Font Color");
    popup.add(menu);
  }

  private void addBackgroundColorMenu() {
    Menu menu = new Menu("Background Color");
    popup.add(menu);
  }

  private void addCloseMenu() {
    MenuItem item = new MenuItem("Close");
    item.addActionListener(e -> System.exit(0));
    popup.add(item);
  }
}

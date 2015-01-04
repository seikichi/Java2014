import java.awt.Font;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;

public final class MenuPresenter {
  MenuPresenter(Frame frame, DigitalClockModel model) {
    Font font = new Font(Font.DIALOG, Font.PLAIN, 12);
    MenuBar menuBar = new MenuBar();
    menuBar.setFont(font);
    frame.setMenuBar(menuBar);

    Menu menuFile = new Menu("File");
    menuFile.setFont(font);
    menuBar.add(menuFile);

    MenuItem menuProperties = new MenuItem("Properties ...", new MenuShortcut('P'));
    menuProperties.setFont(font);
    menuFile.add(menuProperties);
    menuProperties.addActionListener(e -> new PropertiesDialogPresenter(frame, model));
  }
}

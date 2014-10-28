import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;


public final class DigitalClock extends Frame implements Runnable, Observer {
  private static final long serialVersionUID = 2L;

  private static int Width = 600;
  private static int Height = 128;
  private static String timeFormat = "yyyy/MM/dd HH:mm:ss";
  private final Config config;

  public DigitalClock() {
    config = new Config();
    config.addObserver(this);

    setResizable(false);
    setTitle("Digital Clock");
    setVisible(true);
    setFont(config.getFont());
    addWindowListener(WindowAdapterFactory.closing(e -> System.exit(0)));

    // Menu (TODO)
    Frame self = this;
    MenuBar menuBar = new MenuBar();
    setMenuBar(menuBar);
    // [File]
    Menu menuFile = new Menu("File");
    menuBar.add(menuFile);
    // [File]-[Properties]
    MenuItem menuProperties = new MenuItem("Properties ...", new MenuShortcut('P'));
    menuFile.add(menuProperties);
    menuProperties.addActionListener(e -> new PropertiesDialog(self, config));
    // [File]-[Exit]
    MenuItem menuExit = new MenuItem("Exit");
    menuFile.add(menuExit);
    menuExit.addActionListener(e -> System.exit(0));

    update(null, null);
  }

  @Override
  public final void update(Observable o, Object arg) {
    setFont(config.getFont());
    setBackground(config.getBackgroundColor());

    final Insets insets = this.getInsets();
    final Rectangle2D clockSize = getClockSize();
    final int margin = 32;
    setSize((int)clockSize.getWidth() + (insets.left + insets.right) + margin * 2,
            (int)clockSize.getHeight() + (insets.top + insets.bottom) + margin * 2);
    repaint();
  }

  @Override
  public final void paint(final Graphics g) {
    final Image offscreen = createImage(this.getWidth(), this.getHeight());
    final Graphics offscreenGrapghics = offscreen.getGraphics();
    final Graphics2D canvas = (Graphics2D) offscreenGrapghics;
    final GregorianCalendar calender = new GregorianCalendar();
    final SimpleDateFormat formatter = new SimpleDateFormat(timeFormat);
    canvas.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    canvas.setColor(config.getFontColor());

    final int margin = 32;
    canvas.drawString(formatter.format(calender.getTime()),
                      this.getInsets().left + margin,
                      this.getInsets().top + margin + (int)this.getClockSize().getHeight());

    g.drawImage(offscreen, 0, 0, this);
  }

  private Rectangle2D getClockSize() {
    return new TextLayout("0000/00/00 00:00:00", config.getFont(),
                          ((Graphics2D)getGraphics()).getFontRenderContext()).getBounds();
  }

  @Override
  public final void run() {
    while (true) {
      repaint();
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e){
        break;
      }
    }
  }

  public static void main(String[] args) {
    new Thread(new DigitalClock()).start();
  }
}

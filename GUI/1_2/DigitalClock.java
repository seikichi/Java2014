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
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;


public final class DigitalClock extends Frame implements Observer {
  private static final long serialVersionUID = 2L;

  private static final int margin = 32;
  private static String timeFormat = "yyyy/MM/dd HH:mm:ss";

  private final DigitalClockModel model;
  private Image offscreen;
  private Graphics offscreenGrapghics;
  private int offscreenWidth, offscreenHeight;

  public DigitalClock(DigitalClockModel model) {
    this.model = model;
    model.addObserver(this);

    setResizable(false);
    setTitle("Digital Clock");
    setVisible(true);
    setFont(model.getFont());

    addWindowListener(WindowAdapterFactory.closing(e -> System.exit(0)));
    createMenu();

    update(null, null);
  }

  private void createMenu() {
    Font font = new Font(Font.DIALOG, Font.PLAIN, 12);
    Frame self = this;
    MenuBar menuBar = new MenuBar();
    menuBar.setFont(font);
    setMenuBar(menuBar);

    Menu menuFile = new Menu("File");
    menuFile.setFont(font);
    menuBar.add(menuFile);

    MenuItem menuProperties = new MenuItem("Properties ...", new MenuShortcut('P'));
    menuProperties.setFont(font);
    menuFile.add(menuProperties);
    menuProperties.addActionListener(e -> new PropertiesDialog(self, model));
  }

  @Override
  public final void update(Observable o, Object arg) {
    setFont(model.getFont());
    setBackground(model.getBackgroundColor());
    resetOffscreen();

    final Insets insets = this.getInsets();
    final Rectangle2D clockSize = getClockSize();
    setSize((int) clockSize.getWidth() + insets.left + insets.right + margin * 2,
            (int) clockSize.getHeight() + insets.top + insets.bottom + margin * 2);
    repaint();
  }

  @Override
  public final void paint(final Graphics g) {
    if (offscreen == null
        || offscreenWidth != this.getWidth()
        || offscreenHeight != this.getHeight()) {
      resetOffscreen();
    }

    if (offscreenGrapghics != null) {
      offscreenGrapghics.clearRect(0, 0, offscreenWidth, offscreenHeight);
    }

    final Graphics2D canvas = (Graphics2D) offscreenGrapghics;
    final GregorianCalendar calender = new GregorianCalendar();
    final SimpleDateFormat formatter = new SimpleDateFormat(timeFormat);
    canvas.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    canvas.setBackground(model.getBackgroundColor());
    canvas.setColor(model.getFontColor());

    canvas.drawString(formatter.format(calender.getTime()),
                      this.getInsets().left + margin,
                      this.getInsets().top + margin + (int)this.getClockSize().getHeight());
    g.drawImage(offscreen, 0, 0, this);
  }

  private void resetOffscreen() {
    offscreenWidth = this.getWidth();
    offscreenHeight = this.getHeight();

    if (offscreenGrapghics != null) { offscreenGrapghics.dispose(); }

    offscreen = createImage(offscreenWidth, offscreenHeight);
    offscreenGrapghics = offscreen.getGraphics();
  }

  private Rectangle2D getClockSize() {
    FontRenderContext ctx = ((Graphics2D) offscreenGrapghics).getFontRenderContext();
    return new TextLayout(model.getClockText(), model.getFont(), ctx).getBounds();
  }

  public static void main(String[] args) {
    new DigitalClock(new DigitalClockModel());
  }
}

import java.awt.Color;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.TextLayout;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;

public final class DigitalClockPresenter implements Observer {
  private final DigitalClockModel model;
  private final Content content;
  private final DoubleBufferWindow window;
  private final PopupMenuPresenter popupMenuPresenter;

  private static final long serialVersionUID = 1L;
  private static final int Margin = 32;
  private static String timeFormat = "yyyy/MM/dd HH:mm:ss";

  DigitalClockPresenter(DigitalClockModel clockModel) {
    model = clockModel;
    model.addObserver(this);
    content = new Content();
    window = new DoubleBufferWindow(null, content);
    popupMenuPresenter = new PopupMenuPresenter(window, model);

    Dimension size = content.getSize();
    window.setSize(size.width, size.height);
    window.setFont(model.getFont());
    window.setVisible(true);
    window.addMouseListener(MouseAdapterFactory.clicked(event -> {
      if (event.getButton() != MouseEvent.BUTTON3) { return; }
      popupMenuPresenter.show(event.getComponent(), event.getX(), event.getY());
    }));

    MouseAdapter adapter = new Adapter();
    window.addMouseListener(adapter);
    window.addMouseMotionListener(adapter);
  }

  public void repaint() {
    window.repaint();
  }

  @Override
  public void update(Observable o, Object arg) {
    Dimension size = content.getSize();
    window.setSize(size.width, size.height);
    window.setFont(model.getFont());
    repaint();
  }

  class Content implements DoubleBufferWindow.Content {
    private Dimension size = new Dimension(1, 1);

    @Override
    public Dimension getSize() {
      return size;
    }

    @Override
    public void draw(Graphics2D canvas) {
      FontRenderContext ctx = canvas.getFontRenderContext();
      Rectangle2D clockSize = new TextLayout("1234/56/78 00:00:00", model.getFont(), ctx).getBounds();
      Insets insets = window.getInsets();
      int width = (int) clockSize.getWidth() + insets.left + insets.right + Margin * 2;
      int height = (int) clockSize.getHeight() + insets.top + insets.bottom + Margin * 2;
      size = new Dimension(width, height);
      window.setSize(size.width, size.height);

      GregorianCalendar calender = new GregorianCalendar();
      SimpleDateFormat formatter = new SimpleDateFormat(timeFormat);
      canvas.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      canvas.setColor(model.getFontColor());
      canvas.setBackground(model.getBackgroundColor());
      canvas.clearRect(0, 0, size.width, size.height);
      canvas.drawString(formatter.format(calender.getTime()),
                        insets.left + Margin,
                        insets.top + Margin + (int)clockSize.getHeight());
    }
  }

  class Adapter extends MouseAdapter {
    private Point startPoint = null;

    @Override public void mousePressed(MouseEvent event) {
      if (event.getButton() != MouseEvent.BUTTON1) { return; }
      startPoint = event.getPoint();
    }

    @Override public void mouseReleased(MouseEvent event) {
      if (event.getButton() != MouseEvent.BUTTON1) { return; }
      startPoint = null;
    }

    @Override public void mouseDragged(MouseEvent event) {
      if (startPoint == null) { return; }
      Point mousePoint = event.getPoint();
      Point clockPoint = window.getLocation();

      int newClockX = clockPoint.x + mousePoint.x - startPoint.x;
      int newClockY = clockPoint.y + mousePoint.y - startPoint.y;
      window.setLocation(new Point(newClockX, newClockY));
    }
  }
}

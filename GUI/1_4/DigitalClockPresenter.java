import java.awt.Color;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
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
  private final DoubleBufferFrame frame;
  private final MenuPresenter menuPresenter;

  private static final long serialVersionUID = 1L;
  private static final int Margin = 32;
  private static String timeFormat = "yyyy/MM/dd HH:mm:ss";

  DigitalClockPresenter(DigitalClockModel clockModel) {
    model = clockModel;
    model.addObserver(this);
    content = new Content();
    frame = new DoubleBufferFrame(content);
    menuPresenter = new MenuPresenter(frame, clockModel);

    Dimension size = content.getSize();
    frame.setSize(size.width, size.height);
    frame.setFont(model.getFont());
    frame.setVisible(true);

    frame.addWindowListener(WindowAdapterFactory.closing(e -> System.exit(0)));
  }

  public void repaint() {
    frame.repaint();
  }

  @Override
  public void update(Observable o, Object arg) {
    Dimension size = content.getSize();
    frame.setSize(size.width, size.height);
    frame.setFont(model.getFont());
    repaint();
  }

  class Content implements DoubleBufferFrame.Content {
    private Dimension size = new Dimension(1, 1);

    @Override
    public Dimension getSize() {
      return size;
    }

    @Override
    public void draw(Graphics2D canvas) {
      FontRenderContext ctx = canvas.getFontRenderContext();
      Rectangle2D clockSize = new TextLayout("1234/56/78 00:00:00", model.getFont(), ctx).getBounds();
      Insets insets = frame.getInsets();
      int width = (int) clockSize.getWidth() + insets.left + insets.right + Margin * 2;
      int height = (int) clockSize.getHeight() + insets.top + insets.bottom + Margin * 2;
      size = new Dimension(width, height);
      frame.setSize(size.width, size.height);

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
}

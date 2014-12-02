import java.awt.Color;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public final class DigitalClockPresenter {
  private final DoubleBufferWindow window;
  private final PopupMenuPresenter popupMenuPresenter;

  private static final long serialVersionUID = 1L;

  private static int Width = 600;
  private static int Height = 128;
  private static int FontSize = 48;
  private static int FontX = 32;
  private static int FontY = 96;
  private static String timeFormat = "yyyy/MM/dd HH:mm:ss";

  DigitalClockPresenter() {
    window = new DoubleBufferWindow(null, new DoubleBufferWindow.Content() {
      @Override public Dimension getSize() {
        return new Dimension(Width, Height);
      }
      @Override public void draw(Graphics2D canvas) {
        final GregorianCalendar calender = new GregorianCalendar();
        final SimpleDateFormat formatter = new SimpleDateFormat(timeFormat);
        canvas.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        canvas.setColor(Color.BLACK);
        canvas.setBackground(Color.WHITE);
        canvas.clearRect(0, 0, Width, Height);
        canvas.drawString(formatter.format(calender.getTime()), FontX, FontY);
      }
    });
    popupMenuPresenter = new PopupMenuPresenter(window);

    window.setSize(Width, Height);
    window.setFont(new Font(Font.DIALOG, Font.BOLD, FontSize));
    window.setVisible(true);
    window.addMouseListener(MouseAdapterFactory.clicked(event -> {
      if (event.getButton() != MouseEvent.BUTTON3) { return; }
      popupMenuPresenter.show(event.getComponent(), event.getX(), event.getY());
    }));

    MouseAdapter adapter = new MouseAdapter() {
      Point startPoint;
      @Override public void mousePressed(MouseEvent event) {
        if (event.getButton() != MouseEvent.BUTTON1) { return; }
        startPoint = event.getPoint();
      }
      @Override public void mouseDragged(MouseEvent event) {
        Point mousePoint = event.getPoint();
        Point clockPoint = window.getLocation();

        int newClockX = clockPoint.x + mousePoint.x - startPoint.x;
        int newClockY = clockPoint.y + mousePoint.y - startPoint.y;
        window.setLocation(new Point(newClockX, newClockY));
      }
    };
    window.addMouseListener(adapter);
    window.addMouseMotionListener(adapter);
  }

  public void repaint() {
    window.repaint();
  }
}

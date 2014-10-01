import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public final class DigitalClock extends Frame implements Runnable {
  private static final long serialVersionUID = 1L;

  private static int Width = 600;
  private static int Height = 128;
  private static int FontSize = 48;
  private static int FontX = 32;
  private static int FontY = 96;
  private static String timeFormat = "yyyy/MM/dd HH:mm:ss";

  public DigitalClock() {
    setResizable(false);
    setSize(Width, Height);
    setTitle("Digital Clock");
    setVisible(true);
    setFont(new Font(Font.DIALOG, Font.BOLD, FontSize));
    addWindowListener(new WindowAdapter() {
        @Override public void windowClosing(WindowEvent event) {
          System.exit(0);
        }
    });
  }

  @Override public final void paint(final Graphics g) {
    final Graphics2D canvas = (Graphics2D) g;
    final GregorianCalendar calender = new GregorianCalendar();
    final SimpleDateFormat formatter = new SimpleDateFormat(timeFormat);
    canvas.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    canvas.setColor(Color.BLACK);
    canvas.drawString(formatter.format(calender.getTime()), FontX, FontY);
  }

  @Override public final void run() {
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

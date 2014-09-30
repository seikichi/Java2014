import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public final class DigitalClock extends Frame implements Runnable {
  public DigitalClock() {
    setBackground(Color.BLACK);
    setFont(new Font("Osaka", Font.CENTER_BASELINE, 30));
    setResizable(false);
    setSize(350, 55);
    setTitle("Digital Clock");
    setVisible(true);

    addWindowListener(new WindowAdapter() {
      @Override public void windowClosing(WindowEvent event) {
        System.exit(0);
      }
    });
  }

  @Override
  public final void paint(Graphics graphics) {
    final Image image = createImage(this.getWidth(), this.getHeight());
    final Graphics canvas = image.getGraphics();
    canvas.setColor(Color.WHITE);
    canvas.drawString(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new GregorianCalendar().getTime()), 10, 50);
    graphics.drawImage(image, 0, 0, this);
  }

  @Override
  public final void run() {
    while (true) {
      repaint();

      try {
        Thread.sleep(1000);
      } catch (InterruptedException e){
        return;
      }
    }
  }

  public static void main(String[] args) {
    new Thread(new DigitalClock()).start();
  }
}

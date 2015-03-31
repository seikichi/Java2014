import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public final class DigitalClock {
    private static final int Width = 600;
    private static final int Height = 96;
    private static final int FontSize = 48;
    private static final int FontX = 32;
    private static final int FontY = 48;
    private static final String timeFormat = "yyyy/MM/dd HH:mm:ss";

    public static void main(String[] args) {
        new DigitalClock();
    }

    public DigitalClock() {
        JFrame frame = new JFrame();

        frame.setTitle("Digital Clock!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Width, Height);
        frame.setResizable(false);
        frame.setVisible(true);
        new Timer(1000, (ActionEvent) -> frame.repaint()).start();

        frame.getContentPane().add(new JPanel() {
            {
                setFont(new Font(Font.DIALOG, Font.BOLD, FontSize));
            }

            @Override
            public  void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D canvas = (Graphics2D) g;
                GregorianCalendar calender = new GregorianCalendar();
                SimpleDateFormat formatter = new SimpleDateFormat(timeFormat);
                canvas.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                canvas.setColor(Color.BLACK);
                canvas.drawString(formatter.format(calender.getTime()), FontX, FontY);
            }
        });
    }
}

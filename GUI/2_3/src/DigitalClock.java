import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class DigitalClock {
    private static final int FontX = 32;
    private static final int FontY = 32;
    private static final String timeFormat = "yyyy/MM/dd HH:mm:ss";

    private JWindow window = new JWindow();
    private Settings settings = new Settings();

    public static void main(String[] args) {
        new DigitalClock();
    }

    private void updateSize(Graphics g) {
        if (g == null) {
            window.setSize(128, 128);
            return;
        }
        FontRenderContext ctx = ((Graphics2D) g).getFontRenderContext();
        Rectangle2D rect = new TextLayout("1234/56/78 90:12:34", settings.getFont(), ctx).getBounds();
        int newWidth = (int) rect.getWidth() + FontX * 2;
        int newHeight = (int) rect.getHeight() + FontY * 2;
        if (window.getWidth() == newWidth && window.getHeight() == newHeight) {
            return;
        }
        window.setSize(newWidth, newHeight);
    }


    public DigitalClock() {
        window.addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent event) {
                popup(event);
            }

            @Override public void mouseReleased(MouseEvent event) {
                popup(event);
            }

            private void popup(MouseEvent event) {
                if (!event.isPopupTrigger()) { return; }
                new DigitalClock.PopupMenuPresenter(event.getComponent(), event.getX(), event.getY());
            }
        });

        JPanel panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                updateSize(g);

                setFont(settings.getFont());
                setBackground(settings.getBackgroundColor());

                String time = new SimpleDateFormat(timeFormat).format(new GregorianCalendar().getTime());

                Graphics2D canvas = (Graphics2D) g;
                canvas.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                canvas.setColor(settings.getColor());
                canvas.setBackground(settings.getBackgroundColor());
                canvas.drawString(time, FontX, window.getHeight() - FontY);
            }
        };

        window.getContentPane().add(panel, BorderLayout.CENTER);

        MouseAdapter adapter = new MouseAdapter() {
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
        };
        window.addMouseListener(adapter);
        window.addMouseMotionListener(adapter);

        window.setSize(1, 1);
        window.setVisible(true);
        new Timer(1000, e -> window.repaint()).start();
    }

    class PopupMenuPresenter {
        public PopupMenuPresenter(Component invoker, int x, int y) {
            JPopupMenu popup = new JPopupMenu();

            addFontMenu(popup);
            addFontSize(popup);
            addColorMenu(popup);
            addBackgroundColorMenu(popup);
            addCloseMenu(popup);

            popup.show(invoker, x, y);
        }

        private void addFontMenu(JPopupMenu popup) {
            JMenu fontMenu = new JMenu("Font");
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font[] fonts = ge.getAllFonts();
            for (Font font : fonts) {
                JMenuItem item = new JMenuItem(font.getFontName());
                item.addActionListener(e -> settings.setFontName(e.getActionCommand()));
                fontMenu.add(item);
            }
            popup.add(fontMenu);
        }

        private void addFontSize(JPopupMenu popup) {
            int sizeList[] = {
                    8, 9, 10, 11, 12,
                    14, 16, 18, 20,
                    24, 28, 32, 36, 40, 44, 48,
                    54, 60, 66, 72,
                    80, 88, 96,
            };
            JMenu fontSizeMenu = new JMenu("Font Size");
            for (int size : sizeList) {
                JMenuItem menuItem = new JMenuItem(String.valueOf(size));
                menuItem.addActionListener(e -> settings.setFontSize(Integer.valueOf(e.getActionCommand())));
                fontSizeMenu.add(menuItem);
            }
            popup.add(fontSizeMenu);
        }

        private void addColorMenu(JPopupMenu popup) {
            JMenu colorMenu = new JMenu("Color");
            for (String name : settings.getColorSet()) {
                JMenuItem item = new JMenuItem(name);
                item.addActionListener(e -> settings.setColorByName(e.getActionCommand()));
                colorMenu.add(item);
            }
            popup.add(colorMenu);
        }

        private void addBackgroundColorMenu(JPopupMenu popup) {
            JMenu colorMenu = new JMenu("Background Color");
            for (String name : settings.getColorSet()) {
                JMenuItem item = new JMenuItem(name);
                item.addActionListener(e -> settings.setBackgroundColorByName(e.getActionCommand()));
                colorMenu.add(item);
            }
            popup.add(colorMenu);
        }

        private void addCloseMenu(JPopupMenu popup) {
            JMenuItem closeMenu = new JMenuItem("Close");
            closeMenu.addActionListener(e -> System.exit(0));
            popup.add(closeMenu);
        }
    }

    class Settings {
        private int fontSize = 48;
        private String fontName = Font.DIALOG;
        private Color color = Color.BLACK;
        private Color backgroundColor = Color.WHITE;

        public void setFontSize(int newFontSize) {
            fontSize = newFontSize;
        }

        public void setFontName(String newFontName) {
            fontName = newFontName;
        }

        public void setColorByName(String name) {
            color = colors.get(name);
        }

        public void setBackgroundColorByName(String name) {
            backgroundColor = colors.get(name);
        }

        public Font getFont() {
            return new Font(fontName, Font.PLAIN, fontSize);
        }

        public Color getColor() {
            return color;
        }

        public Color getBackgroundColor() {
            return backgroundColor;
        }

        public Set<String> getColorSet() {
            return colors.keySet();
        }

        private final Map<String, Color> colors = new HashMap<String, Color>() {{
            put("BLACK", Color.BLACK);
            put("BLUE", Color.BLUE);
            put("CYAN", Color.CYAN);
            put("GRAY", Color.DARK_GRAY);
            put("GRAY", Color.GRAY);
            put("GREEN", Color.GREEN);
            put("GRAY", Color.LIGHT_GRAY);
            put("MAGENTA", Color.MAGENTA);
            put("ORANGE", Color.ORANGE);
            put("PINK", Color.PINK);
            put("RED", Color.RED);
            put("YELLOW", Color.YELLOW);
            put("WHITE", Color.WHITE);
        }};
    }
}

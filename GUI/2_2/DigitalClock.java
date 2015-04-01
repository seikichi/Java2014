import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public final class DigitalClock {
    private static final int FontX = 32;
    private static final int FontY = 32;
    private static final String timeFormat = "yyyy/MM/dd HH:mm:ss";

    public static void main(String[] args) {
        new DigitalClock();
    }

    private JFrame frame;

    private int currentFontSize = 48;

    private void setClockFontSize(int newSize) {
        currentFontSize = newSize;
        currentFont = new Font(currentFont.getFontName(), Font.PLAIN, currentFontSize);
        frame.repaint();
    }

    private int getClockFontSize() {
        return currentFontSize;
    }

    private Font currentFont = new Font(Font.DIALOG, Font.PLAIN, getClockFontSize());

    private void setClockFont(Font font) {
        currentFont = font;
        frame.repaint();
    }

    private void setClockFont(String fontName) {
        setClockFont(new Font(fontName, Font.PLAIN, getClockFontSize()));
    }

    private Font getClockFont() {
        return currentFont;
    }

    private Color currentColor = Color.BLACK;

    private Color getClockFontColor() {
        return currentColor;
    }

    private void setClockFontColor(Color newColor) {
        currentColor = newColor;
        frame.repaint();
    }

    private Color currentBackgroundColor = Color.WHITE;

    private Color getClockBackgroundColor() {
        return currentBackgroundColor;
    }

    private void setClockBackgroundColor(Color newColor) {
        currentBackgroundColor = newColor;
        frame.repaint();
    }

    private void updateSize(Graphics g) {
        if (g == null) {
            frame.setSize(128, 128);
            return;
        }
        FontRenderContext ctx = ((Graphics2D) g).getFontRenderContext();
        Rectangle2D rect = new TextLayout("1234/56/78 90:12:34", getClockFont(), ctx).getBounds();
        Insets insets = frame.getInsets();
        int newWidth = (int) rect.getWidth() + insets.left + insets.right + FontX * 2;
        int newHeight = (int) rect.getHeight() + insets.top + insets.bottom + FontY * 2;
        if (frame.getWidth() == newWidth && frame.getHeight() == newHeight) {
            return;
        }
        frame.setSize(newWidth, newHeight);
    }

    public DigitalClock() {
        frame = new JFrame();

        System.setProperty("apple.laf.useScreenMenuBar", "true");
        JMenuBar menubar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem propertiesItem = new JMenuItem("Properties");
        propertiesItem.addActionListener(e -> new Dialog(frame));

        fileMenu.add(propertiesItem);
        menubar.add(fileMenu);
        frame.setJMenuBar(menubar);

        frame.getContentPane().add(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                updateSize(g);
                setBackground(getClockBackgroundColor());
                setFont(getClockFont());

                Graphics2D canvas = (Graphics2D) g;
                GregorianCalendar calender = new GregorianCalendar();
                SimpleDateFormat formatter = new SimpleDateFormat(timeFormat);
                canvas.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                canvas.setBackground(getClockBackgroundColor());
                canvas.setColor(getClockFontColor());

                FontRenderContext ctx = ((Graphics2D) g).getFontRenderContext();
                int clockHeight = (int) new TextLayout("1234/56/78 90:12:34", getClockFont(), ctx).getBounds().getHeight();
                int frameHeight = frame.getHeight();

                Insets insets = frame.getInsets();
                canvas.drawString(formatter.format(calender.getTime()), FontX + insets.left, FontY + insets.top);
            }
        }, BorderLayout.CENTER);

        updateSize(null);
        frame.setTitle("Digital Clock!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        new Timer(1000, (ActionEvent) -> frame.repaint()).start();
    }

    private class Dialog {
        private JDialog dialog;
        private JPanel panel;

        Dialog(Frame owner) {
            dialog = new JDialog(owner);

            panel = new JPanel();
            panel.setVisible(true);
            panel.setLayout(new GridLayout(8, 1));

            initFont();
            initFontSize();
            initFontColor();
            initBackgroundColor();

            dialog.getContentPane().add(panel, BorderLayout.CENTER);
            dialog.setTitle("Properties");
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setSize(256, 256);
            dialog.setResizable(false);
            dialog.setVisible(true);
            dialog.setFont(new Font(Font.DIALOG, Font.PLAIN, 10));
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


        class ColorCellRenderer extends DefaultListCellRenderer {
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                String colorName = (String) value;
                Color color = colors.get(colorName);
                setText(colorName);
                setBackground(color);
                return this;
            }
        }

        private void initBackgroundColor() {
            JComboBox<String> comboBox = new JComboBox<>(colors.keySet().toArray(new String[colors.size()]));

            for (String name : colors.keySet()) {
                if (!colors.get(name).equals(getClockBackgroundColor())) {
                    continue;
                }
                comboBox.setSelectedItem(name);
                break;
            }

            comboBox.addItemListener(e -> {
                if (e.getStateChange() != ItemEvent.SELECTED) {
                    return;
                }
                String colorName = (String) comboBox.getSelectedItem();
                setClockBackgroundColor(colors.get(colorName));
            });

            comboBox.setRenderer(new ColorCellRenderer());

            panel.add(new JLabel("Background Color"));
            panel.add(comboBox);
        }

        private void initFontColor() {
            JComboBox<String> comboBox = new JComboBox<>(colors.keySet().toArray(new String[colors.size()]));

            for (String name : colors.keySet()) {
                if (!colors.get(name).equals(getClockFontColor())) {
                    continue;
                }
                comboBox.setSelectedItem(name);
                break;
            }

            comboBox.addItemListener(e -> {
                if (e.getStateChange() != ItemEvent.SELECTED) {
                    return;
                }
                String colorName = (String) comboBox.getSelectedItem();
                setClockFontColor(colors.get(colorName));
            });

            comboBox.setRenderer(new ColorCellRenderer());

            panel.add(new JLabel("Font Color"));
            panel.add(comboBox);
        }

        private void initFontSize() {
            JComboBox<Integer> comboBox = new JComboBox<>(new Integer[]{
                    8, 9, 10, 11, 12,
                    14, 16, 18, 20,
                    24, 28, 32, 36, 40, 44, 48,
                    54, 60, 66, 72,
                    80, 88, 96,
            });
            comboBox.setSelectedItem(getClockFont().getSize());
            comboBox.addItemListener(e -> {
                if (e.getStateChange() != ItemEvent.SELECTED) {
                    return;
                }
                Integer fontSize = (Integer) comboBox.getSelectedItem();
                setClockFontSize(fontSize);
            });
            panel.add(new JLabel("Font Size"));
            panel.add(comboBox);
        }

        private void initFont() {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font[] fonts = ge.getAllFonts();
            String[] items = new String[fonts.length];
            for (int i = 0; i < fonts.length; i++) {
                items[i] = fonts[i].getFontName();
            }

            JComboBox<String> comboBox = new JComboBox<>(items);
            comboBox.setSelectedItem(getClockFont().getFontName());

            comboBox.addItemListener(e -> {
                if (e.getStateChange() != ItemEvent.SELECTED) {
                    return;
                }
                String fontName = (String) comboBox.getSelectedItem();
                setClockFont(fontName);
            });
            panel.add(new JLabel("Font"));
            panel.add(comboBox);
        }
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.Preferences;

public final class DigitalClock {
    private static final int FontX = 32;
    private static final int FontY = 32;
    private static final String timeFormat = "yyyy/MM/dd HH:mm:ss";

    public static void main(String[] args) {
        new DigitalClock();
    }

    private final JFrame frame = new JFrame();
    private final Settings settings = new Settings();

    private void updateSize(Graphics g) {
        FontRenderContext ctx = ((Graphics2D) g).getFontRenderContext();
        Rectangle2D rect = new TextLayout("1234/56/78 90:12:34", settings.getFont(), ctx).getBounds();
        Insets insets = frame.getInsets();
        int newWidth = (int) rect.getWidth() + insets.left + insets.right + FontX * 2;
        int newHeight = (int) rect.getHeight() + insets.top + insets.bottom + FontY * 2;
        if (frame.getWidth() == newWidth && frame.getHeight() == newHeight) {
            return;
        }
        frame.setSize(newWidth, newHeight);
    }

    public DigitalClock() {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        JMenuBar menubar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem propertiesItem = new JMenuItem("Properties");
        propertiesItem.addActionListener(e -> {
            settings.backup();
            new Dialog(frame);
        });

        fileMenu.add(propertiesItem);
        menubar.add(fileMenu);
        frame.setJMenuBar(menubar);

        frame.getContentPane().add(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                updateSize(g);

                setFont(settings.getFont());
                setBackground(settings.getBackgroundColor());

                Graphics2D canvas = (Graphics2D) g;
                canvas.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                canvas.setColor(settings.getColor());
                canvas.setBackground(settings.getBackgroundColor());

                Insets insets = frame.getInsets();
                String time = new SimpleDateFormat(timeFormat).format(new GregorianCalendar().getTime());
                canvas.drawString(time, FontX + insets.left, frame.getHeight() - insets.top - FontY);
            }
        }, BorderLayout.CENTER);

        frame.setLocation(settings.getLocation());
        frame.setSize(128, 128);
        frame.setTitle("Digital Clock!");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                settings.setLocation(frame.getLocation());
                settings.save();
            }
        });

        new Timer(1000, (ActionEvent) -> frame.repaint()).start();
    }

    private class Dialog {
        private final JDialog dialog;
        private final GridBagLayout layout;
        private final JPanel panel = new JPanel();

        Dialog(Frame owner) {
            dialog = new JDialog(owner);
            panel.setVisible(true);
            panel.setLayout(new GridLayout(8, 1));

            layout = new GridBagLayout();
            dialog.getContentPane().setLayout(layout);

            initFont();
            initFontSize();
            initFontColor();
            initBackgroundColor();
            initButtons();

            dialog.setTitle("Properties");
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setSize(512, 256);
            dialog.setResizable(false);
            dialog.setVisible(true);
            dialog.setFont(new Font(Font.DIALOG, Font.PLAIN, 10));
        }

        private void initButtons() {
            JButton okButton = new JButton("OK");
            JButton cancelButton = new JButton("Cancel");

            GridBagConstraints cons = new GridBagConstraints();
            cons.gridy = 4;
            cons.gridx = 1;
            cons.anchor =  GridBagConstraints.EAST;
            this.layout.setConstraints(okButton, cons);
            this.dialog.add(okButton);

            cons.gridy = 4;
            cons.gridx = 2;
            cons.anchor =  GridBagConstraints.EAST;
            this.layout.setConstraints(cancelButton, cons);
            this.dialog.add(cancelButton);

            okButton.addActionListener(e -> {
                this.dialog.dispose();
            });
            cancelButton.addActionListener(e -> {
                settings.restore();
                this.dialog.dispose();
            });
        }

        private void initBackgroundColor() {
            JComboBox<ColorTipLabelElement> comboBox = new JComboBox<>();
            for (Map.Entry<String, Color> entry : settings.getColorMap().entrySet()) {
                comboBox.addItem(new ColorTipLabelElement(entry.getKey(), new ColorIcon(entry.getValue())));
            }

            for (int i = 0; i < comboBox.getItemCount(); i++) {
                ColorTipLabelElement item = comboBox.getItemAt(i);
                if (item.getColor().equals(settings.getBackgroundColor())) {
                    comboBox.setSelectedIndex(i);
                    break;
                }
            }

            comboBox.addItemListener(e -> {
                if (e.getStateChange() != ItemEvent.SELECTED) {
                    return;
                }
                ColorTipLabelElement item = (ColorTipLabelElement) comboBox.getSelectedItem();
                settings.setBackgroundColorByName(item.getText());
            });

            comboBox.setRenderer(new ColorTipCellRenderer());
            addComponentRow(new JLabel("Background Color"), comboBox, 3);
        }

        private void initFontColor() {
            JComboBox<ColorTipLabelElement> comboBox = new JComboBox<>();
            for (Map.Entry<String, Color> entry : settings.getColorMap().entrySet()) {
                comboBox.addItem(new ColorTipLabelElement(entry.getKey(), new ColorIcon(entry.getValue())));
            }

            for (int i = 0; i < comboBox.getItemCount(); i++) {
                ColorTipLabelElement item = comboBox.getItemAt(i);
                if (item.getColor().equals(settings.getColor())) {
                    comboBox.setSelectedIndex(i);
                    break;
                }
            }

            comboBox.addItemListener(e -> {
                if (e.getStateChange() != ItemEvent.SELECTED) {
                    return;
                }
                ColorTipLabelElement item = (ColorTipLabelElement) comboBox.getSelectedItem();
                settings.setColorByName(item.getText());
            });

            comboBox.setRenderer(new ColorTipCellRenderer());
            addComponentRow(new JLabel("Color"), comboBox, 2);
        }

        private void initFontSize() {
            JComboBox<Integer> comboBox = new JComboBox<>(new Integer[]{
                    8, 9, 10, 11, 12, 14, 16, 18,
                    20, 24, 28, 32, 36, 40, 44, 48,
                    54, 60, 66, 72, 80, 88, 96,
            });
            comboBox.setSelectedItem(settings.getFont().getSize());
            comboBox.addItemListener(e -> {
                if (e.getStateChange() != ItemEvent.SELECTED) {
                    return;
                }
                Integer fontSize = (Integer) comboBox.getSelectedItem();
                settings.setFontSize(fontSize);
            });

            addComponentRow(new JLabel("Font Size"), comboBox, 1);
        }

        private void initFont() {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font[] fonts = ge.getAllFonts();
            String[] items = new String[fonts.length];
            for (int i = 0; i < fonts.length; i++) {
                items[i] = fonts[i].getFontName();
            }

            JComboBox<String> comboBox = new JComboBox<>(items);
            comboBox.setSelectedItem(settings.getFont().getFontName());

            comboBox.addItemListener(e -> {
                if (e.getStateChange() != ItemEvent.SELECTED) {
                    return;
                }
                String fontName = (String) comboBox.getSelectedItem();
                settings.setFontName(fontName);
            });

            addComponentRow(new JLabel("Font"), comboBox, 0);
        }

        private void addComponentRow(Component left, Component right, int y) {
            GridBagConstraints cons = new GridBagConstraints();
            cons.gridy = y;
            cons.gridx = 0;
            cons.anchor =  GridBagConstraints.EAST;
            this.layout.setConstraints(left, cons);
            this.dialog.add(left);

            cons.gridy = y;
            cons.gridx = 1;
            cons.gridwidth = 2;
            cons.anchor =  GridBagConstraints.WEST;
            cons.fill = GridBagConstraints.HORIZONTAL;

            this.layout.setConstraints(right, cons);
            this.dialog.add(right);
        }

        class ColorIcon implements Icon {
            private final Color color;
            private static final int size = 12;

            ColorIcon(Color color) {
                this.color = color;
            }

            @Override
            public int getIconHeight() {
                return size;
            }

            @Override
            public int getIconWidth() {
                return size;
            }

            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                g.setColor(color);
                g.fillRect(x, y, size, size);
            }

            public Color getColor() {
                return color;
            }
        }

        class ColorTipLabelElement {
            private final String text;
            private final Icon icon;
            private final Color color;

            ColorTipLabelElement(String text, ColorIcon icon) {
                this.text = text;
                this.icon = icon;
                this.color = icon.getColor();
            }

            public String getText() {
                return text;
            }

            public Icon getIcon() {
                return icon;
            }

            public Color getColor() {
                return color;
            }
        }

        class ColorTipCellRenderer extends JLabel implements ListCellRenderer<ColorTipLabelElement> {

            @Override
            public Component getListCellRendererComponent(JList<? extends ColorTipLabelElement> list,
                                                          ColorTipLabelElement value,
                                                          int index,
                                                          boolean isSelected,
                                                          boolean cellHasFocus) {
                setText(value.getText());
                setIcon(value.getIcon());
                return this;
            }
        }
    }

    class Settings {
        private int fontSize, prevFontSize;
        private String fontName, prevFontName;
        private Color color, prevColor;
        private Color backgroundColor, prevBackgroundColor;
        private Point location, prevLocation;

        Settings() {
            Preferences prefs = Preferences.userNodeForPackage(this.getClass());
            fontSize = prevFontSize = Integer.parseInt(prefs.get("fontSize", "48"));
            fontName = prevFontName = prefs.get("fontName", Font.DIALOG);
            color = prevColor = colors.get(prefs.get("color", "BLACK"));
            backgroundColor = prevBackgroundColor = colors.get(prefs.get("backgroundColor", "WHITE"));
            location = prevLocation = new Point(Integer.parseInt(prefs.get("locationX", "0")), Integer.parseInt(prefs.get("locationY", "0")));
        }

        public void save() {
            Preferences prefs = Preferences.userNodeForPackage(this.getClass());
            prefs.put("fontSize", String.valueOf(fontSize));
            prefs.put("fontName", String.valueOf(fontName));
            prefs.put("color", getColorName(color));
            prefs.put("backgroundColor", getColorName(backgroundColor));
            prefs.put("locationX", String.valueOf(location.x));
            prefs.put("locationY", String.valueOf(location.y));
        }

        private String getColorName(Color color) {
            for (Map.Entry<String, Color> entry : colors.entrySet()) {
                if (entry.getValue().equals(color)) {
                    return entry.getKey();
                }
            }
            return "WHITE";
        }

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

        public Point getLocation() {
            return location;
        }

        public void setLocation(Point location) {
            this.location = location;
        }

        public Map<String, Color> getColorMap() {
            return Collections.unmodifiableMap(colors);
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

        public void backup() {
            prevFontSize = fontSize;
            prevFontName = fontName;
            prevColor = color;
            prevBackgroundColor = backgroundColor;
            prevLocation = location;
        }

        public void restore() {
            fontSize = prevFontSize;
            fontName = prevFontName;
            color = prevColor;
            backgroundColor = prevBackgroundColor;
            location = prevLocation;
            frame.repaint();
        }
    }
}

import java.util.HashMap;

import java.awt.*;
import java.awt.event.*;

public final class PropertiesDialog extends Dialog implements ActionListener {
  private final Config config;

  PropertiesDialog(Frame owner, Config config) {
    super(owner);
    this.config = config;

    setTitle("Properties");
    setSize(512, 512);
    setVisible(true);
    setResizable(false);
    setVisible(true);

    setLayout(new GridLayout(10, 1));

    Choice fontChoice = new Choice();
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    Font fonts[] = ge.getAllFonts();
    for (Font font : fonts) {
      fontChoice.add(font.getName());
    }
    fontChoice.select(config.getFontName());
    fontChoice.addItemListener(e -> {
        Choice c = (Choice) e.getItemSelectable();
        config.setFontName(c.getSelectedItem());
    });
    add(fontChoice);

    Choice fontSizeChoice = new Choice();
    int[] fontSizes = {
      8, 9, 10, 11, 12,
      14, 16, 18, 20,
      24, 28, 32, 36, 40, 44, 48,
      54, 60, 66, 72,
      80, 88, 96,
    };
    for (int size : fontSizes) {
      fontSizeChoice.add(String.valueOf(size));
    }
    fontSizeChoice.select(String.valueOf(config.getFontSize()));
    fontSizeChoice.addItemListener(e -> {
        Choice c = (Choice) e.getItemSelectable();
        config.setFontSize(Integer.parseInt(c.getSelectedItem()));
    });
    add(fontSizeChoice);

    HashMap<String, Color> colors = new HashMap<String, Color>();
    colors.put("BLACK", Color.BLACK);
    colors.put("BLUE", Color.BLUE);
    colors.put("CYAN", Color.CYAN);
    colors.put("GRAY", Color.DARK_GRAY);
    colors.put("GRAY", Color.GRAY);
    colors.put("GREEN", Color.GREEN);
    colors.put("GRAY", Color.LIGHT_GRAY);
    colors.put("MAGENTA", Color.MAGENTA);
    colors.put("ORANGE", Color.ORANGE);
    colors.put("PINK", Color.PINK);
    colors.put("RED", Color.RED);
    colors.put("YELLOW", Color.YELLOW);

    Choice fontColorChoice = new Choice();
    for (String colorName : colors.keySet()) {
      fontColorChoice.add(colorName);
    }
    fontColorChoice.addItemListener(e -> {
        Choice c = (Choice) e.getItemSelectable();
        config.setFontColor(colors.get(c.getSelectedItem()));
    });
    add(fontColorChoice);

    Choice backgroundColorChoice = new Choice();
    for (String colorName : colors.keySet()) {
      backgroundColorChoice.add(colorName);
    }
    backgroundColorChoice.addItemListener(e -> {
        Choice c = (Choice) e.getItemSelectable();
        config.setBackgroundColor(colors.get(c.getSelectedItem()));
    });
    add(backgroundColorChoice);

    Button okButton = new Button("OK");
    okButton.addActionListener(this);
    okButton.addActionListener(e -> { System.exit(0); });
    add(okButton);

    addWindowListener(WindowAdapterFactory.closing(e -> System.exit(0)));
  }

  public void actionPerformed(ActionEvent e) {
    System.exit(0);
  }
}

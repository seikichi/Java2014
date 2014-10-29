import java.util.HashMap;

import java.awt.*;
import java.awt.event.*;

public final class PropertiesDialog extends Dialog {
  private static final long serialVersionUID = 2L;

  private final static int Width = 512;
  private final static int Height = 256;
  private final DigitalClockModel model;

  PropertiesDialog(Frame owner, DigitalClockModel model) {
    super(owner);
    this.model = model;

    setTitle("Properties");
    setSize(Width, Height);
    setVisible(true);
    setResizable(false);
    setVisible(true);
    setFont(new Font(Font.DIALOG, Font.PLAIN, 12));

    setLayout(new GridLayout(9, 1));
    addFontChoice();
    addFontSizeChoice();
    addFontColorChoice();
    addBackgroundColorChoice();
    addOkButton();

    addWindowListener(WindowAdapterFactory.closing(e -> dispose()));
  }

  private void addFontChoice() {
    Choice fontChoice = new Choice();
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    for (Font font : model.getAllFonts()) {
      fontChoice.add(font.getName());
    }
    fontChoice.select(model.getFontName());
    fontChoice.addItemListener(e -> {
        Choice c = (Choice) e.getItemSelectable();
        model.setFontName(c.getSelectedItem());
    });
    add(new Label("Font"));
    add(fontChoice);
  }

  private void addFontSizeChoice() {
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
    fontSizeChoice.select(String.valueOf(model.getFontSize()));
    fontSizeChoice.addItemListener(e -> {
        Choice c = (Choice) e.getItemSelectable();
        model.setFontSize(Integer.parseInt(c.getSelectedItem()));
    });
    add(new Label("Font Size"));
    add(fontSizeChoice);
  }

  private void addFontColorChoice() {
    HashMap<String, Color> colors = model.getColorHash();
    Choice fontColorChoice = new Choice();
    for (String colorName : colors.keySet()) {
      fontColorChoice.add(colorName);
    }
    fontColorChoice.addItemListener(e -> {
        Choice c = (Choice) e.getItemSelectable();
        model.setFontColor(colors.get(c.getSelectedItem()));
    });
    fontColorChoice.select(model.getColorName(model.getFontColor()));
    add(new Label("Font Color"));
    add(fontColorChoice);
  }

  private void addBackgroundColorChoice() {
    HashMap<String, Color> colors = model.getColorHash();
    Choice backgroundColorChoice = new Choice();
    for (String colorName : colors.keySet()) {
      backgroundColorChoice.add(colorName);
    }
    backgroundColorChoice.addItemListener(e -> {
        Choice c = (Choice) e.getItemSelectable();
        model.setBackgroundColor(colors.get(c.getSelectedItem()));
    });
    backgroundColorChoice.select(model.getColorName(model.getBackgroundColor()));
    add(new Label("Background Color"));
    add(backgroundColorChoice);
  }

  private void addOkButton() {
    Button okButton = new Button("OK");
    okButton.addActionListener(e -> dispose());
    add(okButton);
  }
}

import java.awt.GraphicsEnvironment;
import java.awt.Button;
import java.awt.Label;
import java.awt.Choice;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Dialog;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public final class PropertiesDialogPresenter {
  private final static int Width = 512;
  private final static int Height = 256;
  private final DigitalClockModel model;
  private final Dialog dialog;
  private final GridBagLayout layout;

  PropertiesDialogPresenter(Frame owner, DigitalClockModel model) {
    this.model = model;
    this.dialog = new Dialog(owner);

    this.dialog.setTitle("Properties");
    this.dialog.setSize(Width, Height);
    this.dialog.setVisible(true);
    this.dialog.setResizable(false);
    this.dialog.setVisible(true);
    this.dialog.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
    this.dialog.addWindowListener(WindowAdapterFactory.closing(e -> this.dialog.dispose()));

    this.layout = new GridBagLayout();
    this.dialog.setLayout(layout);
    this.addFontChoice();
    this.addFontSizeChoice();
    this.addFontColorChoice();
    this.addBackgroundColorChoice();
  }

  private void addFontChoice() {
    Choice fontChoice = new Choice();
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    for (Font menuFont : ge.getAllFonts()) {
      fontChoice.add(menuFont.getName());
    }
    fontChoice.select(model.getFontName());
    fontChoice.addItemListener(e -> {
      Choice c = (Choice) e.getItemSelectable();
      model.setFontName(c.getSelectedItem());
    });

    GridBagConstraints cons = new GridBagConstraints();
    cons.gridy = 0;
    cons.gridx = 0;
    cons.anchor =  GridBagConstraints.EAST;
    Label label = new Label("Font");
    this.layout.setConstraints(label, cons);
    this.dialog.add(label);

    cons.gridy = 0;
    cons.gridx = 1;
    cons.anchor =  GridBagConstraints.WEST;
    this.layout.setConstraints(fontChoice, cons);
    this.dialog.add(fontChoice);
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
    Label label = new Label("Font Size");

    GridBagConstraints cons = new GridBagConstraints();
    cons.gridy = 1;
    cons.gridx = 0;
    cons.anchor =  GridBagConstraints.EAST;
    this.layout.setConstraints(label, cons);
    this.dialog.add(label);

    cons.gridy = 1;
    cons.gridx = 1;
    cons.anchor =  GridBagConstraints.WEST;
    this.layout.setConstraints(fontSizeChoice, cons);
    this.dialog.add(fontSizeChoice);
  }

  private void addFontColorChoice() {
    Choice fontColorChoice = new Choice();
    for (String colorName : model.getColorNames()) {
      fontColorChoice.add(colorName);
    }
    fontColorChoice.addItemListener(e -> {
        Choice c = (Choice) e.getItemSelectable();
        model.setFontColorName(c.getSelectedItem());
    });
    fontColorChoice.select(model.getColorName(model.getFontColor()));
    Label label = new Label("Font Color");

    GridBagConstraints cons = new GridBagConstraints();
    cons.gridy = 2;
    cons.gridx = 0;
    cons.anchor =  GridBagConstraints.EAST;
    this.layout.setConstraints(label, cons);
    this.dialog.add(label);

    cons.gridy = 2;
    cons.gridx = 1;
    cons.anchor =  GridBagConstraints.WEST;
    this.layout.setConstraints(fontColorChoice, cons);
    this.dialog.add(fontColorChoice);
  }

  private void addBackgroundColorChoice() {
    Choice backgroungColorChoice = new Choice();
    for (String colorName : model.getColorNames()) {
      backgroungColorChoice.add(colorName);
    }
    backgroungColorChoice.addItemListener(e -> {
        Choice c = (Choice) e.getItemSelectable();
        model.setBackgroundColorName(c.getSelectedItem());
    });
    backgroungColorChoice.select(model.getColorName(model.getBackgroundColor()));
    Label label = new Label("Background Color");

    GridBagConstraints cons = new GridBagConstraints();
    cons.gridy = 3;
    cons.gridx = 0;
    cons.anchor =  GridBagConstraints.EAST;
    this.layout.setConstraints(label, cons);
    this.dialog.add(label);

    cons.gridy = 3;
    cons.gridx = 1;
    cons.anchor =  GridBagConstraints.WEST;
    this.layout.setConstraints(backgroungColorChoice, cons);
    this.dialog.add(backgroungColorChoice);
  }

  // private void addOkButton() {
  //   Button okButton = new Button("OK");
  //   okButton.addActionListener(e -> dispose());
  //   add(okButton);
  // }
}

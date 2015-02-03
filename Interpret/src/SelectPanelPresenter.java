import javax.swing.JOptionPane;
import java.awt.Dimension;
import java.awt.Window;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;

class SelectPanelPresenter {
  private Object explored = null;

  private JLabel label = new JLabel("new value: ");
  private JButton find = new JButton("find");
  private JTextField textField = new JTextField();
  // private String[] items = {"int", "double", "String", "other"};
  private Class<?>[] items = {
    boolean.class,
    byte.class,
    short.class,
    int.class,
    long.class,
    float.class,
    double.class,
    char.class,
    String.class,
    Object.class,
  };
  private JComboBox<Class<?>> box = new JComboBox<>(items);

  public Object get() {
    String text = textField.getText();
    Object item = box.getSelectedItem();
    try {
      if (item.equals(Object.class)) {
        return explored;
      } else if (item.equals(boolean.class)) {
        return Boolean.valueOf(text);
      } else if (item.equals(byte.class)) {
        return Byte.valueOf(text);
      } else if (item.equals(short.class)) {
        return Short.valueOf(text);
      } else if (item.equals(int.class)) {
        return Integer.valueOf(text);
      } else if (item.equals(long.class)) {
        return Long.valueOf(text);
      } else if (item.equals(float.class)) {
        return Float.valueOf(text);
      } else if (item.equals(double.class)) {
        return Double.valueOf(text);
      } else if (item.equals(char.class)) {
        return text.charAt(0);
      }
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e.toString(), "Exception", JOptionPane.ERROR_MESSAGE);
    }
    return text;
  }

  SelectPanelPresenter(JPanel panel, Window owner, InterpretModel model) {
    Dimension labelDim = label.getMaximumSize();
    Dimension textFieldDim = textField.getMaximumSize();
    textFieldDim.width = Short.MAX_VALUE;
    textFieldDim.height = labelDim.height;
    textField.setMaximumSize(textFieldDim);

    find.addActionListener(e -> {
        new ExplorerDialogPresenter(owner,
                                    model,
                                    ExplorerDialogPresenter.TargetType.FIELD_READONLY,
                                    (result) -> {
                                      if (result == null) { return; }
                                      if (result instanceof FieldResult) {
                                        explored = ((FieldResult) result).get();
                                      }
        });
      });

    panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
    panel.add(label);
    panel.add(textField);
    panel.add(box);
    panel.add(find);
  }
}

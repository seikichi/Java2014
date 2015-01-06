import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.Window;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JDialog;
import java.awt.Dialog;

class NewDialogPresenter {
  public NewDialogPresenter(Window owner, ConstructorResult ctor, InterpretModel model) {
    System.out.println(ctor);

    JDialog dialog = new JDialog(owner, "New Instance", Dialog.ModalityType.APPLICATION_MODAL);
    JLabel label = new JLabel(String.format("Constructor: %s", ctor.get().toGenericString()));
    JButton cancel = new JButton("Cancel");
    JButton create = new JButton("Create");

    dialog.setLayout(new BorderLayout());
    dialog.add(label, BorderLayout.PAGE_START);

    JPanel footer = new JPanel();
    JTextField dim = new JTextField("-1");
    JTextField name = new JTextField("x");
    footer.setLayout(new GridLayout(3, 2));
    footer.add(new JLabel("create array if value >= 0:"));
    footer.add(dim);
    footer.add(new JLabel("variable name:"));
    footer.add(name);
    footer.add(create);
    footer.add(cancel);
    dialog.add(footer, BorderLayout.PAGE_END);

    int parameterNums = ctor.get().getParameterTypes().length;
    SelectPanelPresenter[] selectors = new SelectPanelPresenter[parameterNums];
    JPanel center = new JPanel();
    center.setLayout(new GridLayout(parameterNums, 1));

    for (int i = 0; i < parameterNums; i++) {
      JPanel panel = new JPanel();
      selectors[i] = new SelectPanelPresenter(panel, dialog, model);
      center.add(panel);
    }
    dialog.add(center, BorderLayout.CENTER);

    create.addActionListener(e -> {
      String varName = name.getText();
      int ndim = Integer.valueOf(dim.getText());
      if (ndim >= 0) {
        model.addLocalVariable(varName, ctor.newArray(ndim));
      } else {
        Object[] args = new Object[parameterNums];
        for (int i = 0; i < parameterNums; i++) {
          args[i] = selectors[i].get();
        }
        model.addLocalVariable(varName, ctor.invoke(args));
      }
      dialog.dispose();
    });
    cancel.addActionListener(e -> {
      dialog.dispose();
    });

    dialog.setSize(512, 256);
    dialog.setVisible(true);
  }
}

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

class InvokeDialogPresenter {
  public InvokeDialogPresenter(Window owner, MethodResult method, InterpretModel model) {
    JDialog dialog = new JDialog(owner, "Invoke Method", Dialog.ModalityType.APPLICATION_MODAL);
    JLabel label = new JLabel(String.format("Method: %s", method.get().toGenericString()));
    JButton cancel = new JButton("Cancel");
    JButton call = new JButton("Invoke");

    dialog.setLayout(new BorderLayout());
    dialog.add(label, BorderLayout.PAGE_START);

    JPanel footer = new JPanel();
    JTextField name = new JTextField(model.getFreshVariableName());
    footer.setLayout(new GridLayout(2, 2));
    footer.add(new JLabel("variable name:"));
    footer.add(name);
    footer.add(call);
    footer.add(cancel);
    dialog.add(footer, BorderLayout.PAGE_END);

    int parameterNums = method.get().getParameterTypes().length;
    SelectPanelPresenter[] selectors = new SelectPanelPresenter[parameterNums];
    JPanel center = new JPanel();
    center.setLayout(new GridLayout(parameterNums, 1));

    for (int i = 0; i < parameterNums; i++) {
      JPanel panel = new JPanel();
      selectors[i] = new SelectPanelPresenter(panel, dialog, model);
      center.add(panel);
    }
    dialog.add(center, BorderLayout.CENTER);

    call.addActionListener(e -> {
      String varName = name.getText();
      Object[] args = new Object[parameterNums];
      for (int i = 0; i < parameterNums; i++) {
        args[i] = selectors[i].get();
      }
      model.addLocalVariable(varName, method.invoke(args));
      dialog.dispose();
    });
    cancel.addActionListener(e -> {
      dialog.dispose();
    });

    dialog.setSize(512, 256);
    dialog.setVisible(true);
  }
}

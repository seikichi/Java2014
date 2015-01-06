import java.awt.GridLayout;
import java.awt.Window;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JDialog;
import java.awt.Dialog;

class InspectDialogPresenter {
  public InspectDialogPresenter(Window owner, FieldResult field, InterpretModel model) {
    JDialog dialog = new JDialog(owner, "Inspect Field", Dialog.ModalityType.APPLICATION_MODAL);
    JLabel label = new JLabel("current value: " + field.get());
    JButton cancel = new JButton("Cancel");
    JButton update = new JButton("Update");

    dialog.setLayout(new BorderLayout());
    dialog.add(label, BorderLayout.PAGE_START);

    JPanel footer = new JPanel();
    footer.setLayout(new GridLayout(1, 2));
    footer.add(update);
    footer.add(cancel);
    dialog.add(footer, BorderLayout.PAGE_END);

    JPanel center = new JPanel();
    SelectPanelPresenter selector = new SelectPanelPresenter(center, dialog, model);
    dialog.add(center, BorderLayout.CENTER);

    update.addActionListener(e -> {
      field.set(selector.get());
      dialog.dispose();
    });
    cancel.addActionListener(e -> {
      dialog.dispose();
    });

    dialog.setSize(512, 256);
    dialog.setVisible(true);
  }
}

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public final class InterpretPresenter {
  JFrame frame;
  private JLabel label;
  private InterpretModel model;

  public InterpretPresenter(InterpretModel model) {
    this.model = model;

    frame = new JFrame();
    label = new JLabel("Hello");
    label.setName("label");

    frame.add(label, BorderLayout.NORTH);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(256, 256);
    frame.setName("Label Sample");
    frame.setVisible(true);

    new ExplorerDialogPresenter(this.frame, this.model);
  }
}

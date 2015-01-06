import javax.swing.JButton;

import java.awt.GridLayout;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public final class InterpretPresenter {
  JFrame frame;
  private JLabel label;
  private InterpretModel model;

  public static Integer value = 10;
  public static Integer other = 123456789;

  public InterpretPresenter(InterpretModel model) {
    this.model = model;
    frame = new JFrame();

    frame.setLayout(new GridLayout(3, 1));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JButton newButton = new JButton("New");
    JButton inspectButton = new JButton("Inspect");
    JButton invokeButton = new JButton("Invoke");
    frame.add(newButton);
    frame.add(inspectButton);
    frame.add(invokeButton);

    frame.setName("GUI Java Interpreter");
    frame.setSize(256, 256);
    frame.setVisible(true);

    try {
      new InspectDialogPresenter(frame,
                                 FieldResult.fromField(this.getClass().getField("value"), this),
                                 model);
    } catch (Exception e) {
      e.printStackTrace();
    }

    inspectButton.addActionListener(e -> {
        new ExplorerDialogPresenter(frame,
                                    model,
                                    ExplorerDialogPresenter.TargetType.FIELD,
                                    (result) -> {
                                      if (result == null) { return; }
                                      if (result instanceof FieldResult) {
                                        new InspectDialogPresenter(frame, (FieldResult) result, model);
                                      }
        });
      });
  }
}

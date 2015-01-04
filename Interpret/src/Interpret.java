import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public final class Interpret extends JFrame {
  private JFrame frame;
  private JLabel label;

  public Interpret() {
    // frame = new JFrame();
    super();
    label = new JLabel("Hello");
    label.setName("label");
    /*frame.*/ add(label, BorderLayout.NORTH);

    /*frame.*/ setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    /*frame.*/ setSize(256, 256);
    /*frame.*/ setName("Label Sample");
    /*frame.*/ setVisible(true);
  }

  public static void main(String[] args) {
    new Interpret();
  }
}

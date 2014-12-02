import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

final class MouseAdapterFactory {
  private MouseAdapterFactory() { }

  public static MouseAdapter clicked(Consumer<MouseEvent> c) {
    return new MouseAdapter() {
      @Override public void mouseClicked(MouseEvent e) { c.accept(e); }
    };
  }
}

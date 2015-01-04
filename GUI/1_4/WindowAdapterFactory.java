import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.function.Consumer;

final class WindowAdapterFactory {
  private WindowAdapterFactory() { }

  public static WindowAdapter closing(Consumer<WindowEvent> c) {
    return new WindowAdapter() {
      @Override public void windowClosing(WindowEvent e) { c.accept(e); }
    };
  }
}

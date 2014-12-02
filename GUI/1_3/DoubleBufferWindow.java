import java.awt.Color;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

public final class DoubleBufferWindow extends Window {
  private Image buffer;
  private Graphics bufferGrapghics;
  private int bufferWidth, bufferHeight;
  private Content content;

  public interface Content {
    Dimension getSize();
    void draw(Graphics2D canvas);
  }

  DoubleBufferWindow(Window owner, Content content) {
    super(owner);
    this.content = content;
  }

  @Override
  public void update(Graphics g){
    paint(g);
  }

  @Override
  public void paint(Graphics g) {
    Dimension dim = content.getSize();
    if (buffer == null ||
        buffer.getWidth(this) != dim.width ||
        buffer.getHeight(this) != dim.height) {
      if (bufferGrapghics != null) { bufferGrapghics.dispose(); }
      buffer = createImage(dim.width, dim.height);
      bufferGrapghics = buffer.getGraphics();
    }

    final Graphics2D canvas = (Graphics2D) bufferGrapghics;
    content.draw(canvas);
    g.drawImage(buffer, 0, 0, this);
  }
}

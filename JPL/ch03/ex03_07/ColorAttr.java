package ch03.ex03_07;

public class ColorAttr extends Attr {
  private ScreenColor myColor;

  public ColorAttr(final String name, final Object value) {
    super(name, value);
    decodeColor();
  }

  public ColorAttr(final String name) {
    this(name, "transparent");
  }

  public ColorAttr(final String name, final ScreenColor value) {
    super(name, value.toString());
    myColor = value;
  }

  public boolean equals(Object obj) {
    if (obj instanceof ColorAttr) {
      ColorAttr rhs = (ColorAttr)obj;
      return this.getName().equals(rhs.getName()) && myColor.equals(rhs.getColor());
    }
    return false;
  }

  public int hashCode() {
    return (this.getName().hashCode() ^ this.getColor().hashCode());
  }

  @Override
  public Object setValue(Object newValue) {
    Object retval = super.setValue(newValue);
    decodeColor();
    return retval;
  }

  public ScreenColor setValue(ScreenColor newValue) {
    super.setValue(newValue.toString());
    ScreenColor oldValue = myColor;
    myColor = newValue;
    return oldValue;
  }

  public final ScreenColor getColor() {
    return myColor;
  }

  public void decodeColor() {
    if (getValue() == null) {
      myColor = null;
    } else {
      myColor = new ScreenColor(getValue());
    }
  }
}

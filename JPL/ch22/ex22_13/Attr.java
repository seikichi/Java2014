package ch22.ex22_13;

public class Attr {
    private final String name;
    private Object value = null;

    public Attr(String name) {
        if (name == null) {
            throw new NullPointerException("Invalid Attr name");
        }
        this.name = name;
    }

    public Attr(String name, Object value) {
        this(name);
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public Object setValue(Object newValue) {
        Object oldVal = value;
        value = newValue;
        return oldVal;
    }

    public String toString() {
        return name + "='" + value + "'";
    }
}

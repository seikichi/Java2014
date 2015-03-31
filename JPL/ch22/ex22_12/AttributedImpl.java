package ch22.ex22_12;

import java.io.Reader;
import java.util.*;

public final class AttributedImpl<E> implements Attributed<E> {
    HashMap<String, Attr<E>> attrTable = new HashMap<>();

    public void add(Attr<E> newAttr) {
        attrTable.put(newAttr.getName(), newAttr);
    }

    public Attr<E> find(String attrName) {
        return attrTable.get(attrName);
    }

    public Attr<E> remove(String attrName) {
        return attrTable.remove(attrName);
    }

    public Iterator<Attr<E>> attrs() {
        return attrTable.values().iterator();
    }

    public static Attributed readAttrs(Reader sourcxe) {
        return null;
    }
}

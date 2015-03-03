package ch22.ex22_04;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;

public class ObservableAttributedImpl<E> extends Observable {
    HashMap<String, Attr<E>> attrTable = new HashMap<>();

    public void add(Attr<E> newAttr) {
        attrTable.put(newAttr.getName(), newAttr);
        setChanged();
        notifyObservers();
    }

    public Attr<E> find(String attrName) {
        return attrTable.get(attrName);
    }

    public Attr<E> remove(String attrName) {
        Attr<E> old = attrTable.remove(attrName);
        setChanged();
        notifyObservers();
        return old;
    }

    public Iterator<Attr<E>> attrs() {
        return attrTable.values().iterator();
    }
}

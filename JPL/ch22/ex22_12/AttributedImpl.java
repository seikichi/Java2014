package ch22.ex22_12;

import java.io.Reader;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

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

    public static Attributed<Object> readAttrs(Reader source) {
        Scanner in = new Scanner(source);
        AttributedImpl<Object> attrs = new AttributedImpl<>();

        Pattern pat = Pattern.compile("^(.+)=(.+)$", Pattern.MULTILINE);
        while (in.hasNext()) {
            in.findInLine(pat);
            MatchResult m = in.match();
            attrs.add(new Attr(m.group(1), m.group(2)));
            in.nextLine();
        }
        return attrs;
    }
}

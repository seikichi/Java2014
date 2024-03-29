package ch22.ex22_12;

import java.io.Reader;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public final class AttributedImpl implements Attributed {
    Map<String, Attr> attrTable = new HashMap<>();

    public void add(Attr newAttr) {
        attrTable.put(newAttr.getName(), newAttr);
    }

    public Attr find(String attrName) {
        return attrTable.get(attrName);
    }

    public Attr remove(String attrName) {
        return attrTable.remove(attrName);
    }

    public Iterator<Attr> attrs() {
        return attrTable.values().iterator();
    }

    public static Attributed readAttrs(Reader source) {
        Scanner in = new Scanner(source);
        AttributedImpl attrs = new AttributedImpl();

        Pattern pat = Pattern.compile("^([^=]+)=([^=]+)$", Pattern.MULTILINE);
        while (in.hasNext()) {
            in.findInLine(pat);
            MatchResult m = in.match();
            attrs.add(new Attr(m.group(1), m.group(2)));
            if (in.hasNextLine()) { in.nextLine(); }
        }
        return attrs;
    }
}

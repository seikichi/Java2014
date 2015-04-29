package ch22.ex22_13;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
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

    public static Attributed readAttrs(Reader source) throws IOException {
        Scanner in = new Scanner(source);
        AttributedImpl attrs = new AttributedImpl();

        Pattern pat = Pattern.compile("^([^=]+)=([^=]+)$", Pattern.MULTILINE);
        while (in.hasNext()) {
            in.findInLine(pat);
            MatchResult m = null;
            try {
                m = in.match();
            } catch(IllegalStateException e) {
                throw new IOException("invalid format.");
            }
            attrs.add(new Attr(m.group(1), m.group(2)));
            if (in.hasNextLine()) { in.nextLine(); }
        }
        return attrs;
    }
}

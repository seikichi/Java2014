package ch21.ex21_04;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ShortStrings implements ListIterator<String> {
    private enum DIR { NONE, PREV, NEXT }
    
    private final ListIterator<String> strings;
    private final int maxLen;
    private String nextShort, previousShort;
    private int nextIndex, previousIndex;
    private DIR lastDir = DIR.NONE;

    public ShortStrings(ListIterator<String> strings, int maxLen) {
        this.strings = strings;
        this.maxLen = maxLen;
    }

    @Override
    public boolean hasNext() {
        lastDir = DIR.NEXT;
        previousShort = null;

        if (nextShort != null) { return true; }
        while (strings.hasNext()) {
            nextIndex = strings.nextIndex();
            nextShort = strings.next();

            if (nextShort.length() <= maxLen) {
                return true;
            }
        }
        nextIndex = strings.nextIndex();
        nextShort = null;
        return false;
    }

    @Override
    public String next() {
        if (nextShort == null && !hasNext()) {
            throw new NoSuchElementException();
        }
        String n = nextShort;
        nextShort = null;

        return n;
    }

    @Override
    public boolean hasPrevious() {
        lastDir = DIR.PREV;
        nextShort = null;

        if (previousShort != null) { return true; }
        while (strings.hasPrevious()) {
            previousIndex = strings.previousIndex();
            previousShort = strings.previous();

            if (previousShort.length() <= maxLen) {
                return true;
            }
        }
        previousIndex = strings.previousIndex();
        previousShort = null;
        return false;
    }

    @Override
    public String previous() {
        if (previousShort == null && !hasPrevious()) {
            throw new NoSuchElementException();
        }
        String p = previousShort;
        previousShort = null;
        return p;
    }

    @Override
    public int nextIndex() {
        hasNext();
        return nextIndex;
    }

    @Override
    public int previousIndex() {
        hasPrevious();
        return previousIndex;
    }

    @Override
    public void remove() {
        if (lastDir == DIR.NEXT) { hasPrevious(); }
        else if (lastDir == DIR.PREV) { hasNext(); }
        else { throw new IllegalStateException(); }
        strings.remove();
        nextShort = previousShort = null;
    }

    @Override
    public void set(String s) {
        if (lastDir == DIR.NEXT) { hasPrevious(); }
        else if (lastDir == DIR.PREV) { hasNext(); }
        else { throw new IllegalStateException(); }
        strings.set(s);
        nextShort = previousShort = null;
    }

    @Override
    public void add(String s) {
        strings.add(s);
        nextShort = previousShort = null;
    }
}

package ch22.ex22_02;

import java.util.Set;
import java.util.TreeSet;

public final class WhichChars {
    private final Set<Character> set = new TreeSet<>();

    public WhichChars(String str) {
        for (int i = 0; i < str.length(); i++) {
            set.add(str.charAt(i));
        }
    }
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        set.forEach(builder::append);
        builder.append("]");
        return builder.toString();
    }
}

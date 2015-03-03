package ch22.ex22_03;

import java.util.*;

public class WhichChars {
    private final Map<Integer, BitSet> set = new TreeMap<>();

    public WhichChars(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            
            int upper = (c >>> 8) & 0xff;
            int lower = c & 0xff;
            if (set.get(upper) == null) {
                set.put(upper, new BitSet());
            }
            
            set.get(upper).set(lower);
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (Map.Entry<Integer, BitSet> entry : set.entrySet()) {
            for (int i = entry.getValue().nextSetBit(0); i >= 0; i = entry.getValue().nextSetBit(i + 1)) {
                builder.append((char)((entry.getKey() << 8) | i));
            }
        }
        builder.append("]");
        return builder.toString();
    }
}

package ch22.ex22_14;

import java.util.StringTokenizer;

public class DoubleUtils {
    public static double sumOfDoublesInString(String string) {
        StringTokenizer tokenizer = new StringTokenizer(string, " ");
        double sum = 0;
        while (tokenizer.hasMoreTokens()){
            sum += Double.valueOf(tokenizer.nextToken());
        }
        return sum;
    }
}

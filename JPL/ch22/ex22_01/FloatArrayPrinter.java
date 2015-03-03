package ch22.ex22_01;

import java.io.PrintWriter;
import java.util.ArrayList;

public class FloatArrayPrinter {
    private static final int LINE_WIDTH = 80;
    
    public static void print(double[] values, int numOfRows) {
        print(values, numOfRows, new PrintWriter(System.out));
    }

    public static void print(double[] values, int numOfRows, PrintWriter out) {
        int widthPerRow = (LINE_WIDTH - numOfRows) / numOfRows;
        
        for (int i = 0; i < values.length; i++) {
            // "e+100".length == 5n
            out.printf(String.format(" %+." + widthPerRow + "e", values[i]));
            if (i % numOfRows == numOfRows - 1) {
                out.println();
            }
        }
        out.flush();
    }
    
    public static void main(String[] args) {
        FloatArrayPrinter.print(new double[]{1.0, 1.1, 1.22e10, 1.333, 1.5e-10, 1.6, 12345, 1.23456}, 3);
    }
}

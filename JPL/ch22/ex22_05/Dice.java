package ch22.ex22_05;

import java.security.SecureRandom;
import java.util.Random;

public class Dice {
    public static void main(String[] args) {
        compare(4);
    }
    
    public static void compare(int dices) {
        double[] expected = new double[6 * dices + 1];
        double[] actual = new double[6 * dices + 1];
        
        int iteration = 10000;
        
        calcExpected(expected, 0, dices);
        calcActual(actual, iteration, dices);
        
        for (int i = 0; i < expected.length; i++) { 
            expected[i] /= Math.pow(6, dices);
            actual[i] /= iteration;
        }
        
        double sumOfSquareDff = 0.0;
        for (int i = dices; i < expected.length; i++) {
            System.out.printf("%d %f (%f expected)%n", i, actual[i], expected[i]);
            sumOfSquareDff += Math.pow(actual[i] - expected[i], 2);
        }
        System.out.println(sumOfSquareDff);
    }
    
    private static void calcExpected(double[] expected, int sum, int dices) {
        if (dices <= 0) { 
            expected[sum] += 1; 
            return; 
        }
        for (int i = 1; i <= 6; i++) {
            calcExpected(expected, sum + i, dices - 1);
        }
    }
    
    private static void calcActual(double[] actual, int iteration, int dices) {
        Random r = new Random();
        for (int i = 0; i < iteration; i++) {
            int sum = 0;
            for (int d = 0; d < dices; d++) {
                sum += 1 + r.nextInt(6);
            }
            actual[sum] += 1;
        }
    }
}

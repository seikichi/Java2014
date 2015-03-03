package ch22.ex22_06;

import java.util.Random;

public class PlotGaussian {
    public static void main(String[] args) {
        int maxBarSize = 50, iteration = 100000;
        double min = -4.0, max = +4.0, step = 0.25;

        int[] freq = new int[(int)Math.ceil((max - min) / step)];
        Random random = new Random();
        for (int i = 0; i < iteration; i++) {
            double v = random.nextGaussian();
            if (v < min || max < v) { continue; }
            freq[(int)Math.floor((v - min) / step)] += 1;
        }

        int M = -1;
        for (int f : freq) {
            M = Math.max(M, f);
        }
        
        for (int i = 0; i < freq.length; i++) {
            int barSize = (int)(maxBarSize * (freq[i] / (double)M));
            System.out.printf("[%+.3f, %+.3f) |", min + step * i, min + step * (i + 1));
            for (int j = 0; j < barSize; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}

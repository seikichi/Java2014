package ch21.ex21_01;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class LineSorter {
    public static List<String> read(Reader r) {
        Scanner in = new Scanner(r);
        
        ArrayList<String> output = new ArrayList<>();
        while (in.hasNext()) {
            output.add(in.nextLine());
        }
        Collections.sort(output);
        return output;
    }
}

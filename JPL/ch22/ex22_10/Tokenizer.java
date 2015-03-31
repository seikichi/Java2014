package ch22.ex22_10;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Tokenizer {
    public static List<String> tokenize(Readable source) {
        Scanner in = new Scanner(source);
        List<String> tokens = new ArrayList<>();

        Pattern commentPattern = Pattern.compile("//.*", Pattern.MULTILINE);

        while (in.hasNext()) {
            if (in.hasNext(commentPattern)) {
                in.nextLine();
            } else {
                tokens.add(in.next());
            }
        }
        return tokens;
    }
}

package ch22.ex22_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {
    public static List<String[]> readCSVTable(Reader source) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(source);
        List<String[]> result = new ArrayList<>();

        tokenizer.whitespaceChars(',', ',');
        tokenizer.eolIsSignificant(true);
        List<String> vals = new ArrayList<>();

        while (tokenizer.nextToken() != StreamTokenizer.TT_EOF) {
            switch (tokenizer.ttype) {
                case StreamTokenizer.TT_WORD:
                    vals.add(tokenizer.sval.trim());
                    break;
                case StreamTokenizer.TT_NUMBER:
                    vals.add(Double.toString(tokenizer.nval));
                    break;
                case StreamTokenizer.TT_EOL:
                    result.add(vals.toArray(new String[0]));
                    vals = new ArrayList<>();
                    break;
                default:
                    break;
            }
        }
        return result;
    }
}

package ch22.ex22_07;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class CSVUtils {
    public static List<String[]> readCSVTable(Readable source, int numOfCells) throws IOException {
        Scanner in = new Scanner(source);
        List<String[]> vals = new ArrayList<String[]>();
        StringBuilder builder = new StringBuilder();
        builder.append("^");
        for (int i = 0; i < numOfCells; i++) {
            builder.append("(.*)");
            if (i < numOfCells - 1) {
                builder.append(",");
            }
        }
        String exp = builder.toString();
        Pattern pat = Pattern.compile(exp, Pattern.MULTILINE);
        
        while (in.hasNextLine()) {
            String line = in.findInLine(pat);
            if (line != null) {
                String[] cells = new String[numOfCells];
                MatchResult match = in.match();
                for (int i = 0; i < numOfCells; i++) {
                    cells[i] = match.group(i + 1);
                }
                vals.add(cells);
                in.nextLine(); // skip newline
            } else {
                throw new IOException("input format error");
            }
        }
        
        IOException ex = in.ioException();
        if (ex != null) { throw ex; }
        
        return vals;
    }
}

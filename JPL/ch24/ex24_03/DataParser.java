package ch24.ex24_03;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class DataParser {
    private static final int[] formats = {
            DateFormat.SHORT,
            DateFormat.MEDIUM,
            DateFormat.LONG,
            DateFormat.FULL,
    };

    private static final Locale loc = new Locale("en", "US");

    private static Date parse(String str) {
        for (int dataFormat : formats) {
            for (int timeFormat : formats) {
                DateFormat fmt = DateFormat.getDateTimeInstance(dataFormat, timeFormat, loc);
                try {
                    return fmt.parse(str);
                } catch (ParseException e) {
                    // skip
                }
            }
        }
        return null;
    }

    public static void print(String str) {
        Date date = parse(str);
        if (date == null) { return; }

        for (int dataFormat : formats) {
            for (int timeFormat : formats) {
                DateFormat fmt = DateFormat.getDateTimeInstance(dataFormat, timeFormat, loc);
                System.out.println(fmt.format(date));
            }
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            print("Friday, August 29, 1986 5:00:00 PM EDT");
            print("August 29, 1986 5:00:00 PM EDT");
            print("Aug 29, 1986 5:00:00 PM");
            print("8/29/86 5:00 PM");
        } else {
            for (String arg : args) {
                print(arg);
            }
        }
    }
}

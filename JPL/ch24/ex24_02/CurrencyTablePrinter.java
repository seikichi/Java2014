package ch24.ex24_02;

import java.util.Currency;
import java.util.Locale;

public class CurrencyTablePrinter {
    private static Locale[] locales = new Locale[] {
            Locale.JAPAN,
            Locale.TAIWAN,
            Locale.KOREA,
            Locale.CHINA,
            Locale.SIMPLIFIED_CHINESE,
            Locale.ENGLISH,
    };

    static String[] codes = new String[]{
            "JPY",
            "KRW",
            "CNY",
            "USD",
            "TWD",
            "HKD",
    };

    public static void main(String[] args) {
        for (Locale loc : locales) {
            System.out.printf(loc.toString());
            for (String code : codes) {
                System.out.printf("\t%s", Currency.getInstance(code).getSymbol(loc));
            }
            System.out.println();
        }
    }
}

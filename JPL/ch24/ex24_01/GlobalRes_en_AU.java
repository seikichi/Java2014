package ch24.ex24_01;

import java.util.ListResourceBundle;

public class GlobalRes_en_AU extends ListResourceBundle {
    private static final Object[][] contents = {
            {GlobalRes.HELLO, "G'day"},
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}

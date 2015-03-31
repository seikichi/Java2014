package ch24.ex24_01;

import java.util.*;

public class GlobalRes_ja_JP_osaka extends ResourceBundle {
    @Override
    protected Object handleGetObject(String key) {
        if (key.equals(GlobalRes.HELLO)) {
            return "まいど！";
        }
        return null;
    }

    @Override
    public Enumeration<String> getKeys() {
        List<String> keys = new ArrayList<String>() {{
            add(GlobalRes.HELLO);
        }};
        return Collections.enumeration(keys);
    }
}

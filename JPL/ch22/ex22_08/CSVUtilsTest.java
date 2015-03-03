package ch22.ex22_08;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CSVUtilsTest {
    @Test
    public void セル数を指定してCSVを読み込むことができること() throws Exception {
        StringReader csvStream = new StringReader(String.format("1,2,3,4%na,b,c,d%n"));
        List<String[]> actual = CSVUtils.readCSVTable(csvStream, 4);
        List<String[]> expected = new ArrayList<String[]>() {{
            add(new String[]{"1", "2", "3", "4"});
            add(new String[]{"a", "b", "c", "d"});
        }};
        assertThat(actual.get(0), is(expected.get(0)));
        assertThat(actual.get(1), is(expected.get(1)));
    }

    @Test(expected = IOException.class)
    public void カンマの数が多いとエラー() throws Exception {
        StringReader csvStream = new StringReader(String.format("1,2,3,4%na,b,c,d%n"));
        CSVUtils.readCSVTable(csvStream, 3);
    }

    @Test
    public void 空行を許可() throws Exception {
        StringReader csvStream = new StringReader(String.format("1,2,3,4%na,b,c,d%n%n%n"));
        List<String[]> actual = CSVUtils.readCSVTable(csvStream, 4);
        List<String[]> expected = new ArrayList<String[]>() {{
            add(new String[]{"1", "2", "3", "4"});
            add(new String[]{"a", "b", "c", "d"});
        }};
        assertThat(actual.get(0), is(expected.get(0)));
        assertThat(actual.get(1), is(expected.get(1)));
    }
}
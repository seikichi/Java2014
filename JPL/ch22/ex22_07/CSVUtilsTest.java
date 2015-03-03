package ch22.ex22_07;

import org.junit.Test;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
}
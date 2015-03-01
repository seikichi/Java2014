package ch21.ex21_01;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Theories.class)
public class LineSorterTest {

    static final class Data {
        public final String input;
        public final ArrayList<String> expected;
        Data(String input, String[] expected) {
            this.input = input;
            this.expected = new ArrayList<>();
            Collections.addAll(this.expected, expected);
        }
    }

    @DataPoints
    public static Data[] data = {
            new Data("", new String[]{}),
            new Data("aaa", new String[]{"aaa"}),
            new Data(String.format("b%na"), new String[]{"a", "b"}),
            new Data(String.format("b%na%n"), new String[]{"a", "b"}),
            new Data(String.format("a%n%na"), new String[]{"", "a", "a"}),
            new Data(String.format("0%n1%n2%n3%n4%n5%n"), new String[]{"0", "1", "2", "3", "4", "5"}),
            new Data(String.format("5%n4%n3%n2%n1%n0%n"), new String[]{"0", "1", "2", "3", "4", "5"}),
    };
    
    @Theory
    public void readは各行をソートしてListに保存すること(Data data) throws Exception {
        List<String> actual = LineSorter.read(new StringReader(data.input));
        assertThat(actual, is(data.expected));
    }
}
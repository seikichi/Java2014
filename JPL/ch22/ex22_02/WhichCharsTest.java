package ch22.ex22_02;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class WhichCharsTest {
    @Test
    public void toStringは使用された文字を返すこと() throws Exception {
        assertThat(new WhichChars("Testing 1 2 3").toString(), is("[ 123Teginst]"));
    }
}
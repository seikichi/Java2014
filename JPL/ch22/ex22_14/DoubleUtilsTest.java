package ch22.ex22_14;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DoubleUtilsTest {
    @Test
    public void 文字列を空白で分解して浮動小数点の和を返すこと() throws Exception {
        double actual = DoubleUtils.sumOfDoublesInString("1 1e-1 1e+1 0.1234");
        double expected = 1 + 1e-1 + 1e+1 + 0.1234;
        assertThat(Math.abs(actual - expected) < 1e-7, is(true));
    }
}
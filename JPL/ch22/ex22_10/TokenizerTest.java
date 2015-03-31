package ch22.ex22_10;

import org.junit.Test;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TokenizerTest {
    @Test
    public void tokenizeがコメントを無視すること() throws Exception {
        StringReader input = new StringReader(
                "ほげほげ\n" +
                "ふがふが // ぽえー\n");
        List<String> expected = new ArrayList<>();
        expected.add("ほげほげ");
        expected.add("ふがふが");
        assertThat(Tokenizer.tokenize(input), is(expected));
    }
}
package ch22.ex22_13;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AttributedImplTest {
    @Test
    public void 属性を読み込むことができること_1つの場合() throws Exception {
        StringReader in = new StringReader("x=1");
        Attributed attrs = AttributedImpl.readAttrs(in);

        assertThat(attrs.find("x").getValue(), is("1"));
    }

    @Test
    public void 属性を読み込むことができること_2つの場合() throws Exception {
        StringReader in = new StringReader(String.format("x=1%ny=hello%n"));
        Attributed attrs = AttributedImpl.readAttrs(in);

        assertThat(attrs.find("x").getValue(), is("1"));
        assertThat(attrs.find("y").getValue(), is("hello"));
    }

    @Test
    public void 属性を読み込むことができること_空白を含む場合() throws Exception {
        StringReader in = new StringReader(String.format("x=Hello, world!%n"));
        Attributed attrs = AttributedImpl.readAttrs(in);

        assertThat(attrs.find("x").getValue(), is("Hello, world!"));
    }

    @Test
    public void 属性を読み込むことができること_マルチバイト文字を含む場合() throws Exception {
        StringReader in = new StringReader(String.format("めっせーじ=こんにちは世界"));
        Attributed attrs = AttributedImpl.readAttrs(in);

        assertThat(attrs.find("めっせーじ").getValue(), is("こんにちは世界"));
    }

    @Test(expected = IOException.class)
    public void 誤って置かれた等号を検出できること() throws Exception {
        StringReader in = new StringReader(String.format("=AAAA"));
        AttributedImpl.readAttrs(in);
    }
}
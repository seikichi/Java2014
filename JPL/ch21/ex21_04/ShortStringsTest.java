package ch21.ex21_04;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class ShortStringsTest {
    public static class 空のイテレータが与えられたとき {
        private ShortStrings sut;

        @Before
        public void setUp() throws Exception {
            sut = new ShortStrings(new ArrayList<String>().listIterator(), 42);
        }
        
        @Test
        public void 要素が無いこと() throws Exception {
            assertThat(sut.hasNext(), is(false));
            assertThat(sut.hasPrevious(), is(false));
        }
        
        @Test(expected = NoSuchElementException.class)
        public void 次の要素を取得すると例外が発生すること() throws Exception {
            assertThat(sut.next(), is(nullValue()));
        }

        @Test(expected = NoSuchElementException.class)
        public void 前の要素を取得すると例外が発生すること() throws Exception {
            assertThat(sut.next(), is(nullValue()));
        }
    }
    
    public static class 条件を満たす要素が存在するとき {
        private ShortStrings sut;
        
        @Before
        public void setUp() throws Exception {
            ArrayList<String> array = new ArrayList<String>() {{
                add("!!!!!!!");
                add("first");
                add("!!!!!!!");
                add("second");
                add("!!!!!!!");
                add("!!!!!!!");
                add("last");
                add("!!!!!!!");
            }};
            sut = new ShortStrings(array.listIterator(), 6);
        }
        
        @Test
        public void nextを繰り返し呼び出したとき() throws Exception {
            assertThat(sut.next(), is("first"));
            assertThat(sut.next(), is("second"));
            assertThat(sut.next(), is("last"));
        }

        @Test(expected = NoSuchElementException.class)
        public void 次の要素が存在しない状態でnextを呼び出した場合() throws Exception {
            for (int i = 0; i < 4; i++) { sut.next(); }
        }

        @Test(expected = NoSuchElementException.class)
        public void 前の要素が存在しない状態でpreviousを呼び出した場合() throws Exception {
            sut.previous();
        }
        
        @Test
        public void nextとpreviousを交互に呼び出した場合() throws Exception {
            assertThat(sut.next(), is("first"));
            assertThat(sut.next(), is("second"));
            assertThat(sut.previous(), is("second"));
            assertThat(sut.previous(), is("first"));
            assertThat(sut.next(), is("first"));
            assertThat(sut.next(), is("second"));
            assertThat(sut.next(), is("last"));
            assertThat(sut.previous(), is("last"));
            assertThat(sut.previous(), is("second"));
            assertThat(sut.previous(), is("first"));
        }
        
        @Test
        public void removeによって正しく要素が削除されること_JPLp537() throws Exception {
            sut.next();
            sut.next();
            // 「イテレーション中にもう1つ短い文字列が残っていて...」の例
            sut.next();
            sut.hasNext();
            sut.remove();

            assertThat(sut.previous(), is("second"));
        }

        @Test(expected = IllegalStateException.class)
        public void nextを呼ぶ前にremoveを呼ぶとエラーになること() throws Exception {
            sut.remove();
        }

        @Test(expected = IllegalStateException.class)
        public void nextを呼ぶ前にsetを呼ぶとエラーになること() throws Exception {
            sut.set("illegal!");
        }

        @Test
        public void nextを呼ぶ前にaddを呼ぶとpreviousで取得できること() throws Exception {
            sut.add("zero");
            assertThat(sut.previous(), is("zero"));
        }
    }
}
package ch21.ex21_07;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.EmptyStackException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class StackTest {
    public static class 要素数0の場合 {
        private Stack<Integer> sut;

        @Before
        public void setUp() throws Exception {
            sut = new Stack<>();
        }
        
        @Test
        public void emptyがtrueを返すこと() throws Exception {
            assertThat(sut.empty(), is(true));
        }
        
        @Test(expected = EmptyStackException.class)
        public void popが例外をスローすること() throws Exception {
            sut.pop();
        }

        @Test(expected = EmptyStackException.class)
        public void peekが例外をスローすること() throws Exception {
            sut.peek();
        }

        @Test
        public void pushした要素をpeekで取り出せること() throws Exception {
            sut.push(0);
            assertThat(sut.peek(), is(0));
        }
    }

    public static class 要素数1の場合 {
        private Stack<Integer> sut;

        @Before
        public void setUp() throws Exception {
            sut = new Stack<>();
            sut.push(128);
        }

        @Test
        public void emptyがfalseを返すこと() throws Exception {
            assertThat(sut.empty(), is(false));
        }

        @Test
        public void popが値を取り出すこと() throws Exception {
            assertThat(sut.pop(), is(128));
        }

        @Test
        public void popによって要素が無くなること() throws Exception {
            sut.pop();
            assertThat(sut.empty(), is(true));
        }

        @Test
        public void peekが値を取り出すこと() throws Exception {
            assertThat(sut.peek(), is(128));
        }

        @Test
        public void peekがべき等であること() throws Exception {
            assertThat(sut.peek(), is(128));
            assertThat(sut.peek(), is(128));
            assertThat(sut.peek(), is(128));
        }

        @Test
        public void pushした要素をpeekで取り出せること() throws Exception {
            sut.push(0);
            assertThat(sut.peek(), is(0));
        }
    }
}

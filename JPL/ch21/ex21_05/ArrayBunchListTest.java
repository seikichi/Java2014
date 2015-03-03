package ch21.ex21_05;

import org.junit.Before;
import org.junit.Test;

import java.util.ListIterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ArrayBunchListTest {

    private ArrayBunchList<Integer> sut;
    private ListIterator<Integer> it;

    @Before
    public void setUp() throws Exception {
        sut = new ArrayBunchList<>(new Integer[][]{
                new Integer[]{0, 1},
                new Integer[]{},
                new Integer[]{2},
        });
        it = sut.listIterator();
    }

    @Test
    public void getが適切な値を返すこと() throws Exception {
        assertThat(sut.size(), is(3));
        assertThat(sut.get(0), is(0));
        assertThat(sut.get(1), is(1));
        assertThat(sut.get(2), is(2));
    }

    @Test
    public void setが適切に実行されること() throws Exception {
        sut.set(1, 100);
        sut.set(2, 100);
        
        assertThat(sut.size(), is(3));
        assertThat(sut.get(0), is(0));
        assertThat(sut.get(1), is(100));
        assertThat(sut.get(2), is(100));
    }
    
    @Test
    public void listIterator_nextIndexがべき等であること() throws Exception {
        assertThat(it.nextIndex(), is(0));
        assertThat(it.nextIndex(), is(0));
        assertThat(it.nextIndex(), is(0));
    }

    @Test
    public void listIterator_previousIndexがべき等であること() throws Exception {
        assertThat(it.previousIndex(), is(-1));
        assertThat(it.previousIndex(), is(-1));
        assertThat(it.previousIndex(), is(-1));
    }

    @Test
    public void listIterator_nextが正しく動作すること() throws Exception {
        ListIterator<Integer> it = sut.listIterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.nextIndex(), is(0));
        assertThat(it.next(), is(0));
        assertThat(it.hasNext(), is(true));
        assertThat(it.nextIndex(), is(1));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.nextIndex(), is(2));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void listIterator_nextの例外が正しくスローされること() throws Exception {
        for (int i = 0; i < 4; i++) {
            it.next();
        }
    }
    
    @Test
    public void listIterator_previousが正しく動作すること() throws Exception {
        for (int i = 0; i < 3; i++) {
            it.next();
        }

        assertThat(it.hasPrevious(), is(true));
        assertThat(it.previousIndex(), is(2));
        assertThat(it.previous(), is(2));

        assertThat(it.hasPrevious(), is(true));
        assertThat(it.previousIndex(), is(1));
        assertThat(it.previous(), is(1));

        assertThat(it.hasPrevious(), is(true));
        assertThat(it.previousIndex(), is(0));
        assertThat(it.previous(), is(0));

        assertThat(it.hasPrevious(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void listIterator_previousの例外が正しくスローされること() throws Exception {
        it.previous();
    }
    
    @Test
    public void listIterator_nextとpreviousを交互に呼べること() throws Exception {
        assertThat(it.next(), is(0));
        assertThat(it.next(), is(1));
        assertThat(it.previous(), is(1));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.previous(), is(2));
        assertThat(it.previous(), is(1));
        assertThat(it.previous(), is(0));
        assertThat(it.next(), is(0));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test(expected = IllegalStateException.class)
    public void listIterator_nextの前にsetを呼ぶと例外になること() throws Exception {
        it.set(42);
    }

    @Test
    public void nextの後にsetを呼ぶことができること() throws Exception {
        it.next();
        it.set(42);
        assertThat(it.previous(), is(42));
        assertThat(it.next(), is(42));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test
    public void previousの後にsetを呼ぶことができること() throws Exception {
        it.next();
        it.next();
        it.previous();
        
        it.set(42);
        assertThat(it.next(), is(42));
        assertThat(it.next(), is(2));
    }
}
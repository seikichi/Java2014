package ch21.ex21_05;

import com.sun.istack.internal.NotNull;

import java.util.AbstractList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ArrayBunchList<E> extends AbstractList<E> {
    private final E[][] arrays;
    private final int size;

    public ArrayBunchList(E[][] arrays) {
        this.arrays = arrays.clone();
        int s = 0;
        for (E[] array : arrays) {
            s += array.length;
        }
        size = s;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E get(int index) {
        int off = 0;
        for (E[] array : arrays) {
            if (index < off + array.length)
                return array[index - off];
            off += array.length;
        }
        throw new ArrayIndexOutOfBoundsException(index);
    }
    
    @Override
    public E set(int index, E value) {
        int off = 0;
        for (int i = 0; i < arrays.length; i++) {
            if (index < off + arrays[i].length) {
                E ret = arrays[i][index - off];
                arrays[i][index - off] = value;
                return ret;
            }
            off += arrays[i].length;
        }
        throw new ArrayIndexOutOfBoundsException(index);
    }
    
    @Override
    public ListIterator<E> listIterator() {
        return new ABLListIterator();
    }
    
    private class ABLListIterator implements ListIterator<E> {
        private int off = 0;
        private int array = 0;
        private int pos = 0;
        private int dir = -1;
        private boolean nextOrPreviousIsCalled = false;

        @Override
        public boolean hasNext() {
            return nextIndex() < size();
        }

        @Override
        public E next() {
            nextOrPreviousIsCalled = true;
            if (dir > 0) { pos++; }
            dir = 1;
            while (array < arrays.length && pos >= arrays[array].length) {
                off += arrays[array].length;
                array++;
                pos = 0;
            }
            if (array >= arrays.length) { throw new NoSuchElementException(); }
            return arrays[array][pos];
        }

        @Override
        public boolean hasPrevious() {
            return previousIndex() >= 0;
        }

        @Override
        public E previous() {
            nextOrPreviousIsCalled = true;
            if (dir < 0) { pos--; }
            dir = -1;
            while (array >= 0 && pos < 0) {
                array--;
                if (array < 0) { break; }
                off -= arrays[array].length;
                pos = arrays[array].length - 1;
            }
            if (array < 0) { throw new NoSuchElementException(); }
            return arrays[array][pos];
        }

        @Override
        public int nextIndex() {
            if (dir < 0) {
                return off + pos;
            }
            return off + pos + 1;
        }

        @Override
        public int previousIndex() {
            if (dir > 0) {
                return off + pos;
            }
            return off + pos - 1;
        }


        @Override
        public void set(E e) {
            if (!nextOrPreviousIsCalled) { throw new IllegalStateException(); }
            arrays[array][pos] = e;
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

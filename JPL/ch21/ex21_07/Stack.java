package ch21.ex21_07;

import java.util.ArrayList;
import java.util.EmptyStackException;

public final class Stack<E> {
    private ArrayList<E> array = new ArrayList<>();
    
    public boolean empty() { 
        return array.isEmpty(); 
    }

    public E peek() throws EmptyStackException {
        if (array.size() == 0) { throw new EmptyStackException(); }
        return array.get(array.size() - 1);
    }

    public E pop() throws EmptyStackException {
        if (array.size() == 0) { throw new EmptyStackException(); }
        E ret = array.get(array.size() - 1);
        array.remove(array.size() - 1);
        return ret;
    }
    
    public void push(E e) {
        array.add(e);
    }
}

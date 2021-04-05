package utilities;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class MyStack<E> implements StackADT<E>{

    /**
	 * generated serial id
	 */
	private static final long serialVersionUID = 8388386049414198490L;

	private MyArrayList<E> elements;

    private int top;

    private static final int DEFAULT_CAPACITY = 100;

    private int capacity;

    public MyStack() {
        capacity = DEFAULT_CAPACITY;
        elements = new MyArrayList<>(capacity);
        top = -1;
    }

    @Override
    public void push(E toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException();
        }
        top++;
        elements.add(toAdd);
    }

    @Override
    public E pop() throws EmptyStackException {
        if (isEmpty()) {
            throw  new EmptyStackException();
        }
        return elements.remove(top--);
    }

    @Override
    public E peek() throws EmptyStackException {
        if (isEmpty()) {
            throw  new EmptyStackException();
        }
        return elements.get(top);
    }

    @Override
    public void clear() {
        elements.clear();
        top = -1;
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public Object[] toArray() {
        Object[] copy = new Object[size()];
        for (int i=0; i< copy.length; i++) {
            copy[i] = elements.get(top-i);
        }
        return copy;
    }

    @SuppressWarnings("unchecked")
	@Override
    public E[] toArray(E[] holder) throws NullPointerException {
        if (holder == null) {
            throw new NullPointerException();
        }
        Object[] result = holder.length >= top+1 ? holder : new Object[top+1];
        for (int i=0; i< size(); i++) {
            result[i] = elements.get(top-i);
        }
        return (E[]) result;
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        int i=0;
        if (toFind == null) {
            throw new NullPointerException();
        }
        while (i <= top && !toFind.equals(elements.get(i))) {
                i++;
            }
        return i <= top;
    }

    @Override
    public int search(E toFind) {
        int pointer = top;
        while (pointer>=0 && !elements.get(pointer).equals(toFind)) {
            pointer--;
        }
        if (pointer>=0) {
            pointer=top-pointer+1;
        }
        return pointer;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iter<>();
    }

    @Override
    public boolean equals(StackADT<E> that) {
        Iterator<E> comparingStackIterator = that.iterator();
        if (that.size() != size()) {
            return false;
        }
        int i = top;
        while (comparingStackIterator.hasNext() ) {
            if (!elements.get(i).equals(comparingStackIterator.next())) {
                return false;
            }
            i--;
        }
        return true;
    }

    @Override
    public int size() {
        return top+1;
    }

    private class Iter<E> implements Iterator<E> {

        private int cursor;

        public Iter() {
            cursor = top;
        }

        @Override
        public boolean hasNext() {
            return cursor>=0;
        }

        @SuppressWarnings("unchecked")
		@Override
        public E next() throws NoSuchElementException {
            if (cursor<0) {
                throw new NoSuchElementException();
            }
            E elem = (E) elements.get(cursor);
            cursor--;
            return elem;
        }
    }
}

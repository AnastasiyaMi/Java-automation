package utilities;

import java.util.NoSuchElementException;

public class MyArrayList<E> implements ListADT<E>{

    /**
	 * generated serial ID
	 */
	private static final long serialVersionUID = -3450076184594420961L;

	private Object[] elements;

    private int size;

    private static final int DEFAULT_CAPACITY = 100;

    private int capacity;

    public MyArrayList() {
        capacity = DEFAULT_CAPACITY;
        size = 0;
        elements = new Object[capacity];
    }

    public MyArrayList(int initialCapacity) {
        capacity = initialCapacity;
        size = 0;
        elements = new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            elements[i] = null;
        size = 0;
    }

    private void boundaryCheck(int index) {
        if (index >= size || index < 0) {
            throw  new IndexOutOfBoundsException();
        }
    }

    private void boundaryCheckForInsert(int index) {
        if (index > size || index < 0) {
            throw  new IndexOutOfBoundsException();
        }
    }

    @Override
    public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
        boundaryCheckForInsert(index);
        for (int i = size; i>index; i--) {
            elements[i] = elements[i-1];
        }
        elements[index] = toAdd;
        size++;
        return true;
    }

    @Override
    public boolean add(E toAdd) throws NullPointerException, IndexOutOfBoundsException {
        if (size == capacity) {
            Object[] oldStorage = elements;
            capacity+=DEFAULT_CAPACITY;
            elements = new Object[capacity];
            for (int i=0;i<oldStorage.length; i++) {
                elements[i] = oldStorage[i];
                oldStorage[i] = null;
            }
        }
        elements[size] = toAdd;
        size++;
        return true;
    }

    @Override
    public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
        if (toAdd.size() + size > capacity) {
            Object[] oldStorage = elements;
            capacity = ((toAdd.size() + size)/DEFAULT_CAPACITY+1)*DEFAULT_CAPACITY;
            elements = new Object[capacity];
            for (int i=0;i<oldStorage.length; i++) {
                elements[i] = oldStorage[i];
                oldStorage[i] = null;
            }
        }
        Iterator<? extends E> iter = toAdd.iterator();
        while (iter.hasNext()) {
            elements[size] = iter.next();
            size++;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
	@Override
    public E get(int index) throws IndexOutOfBoundsException {
        boundaryCheck(index);
        return (E) elements[index];
    }

    @SuppressWarnings("unchecked")
	@Override
    public E remove(int index) throws IndexOutOfBoundsException {
        boundaryCheck(index);
        E deletedElement = (E) elements[index];
        for (int i=index;i<size;i++) {
            elements[i]=elements[i+1];
        }
        elements[size]=null;
        size--;
        return deletedElement;
    }

    @Override
    public E remove(E toRemove) throws NullPointerException {
        int i=0;
        if (toRemove == null) {
            while (i<size && elements[i]!= null) {
                i++;
            }
        } else {
            while (i < size && !toRemove.equals(elements[i])) {
                i++;
            }
        }
        if (i<size) {
            return remove(i);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
	@Override
    public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        boundaryCheck(index);
        E tmp = (E) elements[index];
        elements[index] = toChange;
        return tmp;
    }

    @Override
    public boolean isEmpty() {
        return size  == 0;
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        int i=0;
        if (toFind == null) {
            while (i<size && elements[i] != null) {
                i++;
            }
        } else {
            while (i < size && !toFind.equals(elements[i])) {
                i++;
            }
        }
        return i < size;
    }

    @SuppressWarnings("unchecked")
	@Override
    public E[] toArray(E[] toHold) throws NullPointerException {
        if (toHold == null) {
            throw new NullPointerException();
        }
        Object[] result = toHold.length >= size ? toHold : new Object[size];
        /*for (int i=0;i< size; i++) {
            result[i] = elements[i];
        }*/
        System.arraycopy(elements, 0, result, 0, size);
        return (E[]) result;
    }

    @Override
    public Object[] toArray() {
        Object[] copy = new Object[size];
        System.arraycopy(elements, 0, copy, 0, size);
        return copy;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iter<>();
    }

    private class Iter<E> implements Iterator<E> {

        private int cursor;

        public Iter() {
            cursor = 0;
        }

        @Override
        public boolean hasNext() {
            return cursor<size;
        }

        @SuppressWarnings("unchecked")
		@Override
        public E next() throws NoSuchElementException {
            if (cursor>=size) {
                throw new NoSuchElementException();
            }
            E elem = (E) elements[cursor];
            cursor++;
            return elem;
        }
    }
}

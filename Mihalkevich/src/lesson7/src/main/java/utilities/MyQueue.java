package utilities;

import exceptions.EmptyQueueException;

import java.util.NoSuchElementException;

public class MyQueue<E> implements QueueADT<E> {

    /**
	 * generated serial ID
	 */
	private static final long serialVersionUID = 2910584692223232608L;

	private MyDLL<E> elements;

    private int count;

    public MyQueue() {
        elements = new MyDLL<>();
    }

    @Override
    public void enqueue(E toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException();
        }
        if (isFull()) {
            throw new RuntimeException("Queue is full");
        }
        elements.add(toAdd);
        count++;
    }

    @Override
    public E dequeue() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        E firstElement = elements.remove(0);
        count--;
        return firstElement;
    }

    @Override
    public E peek() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        return elements.get(0);
    }

    @Override
    public void dequeueAll() {
        elements.clear();
        count = 0;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iter<>();
    }

    private class Iter<E> implements Iterator<E> {

        private Iterator<E> internalIterator;

        public Iter() {
            internalIterator = (Iterator<E>) elements.iterator();
        }

        @Override
        public boolean hasNext() {
            return internalIterator.hasNext();
        }

        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return internalIterator.next();
        }
    }

    @Override
    public boolean equals(QueueADT<E> that) {
        if (size() != that.size()) {
            return false;
        }
        Iterator<E> iterOther = that.iterator();
        Iterator<E> iterOur = iterator();
        while (iterOur.hasNext()) {
            if (!iterOur.next().equals(iterOther.next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object[] toArray() {
        return elements.toArray();
    }

    @Override
    public E[] toArray(E[] holder) throws NullPointerException {
        if (holder == null) {
            throw new NullPointerException();
        }
        return elements.toArray(holder);
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int size() {
        return count;
    }
}

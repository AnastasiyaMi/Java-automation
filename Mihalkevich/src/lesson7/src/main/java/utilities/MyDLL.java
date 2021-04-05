package utilities;

import java.util.NoSuchElementException;

public class MyDLL<E> implements ListADT<E> {

    /**
	 * generated serial ID
	 */
	private static final long serialVersionUID = 5077956809119040791L;

	private MyDLLNode<E> first;

    private MyDLLNode<E> last;

    public MyDLL() {
        first = null;
        last = null;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @SuppressWarnings("unchecked")
	@Override
    public E[] toArray(E[] toHold) throws NullPointerException {
        if (toHold == null) {
            throw new NullPointerException();
        }
        int size = size();
        MyDLLNode<E> cursor = first;
        Object[] result = (toHold.length >= size) ? toHold : new Object[size];
        int i=0;
        while (cursor != null) {
            result[i] = cursor.getValue();
            i++;
            cursor = cursor.getNext();
        }
        return (E[]) result;
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        if (toFind == null) {
            throw new NullPointerException();
        }
        MyDLLNode<E> cursor = first;
        while (cursor != null) {
            if (cursor.valueMatch(toFind)) {
                return true;
            }
            cursor = cursor.getNext();
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iter<>();
    }

    private class Iter<E> implements Iterator<E> {

        MyDLLNode<E> cursor;

        public Iter() {
            cursor = (MyDLLNode<E>) first;
        }

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw  new NoSuchElementException();
            }
            MyDLLNode<E> result = cursor;
            cursor = cursor.getNext();
            return result.getValue();
        }
    }

    @Override
    public Object[] toArray() {
        int size = size();
        Object[] result = new Object[size];
        MyDLLNode<E> node = first;
        int index=0;
        while (node != null) {
            result[index] = node.getValue();
            node = node.getNext();
            index++;
        }
        return result;
    }

    @Override
    public boolean add(E toAdd) throws NullPointerException, IndexOutOfBoundsException {
        if (toAdd == null) {
            throw new NullPointerException();
        }
        MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);
        newNode.setPrev(last);
        if (last != null) {
            last.setNext(newNode);
        } else {
            first = newNode;
        }
        last = newNode;
        return true;
    }

    @Override
    public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException();
        }
        Iterator<? extends E> iter = toAdd.iterator();
        while (iter.hasNext()) {
            boolean additionResult = add(iter.next());
            if (!additionResult) {
                return false;
            }
        }
        return true;
    }

    @Override
    public E remove(E toRemove) throws NullPointerException {
        if (toRemove == null) {
            throw new NullPointerException();
        }
        MyDLLNode<E> cursor = first;
        while (cursor != null) {
            if (cursor.valueMatch(toRemove)) {
                if (cursor.getPrev() != null) {
                    cursor.getPrev().setNext(cursor.getNext());
                }
                if (cursor.getNext() != null) {
                    cursor.getNext().setPrev(cursor.getPrev());
                }
                break;
            }
            cursor = cursor.getNext();
        }
        return cursor.getValue();
    }

    @Override
    public void clear() {
        MyDLLNode<E> cursor = first;
        if (cursor != null) {
            cursor =cursor.getNext();
        }
        while (cursor != null) {
            cursor.getPrev().setNext(null);
            cursor.setPrev(null);
            cursor = cursor.getNext();
        }
        first = null;
        last = null;
    }


    @Override
    public int size() {
        int size = 0;
        MyDLLNode<E> cursor = first;
        while (cursor != null) {
            cursor = cursor.getNext();
            size++;
        }
        return size;
    }

    @Override
    public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
        if (toAdd == null) {
            throw new NullPointerException();
        }
        MyDLLNode<E> addingNode = new MyDLLNode<>(toAdd);
        if (index == 0) {
            first.setPrev(addingNode);
            addingNode.setNext(first);
            first = addingNode;
        }
        int position = 0;
        MyDLLNode<E> cursor = first;
        while (position<index && cursor != null) {
            cursor = cursor.getNext();
            position++;
        }
        if (position<index) {
            throw new IndexOutOfBoundsException();
        }
        if (cursor == null) {
            return add(toAdd);
        }
        addingNode.setNext(cursor);
        addingNode.setPrev(cursor.getPrev());
        cursor.getPrev().setNext(addingNode);
        cursor.setPrev(addingNode);
        return true;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        int position = 0;
        MyDLLNode<E> cursor = first;
        while (position<index && cursor != null) {
            cursor = cursor.getNext();
            position++;
        }
        if (cursor == null) {
            throw new IndexOutOfBoundsException();
        }
        return cursor.getValue();
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        int position = 0;
        MyDLLNode<E> cursor = first;
        while (position<index && cursor != null) {
            cursor = cursor.getNext();
            position++;
        }
        if (cursor == null) {
            throw new IndexOutOfBoundsException();
        }
        if (cursor.getPrev() != null) {
            cursor.getPrev().setNext(cursor.getNext());
        }  else {
            first = first.getNext();
        }
        if (cursor.getNext() != null) {
            cursor.getNext().setPrev(cursor.getPrev());
        } else {
            last = last.getPrev();
        }
        return cursor.getValue();
    }

    @Override
    public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        if (toChange == null) {
            throw new NullPointerException();
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        int position = 0;
        MyDLLNode<E> cursor = first;
        while (position<index && cursor != null) {
            cursor = cursor.getNext();
            position++;
        }
        if (cursor == null) {
            throw new IndexOutOfBoundsException();
        }
        E oldValue = cursor.getValue();
        cursor.setValue(toChange);
        return oldValue;
    }

}

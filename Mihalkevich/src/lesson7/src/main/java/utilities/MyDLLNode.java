package utilities;

public class MyDLLNode<E> {

    private E value;

    private MyDLLNode<E> prev;

    private MyDLLNode<E> next;

    public MyDLLNode(E value) {
        this.value = value;
        prev = null;
        next = null;
    }

    public MyDLLNode(E value, MyDLLNode<E> prev, MyDLLNode<E> next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
    }

    public MyDLLNode<E> getNext() {
        return next;
    }

    public void setNext(MyDLLNode<E> next) {
        this.next = next;
    }

    public MyDLLNode<E> getPrev() {
        return prev;
    }

    public void setPrev(MyDLLNode<E> prev) {
        this.prev = prev;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public boolean equals(MyDLLNode<?> other) {
        return value.equals(other.value);
    }

    public boolean valueMatch(E targetValue) {
        return value.equals(targetValue);
    }
}

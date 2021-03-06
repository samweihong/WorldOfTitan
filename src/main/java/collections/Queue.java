package collections;

import java.util.LinkedList;

public class Queue<E> {
    LinkedList<E> queue = new LinkedList<>();

    public Queue() {
    }

    public void enqueue(E e) {
        queue.add(e);
    }

    public E dequeue() {
        if (isEmpty()) return null;
        return queue.removeFirst();
    }

    public E getElement(int i) {
        if (i < 0 || i >= getSize()) return null;
        return queue.get(i);
    }

    public E peek() {
        if (isEmpty()) return null;
        return queue.getFirst();
    }

    public int getSize() {
        return queue.size();
    }

    public boolean contains(E e) {
        return queue.contains(e);
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public String toString() {
        return "Queue: " + queue.toString();
    }

    public E remove(int index){ return queue.remove(index); }
    public boolean remove(E e){ return queue.remove(e); }
}

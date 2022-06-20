package collections;

import java.util.LinkedList;

public class PriorityQueue<E extends Comparable<E>> extends Queue<E> {

    private LinkedList<E> list;

    public PriorityQueue() {
        list = new LinkedList<>();
    }

    public void enqueue(E e) {
        boolean add = false;
        for (int i = 0; i < list.size(); i++) {
            if (e.compareTo(list.get(i)) < 0) {
                list.add(i, e);
                add = true;
                break;
            }
        }
        if (!add) list.addLast(e);
    }

    public E dequeue() {
        if (list.isEmpty()) return null;
        return list.removeFirst();
    }


    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int getSize() { return list.size(); }

    @Override
    public E getElement(int i) {
        if (i < 0 || i >= getSize()) return null;
        return list.get(i);
    }
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < list.size(); i++) { result.append(list.get(i).toString()).append("\n"); }
        return result.toString();
    }

    @Override
    public E peek() {
        return list.peek();
    }
}

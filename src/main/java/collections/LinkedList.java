package collections;

import data_objects.GameCharacter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Iterator;

public class LinkedList<E> implements Iterable<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            Node<E> current = head;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E currentElement = current.element;
                current = current.next;
                return currentElement;
            }
        };
    }

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }

    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(E e) {
        Node<E> newHead = new Node<>(e);
        newHead.next = head;
        head = newHead;
        if(tail == null) tail = head;
        size++;
    }

    public void addLast(E e) {
        Node<E> newTail = new Node<>(e);
        if (tail == null)
            head = tail = newTail;
        else {
            tail.next = newTail;
            tail = newTail;
        }
        size++;
    }

    public void add(int index, E e) {
        if (index == 0) addFirst(e);
        else if (index >= size) addLast(e);
        else {
            Node<E> current = head;
            for(int i = 1; i < index; i++)
                current = current.next;
            Node<E> newNode = new Node<>(e);
            newNode.next = current.next;
            current.next = newNode;
            size++;
        }
    }

    public E removeFirst() {
        if (size == 0) return null;

        E element = head.element;
        head = head.next;
        if (head == null) tail = null;
        size--;
        return element;
    }

    public E removeLast() {
        if (size == 0) return null;
        if (size == 1) return removeFirst();

        E element = tail.element;
        Node<E> current = head;
        while (current.next.next != null)
            current = current.next;
        current.next = null;
        tail = current;
        size--;
        return element;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) return null;
        if (index == 0) return removeFirst();
        if (index == size - 1) return removeLast();

        Node<E> current = head;
        for (int i = 1; i < index; i++)
            current = current.next;
        E element = current.next.element;
        current.next = current.next.next;
        size--;
        return element;
    }

    public void add(E e) {
        addLast(e);
    }

    public boolean contains(E e) {
        for (Node<E> current = head; current != null; current = current.next)
            if (current.element.equals(e))
                return true;
        return false;
    }

    public E getFirst() {
        if (head == null) return null;
        return head.element;
    }

    public E getLast() {
        if (tail == null) return null;
        return tail.element;
    }

    public E get(int index) {
        if (index < 0 || index >= size) return null;
        if (index == 0) return getFirst();
        if (index == size - 1) return getLast();

        Node<E> current = head;
        for (int i = 0; i < index; i++)
            current = current.next;
        return current.element;
    }

    public int indexOf(E e) {
        int i = 0;
        for (Node<E> current = head; current != null; current = current.next) {
            if (e.equals(current.element))
                return i;
            i++;
        }
        return -1;
    }

    public int lastIndexOf(E e) {
        int index = -1;
        int i = 0;
        for (Node<E> current = head; current != null; current = current.next) {
            if (e.equals(current.element))
                index = i;
            i++;
        }
        return index;
    }

    public E set(int index, E e) {
        if(index < 0 || index > size) return null;
        if(index == size) addLast(e);
        Node<E> current = head;
        while (index-- > 0)
            current = current.next;
        E temp = current.element;
        current.element = e;
        return temp;
    }

    public void clear() {
        size = 0;
        head = tail = null;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Node<E> current = head; current != null; current = current.next)
            str.append(current.element).append("\n");
        return str.toString();
    }

    public Object[] toArray() {
        Object[] a = new Object[size];
        int i = 0;
        for (Node<E> current = head; current != null; current = current.next)
            a[i++] = current.element;
        return a;
    }

    @SuppressWarnings("unchecked")
    public E[] toArray(E[] a) {
        if (a.length < size)
            a = (E[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);

        int i = 0;
        for (Node<E> current = head; current != null; current = current.next)
            a[i++] = current.element;
        return a;
    }

    @SuppressWarnings("ConstantConditions")
    public LinkedList<E> binarySearchList(String attribute, int value) {
        LinkedList<E> outputList = new LinkedList<>();
        Node<E> firstNode = binarySearch(attribute, value, true);
        if (firstNode == null) return outputList;
        Node<E> lastNode = binarySearch(attribute, value, false);
        for (Node<E> node = firstNode; node != lastNode.next; node = node.next)
            outputList.add(node.element);
        return outputList;
    }

    private Node<E> binarySearch(String attribute, int value, boolean first) {
        Node<E> start = head;
        Node<E> last = null;
        Node<E> node = null;
        try {
            Method m = GameCharacter.class.getMethod(attribute.toLowerCase());
            while (start != last) {
                Node<E> mid = getMiddle(start, last);
                if (mid == null) return null;
                // If value is present at mid
                if (m.invoke(mid.element).equals(value)) {
                    node = mid;
                    if (first) last = mid;
                    else start = mid.next;
                }
                // If the value is less than mid
                else if (value < (Integer) m.invoke(mid.element)) start = mid.next;
                // If the value is more than mid
                else last = mid;
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return node;
    }

    public void sort(Comparator<? super E> comp) {
        head = sort(head, comp);
        Node<E> tail = head;
        while (tail.next != null)
            tail = tail.next;
    }

    private Node<E> sort(Node<E> head, Comparator<? super E> comp) {
        // Base case : if head is null
        if (head == null || head.next == null) return head;
        // Get the middle of the list
        Node<E> middle = getMiddle(head);
        Node<E> nextOfMiddle = middle.next;
        // Set the next of middle node to null
        middle.next = null;
        // Apply mergeSort on left list
        Node<E> left = sort(head, comp);
        // Apply mergeSort on right list
        Node<E> right = sort(nextOfMiddle, comp);
        // Merge the left and right lists
        return merge(left, right, comp);
    }

    private Node<E> merge(Node<E> a, Node<E> b, Comparator<? super E> comp) {
        /* Base cases */
        if (a == null) return b;
        if (b == null) return a;
        /* Pick either a or b, and recurse */
        if (comp.compare(a.element, b.element) < 0) {
            a.next = merge(a.next, b, comp);
            return a;
        } else {
            b.next = merge(a, b.next, comp);
            return b;
        }
    }

    // Utility function to get the middle of the linked list
    private Node<E> getMiddle(Node<E> head) {
        if (head == null) return null;
        Node<E> slow = head;
        Node<E> fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private Node<E> getMiddle(Node<E> head, Node<E> tail) {
        if (head == null) return null;
        Node<E> slow = head;
        Node<E> fast = head;
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}

import java.util.Comparator;

public class LinkedList<E>{
    public Node<E> head;
    private Node<E> tail;
    private int size;

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node() {}

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

    public void addFirst(E e) {
        Node<E> newHead = new Node<>(e);
        newHead.next = head;
        head = newHead;
        size++;
        if(tail == null) tail = head;
    }

    public void addLast(E e) {
        Node<E> newTail = new Node<>(e);
        if(tail == null) {
            head = tail = newTail;
        }
        else {
            tail.next = newTail;
            tail = newTail;
        }
        size++;
    }

    public void add(int index, E e) {
        if(index == 0) {
            addFirst(e);
        }
        else if(index >= size) {
            addLast(e);
        }
        else {
            Node<E> current = head;
            for(int i=1; i<index; i++) {
                current = current.next;
            }
            Node<E> newNode = new Node<>(e);
            newNode.next = current.next;
            current.next = newNode;
            size++;
        }
    }

    public E removeFirst() {
        if(size == 0) return null;
        else {
            E element = head.element;
            head = head.next;
            size--;
            if (head == null) tail = null;
            return element;
        }
    }

    public E removeLast() {
        if(size == 0) return null;
        else if (size == 1) return removeFirst();
        else {
            E element = tail.element;
            Node<E> current = head;
            while (current.next.next != null) {
                current = current.next;
            }
            current.next = null;
            tail = current;
            size--;
            return element;
        }
    }

    public E remove(int index) {
        if(index < 0 || index >= size) return null;
        else if(index == 0) return removeFirst();
        else if(index == size - 1) return removeLast();
        else {
            Node<E> current = head;
            for (int i=1; i < index; i++) {
                current = current.next;
            }
            E element = current.next.element;
            current.next = current.next.next;
            size--;
            return element;
        }
    }

    public void add(E e) {
        addLast(e);
    }

    public boolean contains(E e) {
        Node<E> current = head;
        for(int i=0; i<size; i++) {
            if(current.element.equals(e)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public E getFirst() {
        if(head == null) return null;
        return head.element;
    }

    public E getLast() {
        if(tail == null) return null;
        return tail.element;
    }

    public E get(int index) {
        if (index < 0 || index >= size) return null; //invalid index
        else if (index == 0) return getFirst();
        else if (index == size - 1) return getLast();
        else {
            Node<E> current = head;
            for (int i=0; i<index; i++)
                current = current.next;
            return current.element;
        }
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
        String str = "";
        for (Node<E> current = head; current != null; current = current.next)
            str += current.element + "\n";
        System.out.println();
        return str;
    }

//    public Node<E> binarySearch(E value) {
//        Node<E> start = head;
//        Node<E> last = null;
//        do {
//            Node<E> mid = getMiddle(head);
//            if (mid == null) {
//                return null;
//            }
//            if (mid.element.toString().equals(value.toString())) {
//                return mid;
//            } else if (mid.element.compareTo(value) > 0) {
//                start = mid.next;
//            } else {
//                last = mid;
//            }
//        } while (last == null || last != start);
//        return null;
//    }

    public void sortList(Comparator<E> comp) {
        head = mergeSort(head, comp);
    }

    public Node<E> mergeSort(Node<E> h, Comparator<E> comp) {
        // Base case : if head is null
        if (h == null || h.next == null) return h;
        // Get the middle of the list
        Node<E> middle = getMiddle(h);
        Node<E> nextOfMiddle = middle.next;
        // Set the next of middle node to null
        middle.next = null;
        // Apply mergeSort on left list
        Node<E> left = mergeSort(h, comp);
        // Apply mergeSort on right list
        Node<E> right = mergeSort(nextOfMiddle, comp);
        // Merge the left and right lists
        Node<E> sortedlist = sortedMerge(left, right, comp);
        return sortedlist;
    }

    private Node<E> sortedMerge(Node<E> a, Node<E> b, Comparator<E> comp) {
        Node<E> result = null;
        /* Base cases */
        if (a == null) return b;
        if (b == null) return a;
        /* Pick either a or b, and recur */
        if (comp.compare(a.element, b.element) < 0) {
            result = a;
            result.next = sortedMerge(a.next, b, comp);
        } else {
            result = b;
            result.next = sortedMerge(a, b.next, comp);
        }
        return result;
    }

    // Utility function to get the middle of the linked list
    private Node<E> getMiddle(Node<E> head) {
        if (head == null) return head;
        Node<E> slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}

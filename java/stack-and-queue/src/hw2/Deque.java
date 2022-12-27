import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Deque<Item> implements Iterable<Item>  {

    private Node head; // head of deque
    private Node tail; // tail of deque
    private int size;

    private class Node  {
        Item value;
        Node next;
        Node prev;

        Node(Item item) {
            this.value = item;
            this.next = null;
            this.prev = null;
        }
    }
    // construct an empty deque
    public Deque()  {
        head = null;
        tail = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() { return size == 0; }

    // return the number of items on the deque
    public int size() { return size; }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("item cannot be null");
        Node n = new Node(item);
        if (size == 0) tail = n;
        else  {
            n.next = head;
            head.prev = n;
        }
        head = n;
        size++;

    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("item cannot be null");
        Node n = new Node(item);
        if (size == 0) head = n;
        else  {
            tail.next = n;
            n.prev = tail;
        }
        tail = n;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (this.isEmpty()) throw new NoSuchElementException("deque is empty");
        Item first = head.value;
        head = head.next;

        if (head != null) head.prev = null;
        else tail = head;
        size--;
        return first;
    }
    public Item removeLast() {
        if (this.isEmpty()) throw new NoSuchElementException("deque is empty");
        Item last = tail.value;
        tail = tail.prev;
        if (tail != null) tail.next = null;
        else head = tail;
        size--;
        return last;

    }
    public Iterator<Item> iterator()  { return new DequeIterator(); }
    private class DequeIterator implements Iterator<Item> {
        private Node iterNext = head;

        @Override
        public boolean hasNext()  {
            return iterNext != null;
        }

        @Override
        public Item next()  {
            if (iterNext == null) throw new NoSuchElementException();
            Item item = iterNext.value;
            iterNext = iterNext.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }


    }

    // unit testing
    public static void main(String[] args) {
        Deque<Integer> d = new Deque<Integer>();
        StdOut.print("test isEmpty: ");
        StdOut.println(d.isEmpty());
        // test addFirst, addLast
        int n = StdIn.readInt();
        int m = StdIn.readInt();

        for (int i = 0; i < n; i++) {
            d.addFirst(StdIn.readInt());
        }
        for (int i = 0; i < m; i++) {
            d.addLast(StdIn.readInt());
        }


        int[] test = new int[] {9, 3, 2, -8, 10};
        // test size
        StdOut.print("\ntest size: ");
        StdOut.println(d.size == test.length);
        // test iterator
        StdOut.println("test iterator: ");
        int i = 0;
        Iterator<Integer> iterator = d.iterator();
        while (iterator.hasNext() && i < test.length) {
            StdOut.print("next element: ");
            StdOut.println(iterator.next() == test[i++]);
        }
        // test removeFirst
        StdOut.print("test removeFirst: ");
        int first = d.removeFirst();
        if (test.length > 0) StdOut.println(first == test[0]);
        d.addFirst(first);

        // test removeLast
        StdOut.print("test removeLast: ");
        int last = d.removeLast();
        if (test.length > 0) StdOut.println(last == test[test.length-1]);
        d.addLast(last);


    }

}

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
public class RandomizedQueue<Item>implements Iterable<Item> {
    private Item[] queue;
    private int next;
    private final static int SHRINK_LIMIT = 4;
    private final static int RESIZE_FACTOR = 2;

    // construct an empty randomized queue
    public RandomizedQueue() {
        this.queue = (Item[]) new Object[1];
        this.next = 0;

    }

    // is the randomized queue empty?
    public boolean isEmpty() { return next == 0;}

    // return the number of items on the queue
    public int size() { return next ;}

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("item cannot be null");
        if (next == queue.length) queue = resize(next*RESIZE_FACTOR);
        queue[next++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (next == 0) throw new NoSuchElementException("empty queue");
        int i = StdRandom.uniform(next);
        Item item = queue[i];
        queue[i] = null;
        queue[i] = queue[--next];
        queue[next] = null;
        if (next > 1 && next == queue.length/SHRINK_LIMIT) queue = resize(queue.length/RESIZE_FACTOR);
        return item;
    }

    // return a random item but not remove it
    public Item sample() {
        if (next == 0) throw new NoSuchElementException("empty queue");
        return queue[StdRandom.uniform(next)];
    }

    private Item[] resize(int capacity) {
        Item[] newQueue = (Item[]) new Object[capacity];
        for (int i=0; i < next; i++) {
            newQueue[i] = queue[i];
        }
        return newQueue;
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int iterNext;
        private int[] ordering;
        public RandomizedQueueIterator() {
            ordering = StdRandom.permutation(next);
            iterNext = 0;
        }

        @Override
        public boolean hasNext() {
            return iterNext != next;
        }

        @Override
        public Item next() {
            if (iterNext == queue.length) throw new NoSuchElementException();
            int id =  ordering[iterNext++];
            return queue[id];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main (String[] args) {
        // test initalize queue
        RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();

        // test size
        StdOut.print("test isEmpty: ");
        StdOut.println(q.isEmpty());

        // test enqueue
        StdOut.print("n: ");
        int n = StdIn.readInt();
        for (int i = 0; i < n; i++) {
            q.enqueue(StdIn.readInt());
        }
        StdOut.println(q.size() == n);



        // test sample
        StdOut.println("sample: " + q.sample());

        // test iterator

        for (Integer integer : q) {
            StdOut.print(integer + " ");
        }
        StdOut.println();
        for (Integer integer : q) {
            StdOut.print(integer + " ");
        }
        StdOut.println();
        // test dequeue
        for (int i =0; i < n; i++) {
            StdOut.print(q.dequeue() + " ");
        }
	    StdOut.println(q.isEmpty());

    }

}

import java.util.Iterator;

/**
 * Implementation of stacks with generics objects.
 * @param <Item> Generic data type
 *
 */
public class FixedCapacityStack<Item> implements Iterable<Item>{
    private Item[] stack;
    private int next;
    private int size;

    public FixedCapacityStack(){
        this.stack = (Item[]) new Object[1];
        this.next = 0;
        this.size = 0;
    }

    public Iterator<Item> iterator() {return new FixedCapacityStackIterator();}

    private class FixedCapacityStackIterator implements Iterator<Item>{
        private int iterNext = next-1;

        @Override
        public boolean hasNext() {
            return iterNext >= 0;
        }

        @Override
        public Item next() {
            return stack[iterNext--];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("unsupported operation");
        }
    }

    public boolean isEmpty() {return size == 0;}

    public void push (Item item){
        if (next >= stack.length) stack = resize(2*size);
        stack[next++] = item;
        size++;
    }
    public Item pop(){
        if (size == 0) return null;
        if (next == stack.length/4) stack = resize(stack.length/2);
        Item item = stack[--next];
        stack[next] = null;
        size--;
        return item;
    }

    public Item peek(){
        if (next == 0) return null;
        return stack[next-1];
    }

    private Item[] resize(int newCapacity){
        Item[] newArr = (Item[]) new Object[newCapacity];
        for (int i = 0; i < this.next; i++){
            newArr[i] = this.stack[i];
        }
        return newArr;
    }
}

import java.util.Iterator;

public class LinkedStack<Item> implements Iterable<Item> {
    private Node head; // head of stack
    private class Node{
        Item value;
        Node next;
        Node(Item value){
            this.value = value;
            this.next = null;
        }
    }
    public Iterator<Item> iterator(){
        return new LinkedStackIterator();
    }

    private class LinkedStackIterator implements Iterator<Item>{
        private Node iterNext = head;
        @Override
        public boolean hasNext(){
            return iterNext != null;
        }

        @Override
        public Item next() {
            Item item = iterNext.value;
            iterNext = iterNext.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("unsupported operation");
        }
    }

    public LinkedStack(){};
    public boolean isEmpty() {
        return head == null;
    }

    public Item pop(){
        Item s = head.value;
        head = head.next;
        return s;
    }

    public void push(Item s){
        Node node = new Node(s);
        node.next = head;
        head = node;
    }
}

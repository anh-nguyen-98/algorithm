public class LinkedQueueOfStrings implements QueueOfStrings{
    private Node head;
    private Node tail;
    private int size;

    private class Node{
        public String value;
        public Node next;

        public Node(String value){
            this.value = value;
            this.next = null;
        }
    }

    public LinkedQueueOfStrings(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty() {return size == 0;}

    public void enqueue(String s){
        if (s == null) throw new IllegalArgumentException("item cannot be empty");
        Node node = new Node(s);
        if (this.isEmpty()){
            this.head = node;
        } else{
            this.tail.next = node;
        }
        this.tail = node;
        this.size++;
    }

    public String dequeue(){
        if (this.isEmpty()) throw new IllegalArgumentException();
        String s = this.head.value;
        this.head = this.head.next;
        this.size--;
        if (this.isEmpty()) this.tail = null;
        return s;
    }

    public int size(){return this.size;}
}

/**
 * Linked-list implementation of Stack
 * Pop/push ~ remove/add to front of linked list
 *
 * @author NGUYEN HOANG NAM ANH
 */
public class LinkedStackOfStrings implements StackOfStrings {
    private Node head; // head of stack
    private class Node{
        String value;
        Node next;
        Node(String value){
            this.value = value;
            this.next = null;
        }
    }

    public LinkedStackOfStrings(){};
    public boolean isEmpty() {
        return head == null;
    }

    public String pop(){
        String s = head.value;
        head = head.next;
        return s;
    }

    public void push(String s){
        Node node = new Node(s);
        node.next = head;
        head = node;
    }
}

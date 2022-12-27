import java.util.HashSet;
import java.util.Set;

public class Node {
    int data;
    Node next;
    public Node() {
        this.data = 0;
        this.next = null;
    }
    public Node (int data) {
        this.data = data;
        this.next = null;
    }

    public void appendToTail(int d) {
        Node node = this;
        while (node.next != null) {
            node = node.next;
        }
        Node newNode = new Node(d);
        node.next = newNode;
    }

    public Node deleteNode (int d) {
        if (this.data == d) {
            return  this.next;
        }
        Node prev = this;
        while (prev.next != null) {
            if (prev.next.data == d) {
                prev.next = prev.next.next;
                return this;
            }
            prev = prev.next;
        }
        
        return this;
    }

    public String toString(){
        String repr = "";
        Node node = this;
        while (node != null) {
            repr += node.data + " ";
            node = node.next;
        }
        return repr;
    }

    
    /**
     * Hash table allowed
     * @return head
     */
    public void removeDup() {
        Set<Integer> buffer = new HashSet<>();
        Node prev = this;
        buffer.add(prev.data);
        Node current = prev.next;
        while (current != null) {
            if (!buffer.contains(current.data)) {
                buffer.add(current.data);
                prev = current;
                current = current.next;
            } else {
                // remove node from linked list
                prev.next = current.next;
                current = current.next;        
            }
        }

    }


    /**
     * No hash table allowed.
     */
    public void removeDupSlow() {
        Node current = this;
        while (current != null) {
            Node prev = current;
            Node runner = prev.next;
            while (runner != null) {
                // check if runner data == current data
                if (runner.data == current.data) {
                    prev.next = runner.next;
                    runner = runner.next;
                } else {
                    prev = runner;
                    runner = runner.next;
                }
            }
            current = current.next;

        }
    }

    public Node removeNthFromEnd(Node head, int n) {
        // count the number of nodes
        int count = 0;
        Node node = head;
        while (node != null) {
            count += 1;
            node = node.next;
        }
        // System.out.println (count);
        // number of nodes to traverse to get to the node needed to be deleted
        int m = count - n;
        
        // corner case: remove node head
        if (m == 0) {
            head = head.next;
            return head;
        }
        node = head;

        while (m > 1) {
            node = node.next;
            m -= 1;
        }

        node.next = node.next.next;

        return head;
    }
    
   
}



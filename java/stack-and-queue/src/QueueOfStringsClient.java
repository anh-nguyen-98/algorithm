import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class QueueOfStringsClient {
    public static void main(String[] args){
        QueueOfStrings queue = new LinkedQueueOfStrings();
        while (!StdIn.isEmpty()){
            String s = StdIn.readString();
            if (s.equals("-")) StdOut.print(queue.dequeue());
            queue.enqueue(s);
        }
    }
}




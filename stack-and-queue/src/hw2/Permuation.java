
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Author: NGUYEN HOANG NAM ANH
 */
public class Permutation {
    public static void main (String[] args) {
        if (args.length > 0) {
            int k = Integer.parseInt(args[0]);
            RandomizedQueue<String> q = new RandomizedQueue<>();
            if (k > 0) {
                while (!StdIn.isEmpty()) {
                    q.enqueue(StdIn.readString());
                }
                for (int i = 0; i < k; i++) {
                    StdOut.println(q.dequeue());
                }
            }


        }

    }
}

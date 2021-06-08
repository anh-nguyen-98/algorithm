import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class StackOfStringsClient {
    public static void main (String[] args){
//        StackOfStrings stack = new LinkedStackOfStrings();
        StackOfStrings stack = new FixedCapacityStackOfStrings();
        while (!StdIn.isEmpty()){
            String s = StdIn.readString();
            if (s.equals("-")) StdOut.print(stack.pop());
            else stack.push(s);
        }
    }
}

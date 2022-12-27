import java.util.EmptyStackException;

/**
 * Array implementation of stack - with resizing.
 */
public class FixedCapacityStackOfStrings implements StackOfStrings{
    private String[] stack;
    private int next;

    public FixedCapacityStackOfStrings(){
        this.stack = new String[1];
        this.next = 0;
    }
    public boolean isEmpty() {
        return next == 0;
    }


    public void push(String s) {
        if (next >= stack.length) this.stack = resize(2*this.stack.length);
        this.stack[next++] = s;
    }

    public String pop() {
        if (this.isEmpty()) return "";
        if (next == this.stack.length/4) this.stack = resize(this.stack.length/2);
        String s = this.stack[--next];
        return s;
    }

    private String[] resize(int newCapacity){
        String[] newArr = new String[newCapacity];
        for (int i = 0; i < this.next; i++){
            newArr[i] = this.stack[i];
        }
        return newArr;
    }

}

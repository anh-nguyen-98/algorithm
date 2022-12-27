/**
 * Interface for APIs of queue of strings.
 */
public interface QueueOfStrings {
    boolean isEmpty();
    void enqueue(String s);
    String dequeue();
    int size();
}

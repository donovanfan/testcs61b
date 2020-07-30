package synthesizer;
import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T> {
    /** Return size of the buffer. */
    int capacity();

    /** Return number of items currently in the buffer. */
    int fillCount();

    /** Add item x to the end. */
    void enqueue(T x);

    /** Delete and return item from the front. */
    T dequeue();

    /** Return (but do not delete) item from the front. */
    T peek();

    /** Make it possible to iterate through a bounded queue. */
    @Override
    Iterator<T> iterator();

    /** Check if the data structure is empty.
     * @return: True if the bounded queue is empty, false otherwise.
     */
    default boolean isEmpty() {
        return (fillCount() == 0);
    }

    /** Check if the data structure is full.
     * @return: True if the bounded queue is full, false otherwise.
     */
    default boolean isFull() {
        return (fillCount() == capacity());
    }
}

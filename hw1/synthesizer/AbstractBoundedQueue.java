package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T>{
    protected int fillCount;
    protected int capacity;

    @Override
    /** Find capacity.
     * @return: The capacity of bounded queue.
     */
    public int capacity() {
        return capacity;
    }

    @Override
    /** Return number of items currently in the buffer.
     * @return: Number of items in the buffer.
     */
    public int fillCount() {
        return fillCount;
    }
}

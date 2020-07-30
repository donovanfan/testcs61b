package synthesizer;

import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     * @param: Size of the array we desire.
     */
    public ArrayRingBuffer(int capacity) {
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
        rb = (T[]) new Object[capacity];
    }

    /** Private helper method to find the next enqueue pointer.
     * @param index: Current pointer.
     * @return: The next pointer.
     */
    private int findPointer(int index) {
        if (index == capacity - 1) {
            return 0;
        }
        return (index + 1);
    }

    /** Adds x to the end of the ring buffer. If there is no room, then throw new
     * RuntimeException("Ring buffer overflow").
     * @param: Generic object to add.
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last = findPointer(last);
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     * @return: An generic object at the front.
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T result = rb[first];
        rb[first] = null;
        fillCount -= 1;
        first = findPointer(first);
        return result;
    }

    /**
     * Return oldest item, but don't remove it.
     * @return: The next object.
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        return rb[first];
    }

    private class BoundedQueueIterator implements Iterator<T> {
        private int wizPos;
        private int current;

        public BoundedQueueIterator() {
            wizPos = 0;
            current = first;
        }

        @Override
        public boolean hasNext() {
            return (wizPos < fillCount);
        }

        @Override
        public T next() {
            T returnItem = rb[current];
            current = findPointer(current);
            wizPos += 1;
            return returnItem;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new BoundedQueueIterator();
    }
}

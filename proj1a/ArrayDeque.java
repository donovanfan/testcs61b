import java.lang.Math.*;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;

public class ArrayDeque<T> {
    private T[] array;
    private int size;
    private int nextFirst;
    private int nextLast;
    private double usage;
    private int capacity;

    /** Constructor of the class.
     */
    public ArrayDeque() {
        array = (T[]) new Object [8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
        usage = 0.0;
    }

    /** Helper method to check if the array should become smaller. */
    private boolean checkContract() {
        return (array.length >= 16 && usage < 0.25);
    }

    /** Helper method to check if the array should expand. */
    private boolean checkExpansion() {
        return (size == array.length);
    }

    /**Helper method to find current start of array. */
    private int findStart(int index) {
        if (index == array.length - 1) {
            return 0;
        }
        return (index + 1);
    }

    /**Helper method to find the current end of array. */
    private int findEnd(int index) {
        if (index == 0) {
            return (array.length - 1);
        }
        return (index - 1);
    }

    /** Private helper method to update usage. */
    private void updateUsage() {
        this.usage = (double)this.size / (double)this.array.length;
    }

    /** Private method to resize the array when the usage ratio
     *  is lower than 25% and the array length is longer than 16
     *  or the array is full.
     */
    private void resize() {
        /* Determine the size of the new array. */
        int newSize = 0;
        if (this.checkExpansion()) {
            newSize = array.length * 2;
        } else if (this.checkContract()) {
            newSize = Math.max(this.size * 2, 8);
        } else {
            return;
        }
        /* Copy all the items in the array to the new array. */
        T newArray[] = (T[]) new Object [newSize];
        int start = findStart(nextFirst);
        int end = findEnd(nextLast);
        if (start < end) {
            System.arraycopy(array, start, newArray, 0, this.size);
        } else {
            int lengthFirst = array.length - start;
            int lengthLast = this.size - lengthFirst;
            System.arraycopy(array, start, newArray, 0, lengthFirst);
            System.arraycopy(array, 0, newArray, lengthFirst, lengthLast);
        }
        this.array = newArray;
        this.nextFirst = newSize - 1;
        this.nextLast = this.size;
        updateUsage();
    }

    /** Adds an item of type T to the front of the deque.
     *
     * @param item: The item we would like to add into deque.
     */
    public void addFirst(T item) {
        this.array[nextFirst] = item;
        this.size += 1;
        this.nextFirst = findEnd(this.nextFirst);
        updateUsage();
        resize();
    }

    /** Adds an item of type T to the end of the deque.
     *
     * @param item: The item we would like to add into deque.
     */
    public void addLast(T item) {
        this.array[nextLast] = item;
        this.size += 1;
        this.nextLast = findStart(this.nextLast);
        updateUsage();
        resize();
    }

    /** Check if a deque is empty.
     *
     * @return: True if the deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return (this.size == 0);
    }

    /** Return the number of items in the deque.
     *
     * @return: The number of items in the deque.
     */
    public int size() {
        return (this.size);
    }

    /** Prints the items in the deque from first to last, separated by a space.
     */
    public void printDeque() {
        int index = findStart(nextFirst);
        while (index != nextLast) {
            System.out.print(array[index] + " ");
            index = findStart(nextFirst);
        }
        System.out.print("\n");
    }

    /** Removes and returns the item at the front of the deque.
     *  If no such item exists, returns null.
     *
     * @return: First item of the deque if deque is not empty, null otherwise.
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        int index = findStart(this.nextFirst);
        T result = array[index];
        array[index] = null;
        this.size -= 1;
        this.nextFirst = index;
        updateUsage();
        resize();
        return result;
    }

    /** Removes and returns the item at the end of the deque. If no such item exists, returns null.
     *
     * @return: Last item of the deque if deque is not empty, null otherwise.
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        int index = findEnd(this.nextLast);
        T result = array[index];
        array[index] = null;
        this.size -= 1;
        this.nextLast = index;
        updateUsage();
        resize();
        return result;
    }

    /** Using iteration to get the item at the given index, where 0 is the front,
     *  1 is the next item, and so forth. If no such item exists, returns null.
     *
     * @param: The index of needed item.
     * @return: The item with index equal to the parameter index.
     */
    public T get(int index) {
        if (index >= this.size) {
            return null;
        }
        int arrayIndex = this.nextFirst + index + 1;
        if (arrayIndex >= array.length) {
            arrayIndex -= array.length;
        }
        return (array[arrayIndex]);
    }
}

public class LinkedListDeque<PlaceholderType> {
    /** Private helper class for node in LinkedListDeque. */
    private class ListNode {
        private PlaceholderType item;
        private ListNode prev;
        private ListNode next;

        private ListNode(PlaceholderType i, ListNode j, ListNode k) {
            item = i;
            prev = j;
            next = k;
        }
    }

    private int size;
    private ListNode sentinel;

    public LinkedListDeque() {
        sentinel = new ListNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /** Adds an item of type PlaceholderType to the front.
     *
     * @param item: new first item
     */
    public void addFirst(PlaceholderType item) {
        ListNode temp = sentinel.next;
        sentinel.next = new ListNode(item, sentinel, temp);
        temp.prev = sentinel.next;
        size += 1;
    }

    /** Adds an item of type PlaceholderType to the end.
     *
     * @param item: new first item
     */
    public void addLast(PlaceholderType item) {
        ListNode temp = sentinel.prev;
        sentinel.prev = new ListNode(item, temp, sentinel);
        temp.next = sentinel.prev;
        size += 1;
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
        ListNode temp = sentinel.next;
        while(temp != sentinel) {
            System.out.print(temp.item + " ");
            temp = temp.next;
        }
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null.
     *
     * @return: First item of the deque if deque is not empty, null otherwise.
     */
    public PlaceholderType removeFirst() {
        if (isEmpty()) {
            return null;
        }
        ListNode temp = sentinel.next.next;
        PlaceholderType result = sentinel.next.item;
        sentinel.next = temp;
        temp.prev = sentinel;
        this.size -= 1;
        return result;
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null.
     *
     * @return: Last item of the deque if deque is not empty, null otherwise.
     */
    public PlaceholderType removeLast() {
        if (isEmpty()) {
            return null;
        }
        ListNode temp = sentinel.prev.prev;
        PlaceholderType result = sentinel.prev.item;
        sentinel.prev = temp;
        temp.next = sentinel;
        this.size -= 1;
        return result;
    }

    /** Using iteration to get the item at the given index, where 0 is the front,
     *  1 is the next item, and so forth. If no such item exists, returns null.
     *
     * @param: The index of needed item.
     * @return: The item with index equal to the parameter index.
     */
    public PlaceholderType get(int index) {
        if (index < 0 || index > this.size - 1) {
            return null;
        }
        ListNode temp = sentinel.next;
        while (index > 0) {
            temp = temp.next;
            index -= 1;
        }
        return temp.item;
    }

    /** Using recursion to gets the item at the given index, where 0 is the front,
     *  1 is the next item, and so forth. If no such item exists, returns null.
     *
     * @param: The index of the needed item.
     * @return: The item with index equal to the parameter index.
     */
    public PlaceholderType getRecursive(int index) {
        if (index < 0 || index > this.size - 1) {
            return null;
        }
        return traverse(index, this.sentinel.next);
    }

    /** The helper method of getRecursive.
     *
     * @param index: The index of the needed item.
     * @param input: The listnode given.
     * @return: The item of the listnode with index equal to param index.
     */
    public PlaceholderType traverse(int index, ListNode input) {
        if (index == 0) {
            return input.item;
        }
        return traverse(index - 1, input.next);
    }
}


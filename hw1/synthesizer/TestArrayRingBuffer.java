package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
    }

    @Test
    public void testEnqueueDequeue() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        for (int i = 1; i < arb.capacity(); i += 1) {
            arb.enqueue(i);
        }
        for (int i = 1; i < arb.capacity(); i += 1) {
            assertEquals((int)arb.dequeue(), i);
        }
    }

    @Test
    public void testPeek() {
        BoundedQueue<Integer> arb = new ArrayRingBuffer<>(10);
        for (int i = 0; i < arb.capacity(); i += 1) {
            arb.enqueue(i);
        }
        for (int i = 0; i < 7; i += 1) {
            arb.dequeue();
        }
        assertEquals((int) arb.peek(), 7);
    }

    @Test
    public void testIsFull() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        for (int i = 0; i < arb.capacity(); i += 1) {
            arb.enqueue(i);
        }
        assertTrue(arb.isFull());
    }

    @Test
    public void testIsEmpty() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        for (int i = 0; i < arb.capacity(); i += 1) {
            arb.enqueue(i);
        }
        for (int i = 0; i < arb.capacity(); i += 1) {
            arb.dequeue();
        }
        assertTrue(arb.isEmpty());
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 

package synthesizer;
import java.util.HashSet;

//Make sure this class is public
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final means
     * the values cannot be changed at runtime. We'll discuss this and other topics
     * in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /** Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /** Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        buffer = new ArrayRingBuffer<>((int) Math.round(SR / frequency));
        while (!buffer.isFull()) {
            buffer.enqueue(0.0);
        }
    }

    /** Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        HashSet<Double> temp = new HashSet<>();
        for (int i = 0; i < buffer.capacity(); i++) {
            double r = Math.random() - 0.5;
            //The while loop guarantees that all doubles in array are different.
            while (temp.contains(r)) {
                r = Math.random() - 0.5;
            }
            buffer.dequeue();
            buffer.enqueue(r);
        }
    }

    /** Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        double drop = buffer.dequeue();
        double next = buffer.peek();
        double toAdd = DECAY * (drop + next) / 2;
        buffer.enqueue(toAdd);
    }

    /** Return the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }
}

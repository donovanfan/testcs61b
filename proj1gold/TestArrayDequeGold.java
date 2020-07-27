import static org.junit.Assert.*;
import org.junit.Test;
public class TestArrayDequeGold {
    private static String message = "";

    private void modifiedAdd(double rand, Integer i, StudentArrayDeque
            <Integer> ss, ArrayDequeSolution<Integer> cs) {
        if (rand >= 0.5) {
            ss.addFirst(i);
            cs.addFirst(i);
            this.message += String.format("\naddFirst(%d)", i);
            return;
        }
        ss.addLast(i);
        cs.addLast(i);
        this.message += String.format("\naddLast(%d)", i);
    }

    private void modifiedRemove(double rand, StudentArrayDeque
            <Integer> ss, ArrayDequeSolution<Integer> cs) {
        Integer expected, actual;
        if (rand >= 0.5) {
            actual = ss.removeFirst();
            expected = cs.removeFirst();
            this.message += "\nremoveFirst()";
        } else {
            actual = ss.removeFirst();
            expected = cs.removeFirst();
            this.message += "\nremoveLast()";
        }
        assertEquals(message, expected, actual);
    }

    @Test
    public void tester() {
        StudentArrayDeque<Integer> ss = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> cs = new ArrayDequeSolution<>();
        for (int i = 0; i < 100; i++) {
            double rand1 = StdRandom.uniform(1);
            double rand2 = StdRandom.uniform(1);
            if (ss.size() == 0) {
                modifiedAdd(rand2, StdRandom.poisson(10), ss, cs);
            } else if (rand1 >= 0.5) {
                modifiedAdd(rand2, StdRandom.poisson(10), ss, cs);
            } else {
                modifiedRemove(rand2, ss, cs);
            }
        }
    }
}

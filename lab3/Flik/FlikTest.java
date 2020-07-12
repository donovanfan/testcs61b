import static org.junit.Assert.*;

import org.junit.Test;
public class FlikTest {
    @Test
    public void testFlik() {
        Integer a = 3;
        Integer b = 3;
        Integer c = 4;
        assertTrue("Diff ref; same num", Flik.isSameNumber(a, b));
        assertTrue("Same ref; same num", Flik.isSameNumber(a, a));
        assertTrue("Diff ref; diff num", !Flik.isSameNumber(a, c));
    }
}

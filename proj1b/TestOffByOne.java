import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testOffByOneLetter() {
        assertFalse(offByOne.equalChars('a', 'a'));
        assertFalse(offByOne.equalChars('a', 'c'));
        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('b', 'a'));
        assertFalse(offByOne.equalChars('A', 'b'));
        assertTrue(offByOne.equalChars('A', 'B'));
        assertTrue(offByOne.equalChars('B', 'A'));
        assertFalse(offByOne.equalChars('A', 'C'));
        assertFalse(offByOne.equalChars('A', 'A'));
    }

    @Test
    public void testOffByOneNonAlphabet() {
        assertTrue(offByOne.equalChars('%', '&'));
        assertTrue(offByOne.equalChars('&', '%'));
        assertFalse(offByOne.equalChars('#', '&'));
        assertFalse(offByOne.equalChars('&', '#'));
    }
}

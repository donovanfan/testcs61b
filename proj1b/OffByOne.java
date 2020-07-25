import java.lang.Math;
public class OffByOne implements CharacterComparator{
    /** Check if two chars are different by one.
     * @param x: Char to check.
     * @param y: Char to check.
     * @return: True if different by exactly one, false otherwise.
     */
    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return (Math.abs(diff) == 1);
    }
}

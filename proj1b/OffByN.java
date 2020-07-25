public class OffByN implements CharacterComparator{
    private int n;
    public OffByN(int N) {
        n = N;
    }
    /** Check if two chars are different by N.
     * @param x: Char to check.
     * @param y: Char to check.
     * @return: True if different by exactly N, false otherwise.
     */
    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return (Math.abs(diff) == n);
    }

}

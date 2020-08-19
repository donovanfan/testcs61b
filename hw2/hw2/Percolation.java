package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.util.Arrays;

public class Percolation {
    private final int N;
    private final int topIndex;
    private final int btmIndex;
    private final boolean[] openGrid;
    private final WeightedQuickUnionUF normalQU;
    private final WeightedQuickUnionUF backwashQU;
    private int numOpen;

    /** Create N-by-N grid, with all sites initially blocked
     * @param N: Length of sides.
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Expect N greater than zero, but given" + N + ".");
        }
        this.N = N;
        topIndex = 0;
        btmIndex = N * N + 1;
        openGrid = new boolean[N * N + 2];
        normalQU = new WeightedQuickUnionUF(N * N + 1); //Not include bottom index
        backwashQU = new WeightedQuickUnionUF(N * N +2);
        openGrid[topIndex] = true;
        openGrid[btmIndex] = true;
    }

    /** Private method to convert 2-D grid into 1-D.
     * @param row: Row of the element in 2-D.
     * @param col: Column of the element in 2-D.
     * @return: The index in 1-D.
     */
    private int xyToIndexIn1D(int row, int col) {
        if (row < 0 || row > N - 1) {
            throw new IndexOutOfBoundsException("Row out of bounds");
        }
        else if (col < 0 || col > N - 1) {
            throw new IndexOutOfBoundsException("Column out of bounds");
        }
        else {
            return (N * row + col + 1);
        }
    }

    /** Open the site (row, col) if it is not open already
     * @param row: Row of the element.
     * @param col: Column of the element.
     */
    public void open(int row, int col) {
        int index = xyToIndexIn1D(row, col);
        openGrid[index] = true;
        numOpen += 1;

        if (row == 0) {
            backwashQU.union(topIndex, index);
            normalQU.union(topIndex, index);
        }
        if (row == N - 1) {
            backwashQU.union(btmIndex, index);
        }
        tryUnion(row, col, row, col - 1);
        tryUnion(row, col, row, col + 1);
        tryUnion(row, col, row - 1, col);
        tryUnion(row, col, row + 1, col);
    }

    /** Check if index is valid.
     * @param row: Row of input.
     * @param col: Column of input.
     * @return: True if the input is valid, false otherwise.
     */
    private boolean isInvalidIndex(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            return true;
        }
        return false;
    }

    /** Private method to try union two points.
     * @param rowA: Row of point A.
     * @param rowB: Row of point B.
     * @param colA: Column of point A.
     * @param colB: Column of point B
     */
    private void tryUnion(int rowA, int colA, int rowB, int colB) {
        if (isInvalidIndex(rowA, colA) || isInvalidIndex(rowB, colB)) {
            return;
        }
        if (!(isOpen(rowA, colA) && isOpen(rowB, colB))) {
            return;
        }
        int indexA = xyToIndexIn1D(rowA, colA);
        int indexB = xyToIndexIn1D(rowB, colB);
        backwashQU.union(indexA, indexB);
        normalQU.union(indexA, indexB);
    }

    /** Check if the site (row, col) is open.
     * @param row: Row of the point.
     * @param col: Column of the point.
     * @return: True if it is open, false otherwise.
     */
    public boolean isOpen(int row, int col) {
        int index = xyToIndexIn1D(row, col);
        return openGrid[index];
    }

    /** Check if the site (row, col) is full.
     * @param row: Row of the point.
     * @param col: Column of the point.
     * @return: True if it is full, false otherwise.
     */
    public boolean isFull(int row, int col) {
        int index = xyToIndexIn1D(row, col);
        return normalQU.connected(index, topIndex);
    }

    /** Calculate the number of open sites.
     * @return: The number of open sites.
     */
    public int numberOfOpenSites() {
        return numOpen;
    }

    /** Check if the system percolate.
     * @return: True if it percolates, false otherwise.
     */
    public boolean percolates() {
        return backwashQU.connected(topIndex, btmIndex);
    }

    public static void main(String[] args) {
    }
}

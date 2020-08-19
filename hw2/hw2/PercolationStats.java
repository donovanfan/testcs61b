package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double std = 1.96;
    private final int T; //Number of trails
    private final int N; //Size of the percolation
    private final PercolationFactory pf; //Item to create percolation
    private double[] threshold;

    /** Perform T independent experiments on an N-by-N grid.
     * @param N: The side length of the grid.
     * @param T: The number of trails.
     * @param pf: The percolation Factory object.
     */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Invalid input");
        }
        this.N = N;
        this.T = T;
        this.pf = pf;
        threshold = new double[T];

        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                }
            }
            threshold[i] = (double) p.numberOfOpenSites() / (N * N);
        }
    }

    /** Sample mean of percolation threshold.
     * @return: Sample mean of threshold.
     */
    public double mean() {
        return StdStats.mean(threshold);
    }

    /** Sample standard deviation of percolation threshold.
     * @return: The sample standard deviation of threshold.
     */
    public double stddev() {
        return StdStats.stddev(threshold);
    }

    /** Calculate the low endpoint of 95% confidence interval.
     * @return: The low endpoint of 95% confidence interval.
     */
    public double confidenceLow() {
        return (mean() - std * stddev() / Math.sqrt(T));
    }

    /** Calculate the high endpoint of 95% confidence interval.
     * @return: The high endpoint of 95% confidence interval.
     */
    public double confidenceHigh() {
        return (mean() + std * stddev() / Math.sqrt(T));
    }
}

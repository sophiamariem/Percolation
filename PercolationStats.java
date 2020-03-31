/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final int n;
    private final double[] experiments;
    private double mean = 0.0;
    private double stddev = 0.0;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 1) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        this.experiments = new double[trials];

        for (int i = 0; i < trials; i++) {
            experiments[i] = experiment();
        }
    }

    private double experiment() {
        Percolation percolation = new Percolation(n);

        while (!percolation.percolates()) {
            int row = StdRandom.uniform(1, n + 1);
            int col = StdRandom.uniform(1, n + 1);
            if (!percolation.isOpen(row, col)) {
                percolation.open(row, col);
            }
        }

        return (double) percolation.numberOfOpenSites() / (n * n);
    }

    // sample mean of percolation threshold
    public double mean() {
        this.mean = StdStats.mean(experiments);
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        this.stddev = StdStats.stddev(experiments);
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return getMeanValue() - confidence();
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return getMeanValue() + confidence();
    }

    private double getMeanValue() {
        if (mean == 0.0d) {
            return mean();
        }
        return mean;
    }

    // percolation threshold as per specification
    private double confidence() {
        double stddevValue = stddev;
        if (stddevValue == 0.0d) {
            stddevValue = stddev();
        }
        return 1.96 * stddevValue / Math.sqrt(experiments.length);
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]),
                                                                 Integer.parseInt(args[1]));
        StdOut.println("mean                    =" + percolationStats.mean());
        StdOut.println("stddev                  =" + percolationStats.stddev());
        StdOut.println("95% confidence interval =[" +
                               percolationStats.confidenceLo() + ", " +
                               percolationStats.confidenceHi() + "]");
    }
}

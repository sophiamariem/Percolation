/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int n;
    private final boolean[] open;
    private final WeightedQuickUnionUF quickUnion;
    private final WeightedQuickUnionUF backwashQuickUnion;
    private final int virtualTop;
    private final int virtualBottom;

    private int count;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        this.virtualTop = n * n;
        this.virtualBottom = n * n + 1;
        this.open = new boolean[n * n];
        this.quickUnion = new WeightedQuickUnionUF(virtualBottom);
        this.backwashQuickUnion = new WeightedQuickUnionUF(virtualBottom + 1);
        this.count = 0;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateWithinBounds(row, col);

        int currentPos = pos(row, col);
        if (open[currentPos]) {
            return;
        }
        open[currentPos] = true;
        count++;

        if (row == 1) {
            quickUnion.union(virtualTop, currentPos);
            backwashQuickUnion.union(virtualTop, currentPos);
        }

        if (row == n) {
            backwashQuickUnion.union(virtualBottom, currentPos);
        }

        unionAdjacent(currentPos, row - 1, col);
        unionAdjacent(currentPos, row + 1, col);
        unionAdjacent(currentPos, row, col - 1);
        unionAdjacent(currentPos, row, col + 1);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateWithinBounds(row, col);
        return open[pos(row, col)];
    }

    //
    // // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validateWithinBounds(row, col);
        return quickUnion.connected(virtualTop, pos(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return backwashQuickUnion.connected(virtualTop, virtualBottom);
    }

    private void validateWithinBounds(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException();
        }
    }

    private int pos(int row, int col) {
        return (row - 1) * n + col - 1;
    }

    private void unionAdjacent(int from, int rowTo, int colTo) {
        try {
            if (isOpen(rowTo, colTo)) {
                int nextPosition = pos(rowTo, colTo);
                quickUnion.union(from, nextPosition);
                backwashQuickUnion.union(from, nextPosition);
            }
        }
        catch (IllegalArgumentException e) {
            return;
        }
    }
}
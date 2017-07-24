import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  
    private int count;
    private final WeightedQuickUnionUF WQUUF;
    private final int matrixRange, startBox, endBox;
    private boolean[][] matrix;
  
  // create n-by-n grid, with all sites blocked
    private Percolation(int n) {
        if (n < 1) throw new IllegalArgumentException("Invalid value");
        matrixRange = n;
        matrix = new boolean[matrixRange+1][matrixRange+1];
        int lineMatrix = n * n + 2; // 0 - start, n*n  
        startBox = lineMatrix-2;
        endBox = lineMatrix-1;
        count = 0;
        WQUUF = new WeightedQuickUnionUF(lineMatrix);
    }                
  
  // open site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);

        if (!isOpen(row, col)) {
            count++;
            matrix[row][col] = true;
            if (row == 1) WQUUF.union(matrix2line(row, col), startBox);
            if (row != 1 && isOpen(row-1, col)) WQUUF.union(matrix2line(row, col), matrix2line(row-1, col));
            if (row == matrixRange) WQUUF.union(matrix2line(row, col), endBox);
            if (row != matrixRange && isOpen(row+1, col)) WQUUF.union(matrix2line(row, col), matrix2line(row+1, col));
            if (col != 1 && isOpen(row, col-1)) WQUUF.union(matrix2line(row, col), matrix2line(row, col-1));
            if (col != matrixRange && isOpen(row, col+1)) WQUUF.union(matrix2line(row, col), matrix2line(row, col+1));
        }
    }    
  // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        return matrix[row][col];
    }  
  // is site (row, col) full?
    public boolean isFull(int row, int col) {
        return WQUUF.connected(startBox, matrix2line(row, col));
    }  
   // number of open sites
    public     int numberOfOpenSites() {
        return count;
    }      
   // does the system percolate?
    public boolean percolates() {
        return WQUUF.connected(startBox, endBox);
    }           

    private int matrix2line(int row, int col) {
        return (row-1)*matrixRange + col -1;
    }
  
    private void validate(int row, int col) {
        if (row < 1 || row > matrixRange || col < 1 || col > matrixRange) throw new IllegalArgumentException("Invalid value row=" + row + " col=" + col);
    }
    
     public static void main(String[] args) {
     Percolation pr = new Percolation(3);
     pr.open(1,3);
     pr.open(2,3);
     pr.open(3,3);
     System.out.println(pr.isFull(3,3));
     
     pr.open(3,1);
     System.out.println(pr.isFull(3,1));
     pr.open(2,1);
     pr.open(1,1);
       
     }  
}
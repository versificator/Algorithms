public class Board {
  // construct a board from an n-by-n array of blocks
  // (where blocks[i][j] = block in row i, column j)
  
    private int[][] blocks, twinBlocks;
    
    public Board(int[][] inBlocks) {
        validate(inBlocks);
        blocks = inBlocks.clone();

    }
    // board dimension n                                   
    public int dimension() {
        return blocks.length;
    }
    
    // number of blocks out of place
    public int hamming() {
        return -1;  
    }
    
    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int sum = 0, number = 0;
        for (int row = 0; row < dimension() -1; row++)
            for (int col = 0; col < dimension() -1; col++) {
                number = (matrix2line(row, col) - blocks[row][col] );
                sum += (number < 0) ? -number : number;
        }
    return sum;  
    }
    
    // is this board the goal board?
    public boolean isGoal() {
        for (int row = 0; row < dimension() -1; row++)
            for (int col = 0; col < dimension() -1; col++)
                if (matrix2line(row, col) != blocks[row][col]) return false;        
        return true;
    }
    
    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        twinBlocks = blocks.clone();
        int temp = 0;
        for (int row = 0; row < dimension() -2; row++)
            if (twinBlocks[row][0] !=0 && twinBlocks[row+1][0] !=0) {
                temp = twinBlocks[row][0];
                twinBlocks[row][0] = twinBlocks[row + 1][0];
                twinBlocks[row + 1][0] = temp;
                return new Board(twinBlocks);
            }
                      
        for (int col = 0; col < dimension() -2; col++) 
            if (twinBlocks[0][col] !=0 && twinBlocks[0][col + 1] != 0) {
                temp = twinBlocks[0][col];
                twinBlocks[0][col] = twinBlocks[0][col + 1];
                twinBlocks[0][col + 1] = temp;
                return new Board(twinBlocks);
        }
            return null;        
    }
    
    // does this board equal y?
    public boolean equals(Object y) {
     return false; 
    }
    
    // all neighboring boards
    public Iterable<Board> neighbors() {
        return null;  
    }
    
    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < dimension() -1; row++) {
            sb.append("\n");
            for (int col = 0; col < dimension() -1; col++) 
                sb.append(" " + blocks[row][col]);
        }
        return sb.toString();
    }
    
    private void validate(int[][] blocks) {
        for (int i = 0; i < blocks.length-1; i++)
            if (blocks[i].length != blocks[i+1].length) throw new java.lang.IllegalArgumentException();   
    }
    
    private int matrix2line(int row, int col) {
        return (row)*dimension() + col;
    }
        
     

    // unit tests (not graded)
    public static void main(String[] args) {
      
    }
}
import java.util.ArrayList;
public class Board {
  // construct a board from an n-by-n array of blocks
  // (where blocks[i][j] = block in row i, column j)
  
    private final int[][] blocks;
    private final int range;
    private final int manhattan;
    public Board(int[][] inBlocks) {
        validate(inBlocks);
        range = inBlocks.length;
        blocks = deepCopy(inBlocks);
        manhattan = getManhattan();
    }
    // board dimension n                                   
    public int dimension() {
        return range;
    }
    
    // number of blocks out of place
    public int hamming() {
        int sum = 0;
        for (int row = 0; row < dimension(); row++)
            for (int col = 0; col < dimension(); col++)
                if (!(matrix2line(row, col) == blocks[row][col] || (row == dimension()-1 && col == dimension()-1))) sum += 1;
        return sum;
    }
    
//    private int linearRowConflicts(){
//    int linear = 0;
//    int goalRow = 0;
//        for (int row = 0; row < dimension(); row++) {
//         int max = -1;
//            for (int col = 0; col < dimension(); col++) {
//                if (blocks[row][col] != 0) {
//                    goalRow = (blocks[row][col] - 1) / range;
//               //     StdOut.println("[" + row +"," + col +"] = " + blocks[row][col] + "  goalRow = " + goalRow);
//                        if (goalRow == row){
//                            if (blocks[row][col] > max)
//                                max = blocks[row][col];
//                            else {
//                                linear =+ 2;
//                            }
//                        } 
//                }
//            }
//        }
//    return linear;
//    }
    
    private int getManhattan() {
        int sum = 0, intPart, fracPart;
        for (int row = 0; row < dimension(); row++) {
            for (int col = 0; col < dimension(); col++)
                if (blocks[row][col] != 0 && (blocks[row][col] != 1+ col +(row*this.range))) {
                    intPart = (blocks[row][col] -1) / dimension();
                    fracPart =  blocks[row][col] - 1 - intPart*dimension();
                    sum += Math.abs(row - intPart) + Math.abs(col - fracPart);
                }
        }
        return sum;  
    }
    
    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        return manhattan;
    }
    
    // is this board the goal board?
    public boolean isGoal() {
        return manhattan == 0; 
    }
    
    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        for (int row = 0; row < dimension(); row++)
            for (int col = 0; col < dimension(); col++) 
                if ((row + 1 < dimension()) && blocks[row][col] != 0 && blocks[row + 1][col] != 0) {
                    return new Board(swap(deepCopy(blocks), row, col, row + 1, col));
                }
        else if ((col + 1 < dimension()) && blocks[row][col] != 0 && blocks[row][col + 1] != 0) { 
                    return new Board(swap(deepCopy(blocks), row, col, row, col + 1));
                }      
        throw new java.lang.IllegalArgumentException();        
    }
    
    // does this board equal y?
    public boolean equals(Object y)
    {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;

        Board that = (Board) y;
        
        if (this.dimension() != that.dimension()) return false;

        for (int i = 0; i < dimension(); ++i)
        {
            for (int j = 0; j < dimension(); ++j)
            {
                if (this.blocks[i][j] != that.blocks[i][j])
                {
                    return false;
                }
            }
        }
        return true;
    }
    
    // all neighboring boards
    public Iterable<Board> neighbors() {
        ArrayList<Board> board = new ArrayList<>();
        for (int row = 0; row < dimension(); row++)
            for (int col = 0; col < dimension(); col++)
                if (blocks[row][col] == 0) {
                    if (row > 0) board.add(new Board(swap(blocks, row, col, row - 1, col)));
                    if (row < dimension()-1) board.add(new Board(swap(blocks, row, col, row + 1, col)));
                    if (col > 0) board.add(new Board(swap(blocks, row, col, row, col - 1)));
                    if (col < dimension()-1) board.add(new Board(swap(blocks, row, col, row, col + 1)));
                }   
        return board;          
    }
    
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(range + "\n");
        for (int i = 0; i < range; i++) {
            for (int j = 0; j < range; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
             }
        s.append("\n");
        }
        return s.toString();
    }
    
    private void validate(int[][] inBlocks) {
        if (inBlocks == null) throw new java.lang.IllegalArgumentException("inBlocks is empty");
        for (int i = 0; i < inBlocks.length-1; i++)
            if (inBlocks[i].length != inBlocks[i+1].length) throw new java.lang.IllegalArgumentException("Length of rows and columns is different");   
    }
    
    private int matrix2line(int row, int col) {
        return (row)*dimension() + col + 1;
    }
    
    private int[][] deepCopy(int[][] inBlocks) {
        int[][] duplicate = new int[dimension()][dimension()];
        for (int row = 0; row < dimension(); row++)
            for (int col = 0; col < dimension(); col++)
                duplicate[row][col] = inBlocks[row][col];
        return duplicate;
    }
    
    private int[][] swap(int[][] inBlocks, int x, int y, int i, int j) {
        int[][] copy = deepCopy(inBlocks);
        int swap = copy[x][y];
        copy[x][y] = copy[i][j];
        copy[i][j] = swap;
        return copy;
    }
    // unit tests (not graded)

}
//  import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Arrays;
public class Board {
  // construct a board from an n-by-n array of blocks
  // (where blocks[i][j] = block in row i, column j)
  
    private final int[][] blocks;
    private final int range;
    
    public Board(int[][] inBlocks) {
        validate(inBlocks);
        range = inBlocks.length;
        blocks = deepCopy(inBlocks);
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
    
    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int sum = 0, intPart, fracPart;
        for (int row = 0; row < dimension(); row++)
            for (int col = 0; col < dimension(); col++)
                if (blocks[row][col] != 0) {
             //       StdOut.print("manhattan (" + row + ", " + col + "),    blocks[row][col]= " + blocks[row][col]);
                    intPart = (blocks[row][col] -1) / dimension();
                    fracPart =  blocks[row][col] - 1 - intPart*dimension();
            //        StdOut.println("  intPart="+intPart + " fracPart=" + fracPart);
                    sum += Math.abs(row - intPart) + Math.abs(col - fracPart);
                }
        
  //      StdOut.println("manhattan sum = " + sum);
        return sum;  
    }
    
    // is this board the goal board?
    public boolean isGoal() {
        return (manhattan() == 0); 
    }
    
    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        int[][] twinBlocks = deepCopy(blocks);
        for (int row = 0; row < dimension(); row++)
            for (int col = 0; col < dimension() ; col++) 
                if ( (row + 1< dimension()) && twinBlocks[row][col] != 0 && twinBlocks[row+1][col] != 0) {
                    return new Board(swap(twinBlocks, row, col, row + 1, col));
                }
        else if ((col + 1 < dimension()) && twinBlocks[row][col] != 0 && twinBlocks[row][col + 1] != 0) { 
                    return new Board(swap(twinBlocks, row, col, row, col + 1));
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

        for (int i = 0; i < range; ++i)
        {
            for (int j = 0; j < range; ++j)
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
        return new Iterable<Board>() {
            @Override
            public Iterator<Board> iterator() {
                return new Iterator<Board>() {
                    private int position;
                    private Board[] items = getNeibors().toArray(new Board[getNeibors().size()]);  
                    
                    private Board getNeibor(int x1, int y1, int x2, int y2) {
                    return new Board(swap(blocks,x1,y1,x2,y2));
                    }
                    private ArrayList<Board> getNeibors() {
                        ArrayList<Board> board = new ArrayList<>();
                        for (int row = 0; row < dimension(); row++)
                            for (int col = 0; col < dimension(); col++)
                                if (blocks[row][col] == 0) {
//                                    StdOut.println("row="+row + " col=" + col);
                                    if (row > 0) {
                                        board.add(getNeibor(row,col,row - 1,col));
                                    }
                            
                                    if (row < dimension()-1) {
                                        board.add(getNeibor(row,col,row + 1,col));
                                    }
                             
                                    if (col > 0) {
                                        board.add(getNeibor(row,col,row,col - 1));
                                    }
                              
                                    if (col < dimension()-1) {
                                        board.add(getNeibor(row,col,row,col + 1));
                                    }
                                }   
                        return board;
                    }
                
                    @Override
                    public boolean hasNext() {
                        return position != items.length;
                    }

                    @Override
                    public Board next() {
                        if (!hasNext()) throw new NoSuchElementException();
                        return items[position++];
                    }
                };
            }
        };
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
    
    private int[][] swap(int[][] blocks, int x, int y, int i, int j) {
        int[][] copy = deepCopy(blocks);
        int swap = copy[x][y];
        copy[x][y] = copy[i][j];
        copy[i][j] = swap;
        return copy;
    }
    // unit tests (not graded)

}
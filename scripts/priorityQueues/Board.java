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
        for (int row = 0; row < dimension() - 1; row++)
            if (twinBlocks[row][0] != 0 && twinBlocks[row+1][0] != 0) {
                swap(twinBlocks, row, 0, row + 1, 0);
                return new Board(twinBlocks);
            }
                      
        for (int col = 0; col < dimension() - 1; col++) 
            if (twinBlocks[0][col] != 0 && twinBlocks[0][col + 1] != 0) {
                swap(twinBlocks,0,col,0, col +1);
                return new Board(twinBlocks);
            }
        throw new java.lang.IllegalArgumentException();        
    }
    
    // does this board equal y?
    public boolean equals(Object y) {
        Object that =  y;
        if (y == null) return false;
        if (this == y) return true;
        if (this.getClass() != y.getClass()) return false;
        if (((Board) that).range != ((Board) this).range) return false;
        
        return Arrays.deepEquals(((Board) this).blocks, ((Board) that).blocks);
    }
    
    // all neighboring boards
    public Iterable<Board> neighbors() {
        return new Iterable<Board>() {
            @Override
            public Iterator<Board> iterator() {
                return new Iterator<Board>() {
                    private int position;
                    private Board[] items = getNeibors().toArray(new Board[getNeibors().size()]);
                    
                    
                    private Board getNeibor(int[][] tile, int x1, int y1, int x2, int y2) {
                    int[][] b = deepCopy(blocks);
                    swap(b,x1,y1,x2,y2);
                    return new Board(b);
                    }
                    private ArrayList<Board> getNeibors() {
                        int temp = 0;
                        ArrayList<Board> board = new ArrayList<>();
                        for (int row = 0; row < dimension(); row++)
                            for (int col = 0; col < dimension(); col++)
                                if (blocks[row][col] == 0) {
//                                    StdOut.println("row="+row + " col=" + col);
                                    if (row > 0) {
                                        int [][] boardNorth = new int[blocks[0].length][];
                                        for (int i = 0; i < blocks[0].length; i++) {
                                            int[] aMatrix = blocks[i];
                                            int aLength = aMatrix.length;
                                            boardNorth[i] = new int[aLength];
                                            System.arraycopy(aMatrix, 0, boardNorth[i], 0, aLength);
                                        }   
                                    

                                        temp = boardNorth[row][col];
                                        boardNorth[row][col] =  boardNorth[row - 1][col];
                                        boardNorth[row - 1][col] =  temp;
                                        Board b1 = new Board(boardNorth);
                                        board.add(b1);
                                    }
                            
                                    if (row < dimension()-1) {
                                        int [][] boardSouth = new int[blocks[0].length][];
                                        for (int i = 0; i < blocks[0].length; i++) {
                                            int[] aMatrix = blocks[i];
                                            int   aLength = aMatrix.length;
                                            boardSouth[i] = new int[aLength];
                                            System.arraycopy(aMatrix, 0, boardSouth[i], 0, aLength);
                                        }     
                                    
                                        temp = boardSouth[row][col];
                                        boardSouth[row][col] =  boardSouth[row + 1][col];
                                        boardSouth[row + 1][col] =  temp;
                                        Board b2 = new Board(boardSouth);
                                        board.add(b2);
                                    }
                             
                             
                                    if (col > 0) {
                                        int [][] boardWest = new int[blocks[0].length][];
                                        for (int i = 0; i < blocks[0].length; i++) {
                                            int[] aMatrix = blocks[i];
                                            int   aLength = aMatrix.length;
                                            boardWest[i] = new int[aLength];
                                            System.arraycopy(aMatrix, 0, boardWest[i], 0, aLength);
                                        }     
                                        temp = boardWest[row][col];
                                        boardWest[row][col] =  boardWest[row][col - 1];
                                        boardWest[row][col - 1] =  temp;
                                        Board b3 = new Board(boardWest);
                                        board.add(b3);
                                    }
                              
                                    if (col < dimension()-1) {
                                        int [][] boardEast = new int[blocks[0].length][];
                                        for (int i = 0; i < blocks[0].length; i++) {
                                            int[] aMatrix = blocks[i];
                                            int   aLength = aMatrix.length;
                                            boardEast[i] = new int[aLength];
                                            System.arraycopy(aMatrix, 0, boardEast[i], 0, aLength);
                                        }     
                                        temp = boardEast[row][col];
                                        boardEast[row][col] =  boardEast[row][col + 1];
                                        boardEast[row][col + 1] =  temp;
                                        Board b4 = new Board(boardEast);
                                        board.add(b4);
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
        if (inBlocks == null) throw new java.lang.IllegalArgumentException();
        for (int i = 0; i < inBlocks.length-1; i++)
            if (inBlocks[i].length != inBlocks[i+1].length) throw new java.lang.IllegalArgumentException();   
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
    
    private void swap(int[][] blocks, int x, int y, int i, int j) {
        int swap = blocks[x][y];
        blocks[x][y] = blocks[i][j];
        blocks[i][j] = swap;
    }
    // unit tests (not graded)

}
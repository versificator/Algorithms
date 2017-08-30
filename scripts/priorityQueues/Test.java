import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdStats;

public class Test {

public static void main(String[] args) {

    // create initial board from file
    //In in = new In(args[0]);
    In in = new In("/samples/8puzzle/puzzle25.txt");
    int n = in.readInt();
    int[][] blocks = new int[n][n];
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            blocks[i][j] = in.readInt();
    Board initial = new Board(blocks);
    
    String lines[] = initial.toString().split("\n");
    for (String s : lines) StdOut.println(s);
    
    
    StdOut.println("board dimension = " + initial.dimension());
    StdOut.println("number of blocks out of place. hamming = " + initial.hamming());
    StdOut.println("sum of Manhattan distances between blocks and goal manhattan = " + initial.manhattan());
    StdOut.println("isGoal = " + initial.isGoal());
    
    
    Board twin = initial.twin();
    
  StdOut.println("---------------------------------------------------------------------------");
    StdOut.println("Twin matrix:");
    String twinLines[] = twin.toString().split("\n");
        for (String s : twinLines) StdOut.println(s);
    
  StdOut.println("---------------------------------------------------------------------------\n\n\n");
    // solve the puzzle
  
  
  
    StdOut.println("Neighbors : ");
    StdOut.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
    for (Board neighbor: initial.neighbors())
         StdOut.println(neighbor.toString() + "  neighbor.manhattan = " + neighbor.manhattan());
  
    StdOut.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
    Solver solver = new Solver(initial);
    
    StdOut.println("solver.moves() =" + solver.moves());

    // print solution to standard output
    if (!solver.isSolvable())
        StdOut.println("No solution possible");
    else {
        StdOut.println("Minimum number of moves = " + solver.moves());
        for (Board board : solver.solution())
            StdOut.println(board);
    }
    
  StdOut.println("---------------------------------------------------------------------------");
  
  

    
    

}



}
import edu.princeton.cs.algs4.MinPQ;
// import java.util.Comparator;
 import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;


//import edu.princeton.cs.algs4.StdOut;


public class Solver {
    
//    private Board initial;
    private boolean isSolvable;
    private Node currentNode;
    private class Node implements Comparable<Node> {
  
        private final Node previous;
        private final Board board;
        private int moves = 0, priority;
  
        public Node(Node previous, Board board, int moves) {
            this.previous = previous;
            this.board = board;
            this.moves = moves;
            this.priority = board.manhattan() + moves;
        }
        
        public int compareTo(Node that) {
            if (this.priority > that.priority) return 1;
            if (this.priority < that.priority) return -1;
            return 0;
        }
    }
    
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board inInitial) {
        MinPQ<Node> pq = new MinPQ<Node>();
        MinPQ<Node> pqTwin = new MinPQ<Node>(); 
        Board initial = inInitial;
        currentNode = new Node(null, initial, 0);
        Node currentTwinNode = new Node(null, initial.twin(), 0);

        if (initial.isGoal()) {
            isSolvable = true;
            currentNode = new Node(null,initial,0);
            return;
        }
        

        
//        StdOut.println("StartTwinNode " + startTwinNode.board.toString());
        pq.insert(currentNode);
        pqTwin.insert(currentTwinNode);
        
        
        while (!currentNode.board.isGoal() && !currentTwinNode.board.isGoal() ) {
            //StdOut.println("currentTwinNode.board.isGoal()  = " + currentTwinNode.board.isGoal() +   "   currentTwinNode.board.manhattan() = " + currentTwinNode.board.manhattan());
          //   StdOut.println(currentTwinNode.board.toString());
            
            for (Board nextBoard: currentNode.board.neighbors())
              if (!nextBoard.equals(currentNode.board)) {
                    pq.insert(new Node(currentNode, nextBoard, currentNode.moves + 1));
                    //StdOut.println("neighbors at Solver: " + nextBoard.toString() + "   manhattan =  " + nextBoard.manhattan());
            }
            for (Board nextTwinBoard: currentTwinNode.board.neighbors())
                if (!nextTwinBoard.equals(currentTwinNode.board)) {
                    pqTwin.insert(new Node(currentTwinNode, nextTwinBoard, currentTwinNode.moves + 1));

                }
            
            currentNode = pq.delMin();
            //StdOut.println("Current Node " + currentNode.board.toString());
            //StdOut.print(" manhattan = " + currentNode.board.manhattan() + "  currentNode.board.isGoal() = " + currentNode.board.isGoal());
//            StdOut.print(" priority = " + currentNode.priority);
//            StdOut.println(" moves = " + currentNode.moves);
            currentTwinNode = pqTwin.delMin();
            //StdOut.println("TwinNode " + currentTwinNode.board.toString() + "  currentTwinNode.board.isGoal() = "  + currentTwinNode.board.isGoal());
 //           StdOut.println("!currentNode.board.isGoal() && !currentTwinNode.board.isGoal()= "   + (!currentNode.board.isGoal() && !currentTwinNode.board.isGoal()  ));
        }
        
       
         if (currentNode.board.isGoal()) { 
                isSolvable = true;
//                lastNode = currentNode;
            }
          else if (currentTwinNode.board.isGoal()) {
                isSolvable = false;
//                lastNode = null;
            }
        
    }
    
    // is the initial board solvable?  
    public boolean isSolvable() {
        return isSolvable; 
    }
  
    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (isSolvable && currentNode != null) return currentNode.moves;
        else if (!isSolvable) return -1;
    return 0;
      
    }
  
    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        Node goalNode = currentNode;

        if (isSolvable && goalNode != null) {
            Stack<Board> solution2 = new Stack<Board>();
            while (goalNode != null) {
       //        StdOut.println("Population  " + goalNode.board.toString());
                solution2.push(goalNode.board);     
                goalNode = goalNode.previous; 
            }
            Stack<Board> solution = new Stack<Board>();
            while (!solution2.isEmpty()) {
              Board t = solution2.pop();
       //       StdOut.println(t.toString());
                solution.push(t);
            }
            return solution;
        
            
        }
        return null;
    }
    
    // solve a slider puzzle (given below)
}
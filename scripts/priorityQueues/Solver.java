import edu.princeton.cs.algs4.MinPQ;
import java.util.Stack;

public class Solver {
    
//    private Board initial;
    private boolean isSolvable;
    private final Node lastNode;
    
    private class Node implements Comparable<Node> {
        private final Node previous;
        private final Board board;
        private final int moves, priority;

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
        if (inInitial == null) throw new java.lang.IllegalArgumentException();
        MinPQ<Node> pq = new MinPQ<Node>();
        MinPQ<Node> pqTwin = new MinPQ<Node>(); 
        Board initial = inInitial;
        Node currentNode = new Node(null, initial, 0);
        Node currentTwinNode = new Node(null, initial.twin(), 0);  
        if (initial.isGoal()) {
            isSolvable = true;
            lastNode = currentNode;
            return;
        }

        pq.insert(currentNode);
        pqTwin.insert(currentTwinNode);

        while (true) {
            for (Board nextBoard: currentNode.board.neighbors())
              if (currentNode.previous == null || !nextBoard.equals(currentNode.previous.board)) {
                    pq.insert(new Node(currentNode, nextBoard, currentNode.moves + 1));
            }
            for (Board nextTwinBoard: currentTwinNode.board.neighbors())
                if (currentTwinNode.previous == null || !nextTwinBoard.equals(currentTwinNode.previous.board)) {
                    pqTwin.insert(new Node(currentTwinNode, nextTwinBoard, currentTwinNode.moves + 1));
                }            
            currentNode = pq.delMin();
            currentTwinNode = pqTwin.delMin();
         if (currentNode.board.isGoal()) { 
                isSolvable = true;
                lastNode = currentNode;
                break;
            }
          else if (currentTwinNode.board.isGoal()) {
                isSolvable = false;
                lastNode = null;
                break;
            }
        }
    }
    
    // is the initial board solvable?  
    public boolean isSolvable() {
        return isSolvable; 
    }
  
    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (isSolvable && lastNode != null) return lastNode.moves;
        else if (!isSolvable) return -1;
    return 0;
      
    }
  
    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        Node goalNode = lastNode;

        if (isSolvable && goalNode != null) {
            Stack<Board> solution2 = new Stack<Board>();
            while (goalNode != null) {
                solution2.push(goalNode.board);     
                goalNode = goalNode.previous; 
            }
            Stack<Board> solution = new Stack<Board>();
            while (!solution2.isEmpty()) {
                Board t = solution2.pop();
                solution.push(t);
            }
            return solution;
        }
        return null;
    }
    
    // solve a slider puzzle (given below)
}
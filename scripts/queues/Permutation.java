import edu.princeton.cs.algs4.StdIn;

public class Permutation {

    public static void main(String[] args) {
    
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {  // loop through pairs
            rq.enqueue(StdIn.readString());
        }
        int length = Integer.parseInt(args[0]);
        for (int i = 0; i < length; i++)
            System.out.println(rq.dequeue());
    }  
}

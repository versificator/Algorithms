import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int n;
    // construct an empty randomized queue
    public RandomizedQueue() {
        a = (Item[]) new Object[2];
        n = 0;
    }
    // is the queue empty?
    public boolean isEmpty() {
        return n == 0;
    }
    // return the number of items on the queue
    public int size() {
        return n;
    }
    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new NoSuchElementException();
        if (n == a.length) resize(2*a.length);    // double size of array if necessary
        a[n++] = item;                            // add item
    }
    
    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int randomIndex = StdRandom.uniform(0, n);
        Item item = a[randomIndex];
        a[randomIndex] = a[n-1];
        a[n-1] = null;                              // to avoid loitering
        n--;
        // shrink size of array if necessary
        if (n > 0 && n == a.length/4) resize(a.length/2);
        return item;
    }
    // return (but do not remove) a random item
    public Item sample() {
        if (size()  < 1) throw new NoSuchElementException();
        return a[StdRandom.uniform(0, n)];
    }
    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomArrayIterator();
    }
    
      
    private class RandomArrayIterator implements Iterator<Item> {
        private int i = size();
        
        private int[] idx;
        
        public RandomArrayIterator() {
            idx =  idxShuffle(i);
        }
        private int[] idxShuffle(int idxCapacity) {
            idx = new int[idxCapacity];
            for (int x = 0; x < idxCapacity; ++x) {
                idx[x] = i;
            }
            StdRandom.shuffle(idx);
//            System.out.println(Arrays.toString(idx));
            return idx;
        }
          
        public boolean hasNext() {
            return (!isEmpty() && i > 0);
        }
          
        public Item next() {
          if (i < 1 || isEmpty()) throw new NoSuchElementException();
            return a[idx[--i]];
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    // resize the underlying array holding the elements
    private void resize(int capacity) {
        assert capacity >= n;

        // textbook implementation
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;

       // alternative implementation
       // a = java.util.Arrays.copyOf(a, capacity);
    }
    // unit testing (optional)
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        System.out.println(rq.isEmpty());
        rq.enqueue("1 item");
        rq.enqueue("2 item");
        rq.enqueue("3 item");
        rq.enqueue("4 item");
        rq.enqueue("5 item");
        rq.enqueue("6 item");
//        System.out.println(rq.isEmpty());
//        System.out.println(rq.sample());
//        System.out.println(rq.dequeue().toString());
        
        for (String s : rq)
            System.out.println(s);
        
    }
}

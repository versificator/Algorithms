import java.util.Iterator;
import java.util.NoSuchElementException;
public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int n = 0;
    
    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        n = 0;
    }
    
    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }
    
    // return an iterator over items in order from front to end
    @Override
    public Iterator<Item> iterator() { 
        return new ListIterator(); 
    }
      
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() {
            return current != null;  
        }
        
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
 
    }
    

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }
    // return the number of items on the deque
    public int size() {
        return n;
    }
    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        if (oldFirst == null) {
            last = first;
        }
        else {
            oldFirst.previous = first;
        }
        n++;
    }
    // add the item to the end
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.previous = oldLast;
        if (oldLast == null) {
            first = last;
        }
        else {
          oldLast.next = last;
        } 
        n++;
    }
    // remove and return the item from the front
    public Item removeFirst() {
        if (n == 0) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        if (first == null) {
            last = null;
        } 
        else {
            first.previous = null;
            if (first.next == null) last.previous = null;
        }
        n--;
        return item;
    }
    // remove and return the item from the end
    public Item removeLast() {
        if (n == 0) throw new NoSuchElementException();
        Item item = last.item;
        last = last.previous;
        if (last == null) {
            first = null;
        }
            else {
                last.next = null;
                if (last.previous == null) first.next = null;
            }
        n--;
        return item;
    }
    
    
    // check internal invariants
/*    private boolean check() {

        // check a few properties of instance variable 'first'
        if (n < 0) {
            return false;
        }
        if (n == 0) {
            if (first != null || last != null) return false;
        }
        else if (n == 1) {
            if (first == null)      return false;
            if (first.next != null) return false;
            if (last == null)       return false;
            if (last.previous != null) return false;
        }
        else {
            if (first == null || last == null)      return false;
            if (first.next == null || last.previous == null) return false;
        }

        // check internal consistency of instance variable n
        int numberOfNodes = 0;
        for (Node x = first; x != null && numberOfNodes <= n; x = x.next) {
            numberOfNodes++;
        }
        if (numberOfNodes != n) return false;

        return true;
    }*/
    // unit testing (optional)
    public static void main(String[] args) {
        Deque<String> deq = new Deque<>();
        System.out.println(deq.isEmpty());
        deq.addFirst("1 element");
        deq.addFirst("2 element");
        deq.addFirst("3 element");
        deq.addLast("1 last element");
        deq.addLast("2 last element");
        deq.addFirst("4 element");
        System.out.println(deq.isEmpty());
        System.out.println(deq.size());
        for (String s : deq)
            System.out.println(s);
    }
}

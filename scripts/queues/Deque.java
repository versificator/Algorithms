import java.util.Iterator;
public class Deque<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last = null;
    private int capacity, size = 0;
    
    private class Node {
        Item item;
        Node next;
        Node previous;
    }
    
    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() { return new ListIterator(); }
      
    private class ListIterator implements Iterator<Item> {
        
        private Node current = last;
        
        public boolean hasNext() {
            return current != null;  
        }
        
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    
    // construct an empty deque
    public Deque() {

    };
    // is the deque empty?
    public boolean isEmpty() {
        return first == null || last == null;
    }
    // return the number of items on the deque
    public int size() {
        return size;
    }
    // add the item to the front
    public void addFirst(Item item) {
        size++;
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.previous = oldFirst;
        if (oldFirst != null) oldFirst.next = first;
        if (isEmpty()) last = first;
    }
    // add the item to the end
    public void addLast(Item item) {
        size++;
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = oldLast;
        if (oldLast != null) oldLast.previous = last;
        if (isEmpty()) first = last;
    }
    // remove and return the item from the front
    public Item removeFirst() {
        size--;
        Item item = first.item;
        first = first.previous;
        if (isEmpty()) last = first = null;
        return item;
    }
    // remove and return the item from the end
    public Item removeLast() {
        size--;
        Item item = last.item;
        last = last.next;
        if (isEmpty()) last = first = null;
        return item;
    }
    
    // unit testing (optional)
    public static void main(String[] args) {
        Deque<String> deq = new Deque<>();
        System.out.println(deq.isEmpty());
        deq.addFirst("1 element");
        deq.addFirst("2 element");
        deq.addFirst("3 element");
        deq.addLast("1 last element");
        deq.addLast("2 last element");
        System.out.println(deq.isEmpty());
        System.out.println(deq.size());
        for (String s : deq)
            System.out.println(s);
    }
}

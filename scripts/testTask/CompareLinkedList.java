//Given two linked lists, represented as linked lists (every character is a node in linked list). 
//Write a function compare() that works similar to strcmp(), i.e., it returns 0 if both strings are same,
//1 if first linked list is lexicographically greater, and -1 if second string is lexicographically greater.
//
//Examples:
//
//Input: list1 = g->e->e->k->s->a
//       list2 = g->e->e->k->s->b
//Output: -1
//
//Input: list1 = g->e->e->k->s->a
//       list2 = g->e->e->k->s
//Output: 1
//
//Input: list1 = g->e->e->k->s
//       list2 = g->e->e->k->s
//Output: 0


class CompareLinkedList{
  static Node a,b;
  static class Node{
    char data;
    Node next;
    
    Node(char data){
      this.data = data;
      this.next = null;
    }
    
  }
  int compare(Node node1, Node node2) {
    if (node1 == null && node2 == null) return 0;
    
    if (node1 != null && node2 != null) {
      while(node1 != null && node2 != null && node1.data == node2.data) {
        
        System.out.println("node1 == node2 == " + node1.data);
        node1 = node1.next;
        node2 = node2.next;
      }
      
        if (node1 != null &&( node2 == null|| node1.data > node2.data )) return 1;    
            
        if (node2 != null &&(node2 == null || node2.data > node1.data || node1 == null)) return -1;  
            
            }
    return 0;  
    } 
  public static void main(String args[]){
    CompareLinkedList cll = new CompareLinkedList();
    cll.a = new Node('a');
    a.next = new Node('b');
    a.next.next = new Node('c');
    
    
    cll.b = new Node('a');
    b.next = new Node('b');
    b.next.next = new Node('c');
    
    System.out.println(cll.compare(cll.a,cll.b));
  }
  }
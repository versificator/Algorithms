class SortedLinkedList{
  Node head;
  
  class Node{
    int data;  
    Node next;
    
    Node(int data){
      this.data = data;
      this.next = null;
    }
  }
  
  
  void sortedInsert(Node newNode) {
    Node currentNode;
    if (head==null || newNode.data<head.data){
      newNode.next = head;
      head = newNode;
    }
    else {
      currentNode = head;
      while (currentNode.next != null && currentNode.next.data < newNode.data) {  
        currentNode = currentNode.next;
      }
      newNode.next = currentNode.next;
      currentNode.next = newNode;
    }
  }
  
  Node newNode(int data){
    Node n = new Node(data);
    return n;
  }
  
  
  void printList(){
    Node currentNode = head;
    while (currentNode != null){
      System.out.println(currentNode.data);
      currentNode = currentNode.next;
    }
    
    
  }
  
  public static void main(String args[]){
    
    SortedLinkedList list = new SortedLinkedList();
    Node newNode;
    newNode = list.newNode(5);
    list.sortedInsert(newNode);
    newNode = list.newNode(10);
    list.sortedInsert(newNode);
    newNode = list.newNode(7);
    list.sortedInsert(newNode);
    newNode = list.newNode(3);
    list.sortedInsert(newNode);
    newNode = list.newNode(1);
    list.sortedInsert(newNode);
    newNode = list.newNode(9);
    list.sortedInsert(newNode);
    System.out.println("Created Linked List");
    list.printList();
  }
  
}
class Node{
  int value;
  Node left;
  Node right;
  
  Node(int value){
    this.value = value;
  }
  
  
  
}



public class MinimumDepthOfBinaryTree{
  
  Node root;
  
  public int minimumDepth(){
    return minimumDepth(root);
  }
  
  public int minimumDepth(Node root){
    
    if (root == null) return 0;
    
    if (root.left == null && root.right == null) return 1;
    
    if (root.left == null) return minimumDepth(root.right) + 1;
    
    if (root.right == null) return minimumDepth(root.left) + 1;
    
    
    return Math.min(minimumDepth(root.left), minimumDepth(root.right)) + 1;
    
  }
  
  public static void main(String args[]){
    MinimumDepthOfBinaryTree bt = new MinimumDepthOfBinaryTree();
    bt.root = new Node(1);
    bt.root.left = new Node(2);
    bt.root.right = new Node(3);
    bt.root.right.left = new Node(6);
    bt.root.left.left = new Node(4);
    bt.root.left.left = new Node(5);
    
    System.out.println(bt.minimumDepth());
  }
}
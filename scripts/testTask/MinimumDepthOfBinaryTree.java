class Node{
  int value;
  Node left;
  Node right;
  
  Node(int value){
    this.value = value;
  }  
}

public class MinimumDepthOfBinaryTree{
  
  Node root, subTreeRoot;
  
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
  
  public  boolean isBinaryTreeFull(Node root){
    
    if (root == null || (root.left == null && root.right == null)) return true;
    
    if ((root.left != null && root.right == null) || (root.left == null && root.right != null) ) return false;
    
    return isBinaryTreeFull(root.left) && isBinaryTreeFull(root.right);
  }
  
  public Node findNode(Node root, int value){
    if (root == null) return null;
    if (root.value == value) return root;

    Node left = findNode(root.left, value);
    Node right = findNode(root.right, value);
    return (left == null ? right : left);
  }
  
  public boolean isTreeSubtree(Node tree, Node subtree)  {
    if (tree == null && subtree == null) return true;
    Node root = findNode(tree,subtree.value);
    return isSameRootTree(root,subtree);
  }
  
  public boolean isSameRootTree(Node tree, Node subtree){ 
    if (tree == null && subtree == null) return true;
    if (tree == null || subtree == null || tree.value != subtree.value) return false; 
    if (tree.value == subtree.value && tree.left == null && tree.right == null && subtree.left == null && subtree.right == null) return true;
    return (isSameRootTree(tree.left,subtree.left) && (isSameRootTree(tree.right,subtree.right)));
  }
  
  
  public static void main(String args[]){
    MinimumDepthOfBinaryTree bt = new MinimumDepthOfBinaryTree();
    bt.root = new Node(1);
    bt.root.left = new Node(2);
    bt.root.right = new Node(3);
    bt.root.right.left = new Node(6);
    bt.root.right.right = new Node(7);
    bt.root.left.left = new Node(4);
    bt.root.left.right = new Node(8);
    bt.root.left.left.left = new Node(5);
    
    bt.subTreeRoot = new Node(2);
    bt.subTreeRoot.left = new Node(4);
    bt.subTreeRoot.right = new Node(8);
    bt.subTreeRoot.left.left = new Node(5);
    System.out.println("isBinaryTreeFull " + bt.isBinaryTreeFull(bt.root));
    System.out.println("find node " + bt.findNode(bt.root,1));
    System.out.println("isTreeSubtree " + bt.isTreeSubtree(bt.root, bt.subTreeRoot));
    System.out.println(bt.minimumDepth());
  }
}
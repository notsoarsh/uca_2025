public class RBT<Key extends Comparable<Key>, Value> {
  public Node root;
  /*
   * Node class [private]
   */
  private class Node {
    Key key;
    Value val;
    Node left;
    Node right;
    int N; // number of nodes under this node
    boolean isRed;
    /*
     * Constructor [default]
     */
    Node(Key key, Value value) {
      this.key = key;
      this.val = value;
      this.left = null;
      this.right = null;
      this.N = 1;
      this.isRed = true; // initially node is red
    }
  } 

    /* METHODS */

  private boolean isRed(Node x) {
    return x != null && x.isRed;
  }  
  /*
   * Insert [public]
   * @returns the updated head
  */
  public Node insertNode (Key key, Value val) {
    root = insertHelper(root, key, val);
    root.isRed = false; // root always black
    return root;
  }

  private Node insertHelper(Node n, Key k, Value v) {
    //if we reach a null , we put a node here
    if (n == null) return new Node(k, v);

    int cmp = k.compareTo(n.key);
    if (cmp == 0) {
      n.val = v;
      return n;
    } else if (cmp > 0) {
      n.right = insertHelper(n.right, k, v);
    } else {
      n.left = insertHelper(n.left, k, v);
    }

    //handle the three cases for Red Black Tree validation
    if (!isRed(n.left) && isRed(n.right)) {
      n = leftRotate(n);
    }
    if (isRed(n.left) && isRed(n.left.left)) {
      n = rightRotate(n);
    }
    if (isRed(n.left) && isRed(n.right)) {
      flipColor(n);
    }

    //upate the size for the current node as well
    n.N = 1 + sizeOf(n.left) + sizeOf(n.right);
    
    return n;
  }
  /**
   * Method : leftRotate - Rotates the subtree to left in case leftChild is Black and rightChild is Red
   * @param n
   * @return new upated node
   */
  private Node leftRotate(Node n) {
    Node temp = n.right;
    n.right = temp.left;
    temp.left = n;
    temp.isRed = n.isRed;
    n.isRed = true;
    temp.N = n.N;
    n.N = 1 + sizeOf(n.left) + sizeOf(n.right);
    return temp;
  }
  /**
   * Method : rightRotate - Rotates the subtree to right in case leftChild is Red and leftGrandChild is Red
   * @param n
   * @return new upated node
   */
  private Node rightRotate(Node n) {
    Node temp = n.left;
    n.left = temp.right;
    temp.right = n;
    temp.isRed = n.isRed;
    n.isRed = true;
    temp.N = n.N;
    n.N = 1 + sizeOf(n.left) + sizeOf(n.right);
    return temp;
  }
  /**
   * Method : flipColor - Flips the color of the both child nodes from Red to Black
   * @param n
   * @return new upated node
   */
  private void flipColor(Node n) {
    n.isRed = !n.isRed;
    n.left.isRed = !n.left.isRed;
    n.right.isRed = !n.right.isRed;
  }

  /**
   * Method : sizeOf - Returns the number of nodes in the subtree
   * @param n
   * @return int 
   */
  private int sizeOf(Node n) {
    return n == null ? 0 : n.N;
  }

  /**
   * Method- get - Returns the value for a particular node
   * @param key
   * @return
   */
  public Value get(Key key) {
    return get(root, key);
  }
  
  private Value get(Node x, Key key) {
    if (x == null) return null;
    
    int cmp = key.compareTo(x.key);

    if (cmp == 0) return x.val;
    else if (cmp < 0) return get(x.left, key);
    else return get(x.right, key);
  } 
  /**
   * Method - rank - Returns the rank of a node
   * @param key
   * @return
   */
  public int rank(Key key) {
    Value v = get(key);
    if (v == null) return -1;
    return rank(root, key);
  }
  
  private int rank(Node x, Key k) {
    if (x == null) return 0;
    int cmp = k.compareTo(x.key);
    if (cmp < 0) return rank(x.left, k);
    else if (cmp > 0) return 1 + sizeOf(x.left) + rank(x.right, k);
    else return sizeOf(x.left);
  }

  public void printTree(Node n) {
    if (n == null) return;
    printTree(n.left);
    System.out.println("Key: "  + n.key + " Value: " + n.val);
    printTree(n.right);
  }
  public static void main(String[] args) {
    RBT<Integer, String> tree = new RBT<>();
    tree.insertNode(2, "Apple");
    tree.insertNode(5, "Orange");
    tree.insertNode(1, "Banana");
    tree.insertNode(2, "Onion");
    tree.insertNode(7, "Pear");

    tree.printTree(tree.root);
  }
}

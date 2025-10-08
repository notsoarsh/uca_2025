public class BST<Key extends Comparable<Key>, Value> {

  private Node root;

  private class Node {
    Key key;
    Value val;
    Node left;
    Node right;
    int N; //no of nodes under this node
    Node(Key key, Value val) {
      this.key = key;
      this.val = val;
      this.N = 1;
    }
  }

  //Min key
  public Key min() {
     return minKey(root); 
  }
  
  private Key minKey(Node x) {
    if (x == null) return null;
    if (x.left == null) return x.key;
    return minKey(x.left); 
  }

  //Max key
  public Key max() {
    return maxKey(root);
  }

  private Key maxKey(Node x) {
    if (x == null) return null;
    if (x.right == null) return x.key;
    return maxKey(x.right);
  }
  
  //Function to delete the min
  public void delMin() {
    root = delMin(root);
  }  
  
  private Node delMin(Node x) {
    if (x.left == null) return x.right;
    x.left = delMin(x.left);
    return x; 
  }
  
  //Floor - largest number just smaller than given
  public Key floor(Key key) {
    return null;
  }
  
  //Ceil - Smallest number just greater than given
  public Key ceil(Key key) {
    return null;
  }

  public int rank(Key key) {
    Value v = get(key);
    if (v == null) return -1;
    return rank(root, key);
  }
  
  private int rank(Node x, Key k) {
    int cmp = k.compareTo(x.key);
    if (cmp == 0) return sizeof(x.left);
    if (cmp < 0) return rank(x.left, k);
    return 1 + sizeof(x.left) + rank(x.right, k);
  }

  //Get method
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
  
  private int sizeof(Node x) {
    return x == null ? 0 : x.N;
  }

  //Put method
  public void put(Key key, Value val) {
    root = put(root, key, val);
  }
  
  private Node put(Node x, Key key, Value val) {
    if (x == null) return new Node(key, val);

    int cmp = key.compareTo(x.key);
    
    if (cmp == 0) x.val = val; //overrite the key
    else if (cmp < 0) x.left = put(x.left, key, val);
    else x.right = put(x.right, key, val);
    x.N += sizeof(x.left) + sizeof(x.right);    
    return x;
  }

  public static void main(String[] args) {
    BST<Integer, String> bst = new BST<Integer, String>();
    
    bst.put(5, "A");
    bst.put(1, "X");
    bst.put(3, "Y");
    bst.put(4, "Z");
    bst.put(7, "A");

    assert bst.get(5).equals("A") : "Test failed for key 5";
    assert bst.get(1).equals("X") : "Test failed for key 5";
    assert bst.get(3).equals("Y") : "Test failed for key 5";
    assert bst.get(4).equals("Z") : "Test failed for key 5";
    assert bst.get(7).equals("A") : "Test failed for key 5";
    assert bst.get(10) == null : "Test failed for key 10 , key missing";
    System.out.println(bst.min());
    System.out.println(bst.max());
    System.out.println("All test cases passed successfully");
    System.out.println(rank(7)); 
  }
}

public class BST<Key extends Comparable<Key>, Value> {

  private Node root;

  private class Node {
    Key key;
    Value val;
    Node left;
    Node right;

    Node(Key key, Value val) {
      this.key = key;
      this.val = val;
    }
  }

  //Min key
  public Key min() {
  }

  //Max key
  public Key min() {
  }
  
  //Floor - largest number just smaller than given
  public Key floor(Key key) {
  }
  
  //Ceil - Smallest number just greater than given
  public Key ceil(Key key) {
  }

  public int rank(Key key) {
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
  
  //Put method
  public void put(Key key, Value val) {
    root = put(x, key, val);
  }
  
  private Node put(Node x, Key key, Value val) {
    if (x == null) return new Node(key, val);

    int cmp = key.compareTo(x.key);
    
    if (cmp == 0) x.val = val; //overrite the key
    else if (cmp < 0) x.left = put(x.left, key, val);
    else x.right = put(x.right, key, val);
    return x;
  }

  public static void main(String[] args) {
    BST<Integer, String> bst = new BST<Integer, String>();
    
    bst.put(5, "A");
    bst.put(1, "X");
    bst.put(3, "Y");
    bst.put(4, "Z");
    bst.put(7, "A");

    assert 
    
  }
}

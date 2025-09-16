public class HashTable<Key, Value> {
  static class Node {
    Object key;
    Object value;
    Node next;

    Node(Object key, Object value, Node next) {
      this.key = key;
      this.value = value;
      this.next = next;
    }
  }

  private int M = 32;
  private Node[] ht = new Node[M];

  public int hash(Key key) {
    return (key.hashCode() & 0x7fffffff) % M;
  }

  public void put(Key key, Value value) {
    int i = hash(key);
    
    for (Node x = ht[i]; x != null; x = x.next) {
      if (x.key.equals(key)) {
        x.value = value;
        return;
      }
    }

  //Insert a node at the start of the linked list
  ht[i] = new Node(key, value, ht[i]);
  
  }

  public Value get(Key key) {
    if (key == null) return null;
    int i = hash(key);
  
    for (Node x = ht[i]; x != null; x = x.next) {
      if (x.key.equals(key)) {
        return ((Value)x.value);
      }
    }
    return null;
  }
}

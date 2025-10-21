public class HashTable<Key, Value> {

  static class Node<K, V> {
    K key;
    V val;
    Node<K , V> next;

    Node(K key, V val, Node<K, V> next) {
      this.key = key;
      this.val = val;
      this.next = next;
    }
  }

  //define the size of table
  private int M = 32;
  //define bucket array
  private Node<Key, Value>[] ht;

  public HashTable() {
    this.ht = (Node<key, val>[])new Node[M];
  }
  
  public int hash(Key key) {
    return (key.hashCode() & 0x7fffffff) % M;
  }

  public void put(Key key, Value val) {
    int i = hash(key);
    for (Node<Key, Value> temp = ht[i]; temp != null; temp = temp.next) {
      if (temp.key.equals(key)) {
        temp.val = val;
        return;
      }
    }
    ht[i] = new Node<>(key, value, ht[i]);
  }

  public int get(Key key) {
    int i = hash(key);
    for (Node<Key, Value> temp = ht[i]; temp != null; temp = temp.next) {
      if (temp.key.equals(key)) {
        return temp.val;
      }
    }
    return null;
  }

  public static void main(String[] args) {
    HashTable<String, Integer> fruits = new HashTable<>();
    fruits.put("Apple", 10);
    fruits.put("Orange", 12);
    fruits.put("Apple", 5);
    int A = fruits.get("Apple");
    int O = fruits.get("Orange");
    System.out.println(A + " " + O);
  }
  

}

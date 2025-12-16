import java.util.Arrays;

public class OpenAddressing {
  private Node[] data;
  private final int size;
  private final int PRIME = 7;

  public OpenAddressing(int size) {
    this.size = size;
    data = new Node[size];
    for (int i = 0; i < size; i++) {
      data[i] = new Node();
    }
  }

  private enum State {
    Free, Occupied, Deleted;
  }

  private static class Node {
    private Integer key;
    private State state;

    public Node(Integer key) {
      this.key = key;
      this.state = State.Occupied;
    }

    public Node() {
      this.key = null;
      this.state = State.Free;
    }
  }

  private int hashFunction(Integer key, int h) {
    if (h == -1) return key % size;
    return (h + 1) % this.size; // linear probing
  }

  public void insert(int key) {

    int h = hashFunction(key, -1);
    int og = h;

    while (data[h].state == State.Occupied) {

      if (data[h].key != null && data[h].key == key) return; // avoid duplicate

      h = hashFunction(key, h);
      if (h == og) throw new RuntimeException("Overflow");
    }

    data[h] = new Node(key);
  }

  public boolean search(int key) {
    int h = hashFunction(key, -1);
    int og = h;

    while (true) {
      if (data[h].state == State.Occupied && data[h].key == key) return true;

      if (data[h].state == State.Free) return false;

      h = hashFunction(key, h);
      if (h == og) return false;
    }
  }

  public void delete(int key) {
    int h = hashFunction(key, -1);
    int og = h;

    while (true) {
      if (data[h].state == State.Occupied && data[h].key == key) {
        data[h].state = State.Deleted;
        return;
      }
      if (data[h].state == State.Free) {
        System.out.println("Key not found!");
        return;
      }
      h = hashFunction(key, h);
      if (h == og) {
        System.out.println("Key not found!");
        return;
      }
    }
  }

  @Override
    public String toString() {
      Integer[] temp = new Integer[size];
      State[] states = new State[size];
      for (int i = 0; i < size; i++) {
        temp[i] = data[i].key;
        states[i] = data[i].state;
      }
      return "Keys= " + Arrays.toString(temp) +
        "\nStates= " + Arrays.toString(states) +
        "\nSize: " + size;
    }

  public static void main(String[] args) {
    OpenAddressing o = new OpenAddressing(10);
    o.insert(15);
    o.insert(25);
    o.insert(35);
    o.insert(35);
    o.insert(8);
    o.insert(45);
    o.insert(65);
    System.out.println(o);

    o.delete(15);
    System.out.println(o.search(15)); // false
    System.out.println(o.search(65)); // true
  }
}


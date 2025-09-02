

public class MaxPriorityQueue<T> {
  private T[] pq;
  private int size;
  
  public MaxPriorityQueue(int capacity) {
    this.pq = (T []) new Object[capacity + 1];
    this.size = 0;
  }

  public boolean isEmpty() {
    return this.size == 0;
  }

  public int size() {
    return this.size;
  }

  private void exchange(int i, int j) {
    T temp = this.pq[i];
    this.pq[i] = this.pq[j];
    this.pq[j] = temp;
  }

  private boolean isLess(int i, int j) {
    return ((Comparable<T>) this.pq[i]).compareTo(((T) this.pq[j])) < 0;
  }

  public void insert(T key) {
    this.pq[++size] = key;
    swim(size);
  }

  private void swim(int idx) {
    while (idx > 1 && isLess(idx / 2, idx)) {
      exchange(idx, idx / 2);
      idx = idx / 2;
    }
  }

  public T deleteMax() {
    T elem = this.pq[1];
    exchange(1, size--);
    sink(1);

    return elem;
  }

  private void sink(int idx) {
    while (2 * idx <= size) {
      int j = 2 * idx;

      if (j < size && isLess(j, j+1)) {
        j++;
      }
      if (!isLess(idx, j)){
        break;
      }
      exchange(idx, j);
      idx = j;
    }
  }

  public static void main(String[] args) {
    MaxPriorityQueue<Integer> pq = new MaxPriorityQueue<Integer>(32);
    pq.insert(2);
    pq.insert(7);
    pq.insert(4);
    pq.insert(5);
    pq.insert(3);
    pq.insert(3);
    pq.insert(2);
    pq.insert(9);

    assert pq.deleteMax() == 9;
    
    pq.insert(8);

    assert pq.isEmpty() == false;
    assert pq.size() == 8;
    assert pq.deleteMax() == 8;
    assert pq.deleteMax() == 7;
    assert pq.deleteMax() == 5;
    assert pq.deleteMax() == 4;
    assert pq.deleteMax() == 3;
    assert pq.deleteMax() == 3;
    assert pq.deleteMax() == 2;
    assert pq.deleteMax() == 2;
  }
}  

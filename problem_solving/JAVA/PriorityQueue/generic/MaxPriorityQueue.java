public class MaxPriorityQueue<T extends Comparable<T>> {
    private T[] heap;
    private int size;
    @SuppressWarnings("unchecked")
    MaxPriorityQueue(int capacity) {
        this.heap = (T[]) new Comparable[capacity + 1];
        this.size = 0;
    }
    
    public int size() { return size; }
    
    public boolean isEmpty() { return size == 0; }
    
    public void insert(T key) {
        heap[++size] = key;
        swim(size);
    }
    
    private void swim(int child) {
        while (child > 1 && isGreater(child, child / 2)) {
            exchange(child, child / 2);
            child /= 2;
        }
    }
    
    private boolean isGreater(int i, int j) {
        return heap[i].compareTo(heap[j]) > 0;
    }
    
    private void exchange(int i, int j) {
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    
    public T delMax() {
        if (isEmpty()) throw new IllegalStateException("Heap is empty");
        T max = heap[1];
        exchange(1, size--);
        sink(1);
        heap[size + 1] = null;
        return max;
    }
    
    private void sink(int p) {
        while (2 * p <= size) {
            int c = 2 * p;
            if (c < size && isGreater(c + 1, c)) c++;
            if (!isGreater(c, p)) break;
            exchange(p, c);
            p = c;
        }
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

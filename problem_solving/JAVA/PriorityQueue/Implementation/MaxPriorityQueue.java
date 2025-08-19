public class MaxPriorityQueue {
    private int[] pq;
    private int n;

    public MaxPriorityQueue(int capacity) {
        this.pq = new int[capacity + 1]; // index 0 unused
        this.n = 0;
    }

    public boolean isEmpty() {
        return this.n == 0;
    }

    public int size() {
        return this.n;
    }

    private void exch(int i, int j) {
        int temp = this.pq[i];
        this.pq[i] = this.pq[j];
        this.pq[j] = temp;
    }

    private boolean greater(int i, int j) {
        return this.pq[i] > this.pq[j];
    }

    private boolean less(int i, int j) {
        return this.pq[i] < this.pq[j];
    }

    public void insert(int x) {
        this.pq[++n] = x;
        swim(n);
    }

    private void swim(int k) {
        while (k > 1 && greater(k, k/2)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    public int delMax() {
        int elem = this.pq[1];
        exch(1, n--);
        sink(1);
        return elem;
    }

    public void sink(int k) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n - 1 && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }
}

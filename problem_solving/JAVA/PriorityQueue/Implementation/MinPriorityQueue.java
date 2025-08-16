/**
 *This is the implementation of MinPriorityQueue in JAVA.
/

public class MinPriorityQueue {
  private int[];
  private int n;

  public MinPriorityQueue(int capacity) {
    this.pq = new int[capacity + 1];
    this.n = 0;
  }

  public void resize(int new_capacity) {



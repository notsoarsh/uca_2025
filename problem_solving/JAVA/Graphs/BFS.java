mport java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

public class BFS {
  private boolean[] marked;
  private int[] edgeTo;
  private int s;
  
  BFS(Graph g, int s) {
    this.marked = new boolean[g.V()];
    this.edgeTo = new int[g.V()];
    this.s = s;
    bfs(g, s);
  }

  private void bfs(Graph g, int v) {
    Queue<Integer> q = new LinkedList<>();
    q.offer(v);
    marked[v] = true;
    
    while (!q.isEmpty()) {
      int w = q.poll();
      for (int z : g.adj(w)) {  
        if (marked[z]) continue;
        marked[z] = true;
        edgeTo[z] = w;
        q.add(z);
      }
    }    
  }

  public boolean connected(int v) {
    return marked[v];
  }

  public void printShortestPath(int v) {
    if (!connected(v)) return;
    Stack<Integer> path = new Stack<>();
    for (int w = v; w != s; w = edgeTo[w]) {
      path.push(w);
    }
    path.push(s); 
    while (!path.isEmpty()) {
      System.out.print(path.pop() + " ");
    }
    System.out.println();
  }
}

import java.util.Stack;

public class TopologicalSort {
  private boolean[] marked;
  private Stack<Integer> reversePostOrder;
  
  public TopologicalSort(DirectedGraph g) {
    reversePostOrder = new Stack<Integer>();
    marked = new boolean[g.V()];
    
    for (int i = 0; i < g.V(); i++) {
      if (!marked[i]) {
        dfs(g, i);
      }
    }
  }

  private void dfs(DirectedGraph g, int v) {
    marked[v] = true;
    
    for (int w : g.adj(v)) {
      if (!marked[w]) {
        dfs(g, w);
      }
    }
    reversePostOrder.push(v);
  }
  
  public Stack<Integer> order() {
    return this.reversePostOrder;
  }
  
  public static void main(String[] args) {
    DirectedGraph dg = new DirectedGraph(7);
    dg.addEdge(0, 5);
    dg.addEdge(0, 2);
    dg.addEdge(0, 1);
    dg.addEdge(1, 4);
    dg.addEdge(3, 6);
    dg.addEdge(3, 5);
    dg.addEdge(3, 4);
    dg.addEdge(5, 2);
    dg.addEdge(6, 4); 
    dg.addEdge(6, 0);
    dg.addEdge(3, 2);

    TopologicalSort ts = new TopologicalSort(dg);
    Stack<Integer> ans = ts.order();
    while (!ans.isEmpty()) {
      System.out.print(ans.pop() + " ");
    }
  }
}

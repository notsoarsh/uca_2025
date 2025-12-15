import java.util.*;

public class StronglyConnectedComponents {

  private int V;
  private List<Integer>[] graph;
  private List<Integer>[] reverseGraph;

  public StronglyConnectedComponents(int V) {
    this.V = V;
    graph = new ArrayList[V];
    reverseGraph = new ArrayList[V];

    for (int i = 0; i < V; i++) {
      graph[i] = new ArrayList<>();
      reverseGraph[i] = new ArrayList<>();
    }
  }

  // Add edge u -> v
  public void addEdge(int u, int v) {
    graph[u].add(v);
    reverseGraph[v].add(u); // reverse edge
  }

  // Step 1: DFS to fill stack by finish time
  private void fillOrder(int node, boolean[] visited, Stack<Integer> stack) {
    //mark visited
    visited[node] = true;

    for (int nei : graph[node]) {
      if (!visited[nei]) {
        fillOrder(nei, visited, stack);
      } 
    }
    //dfs over for this can add to stack
    stack.push(node);
  }

  // Step 2: DFS on reversed graph
  private void dfsOnReverse(int node, boolean[] visited, List<Integer> component) {
    // mark visited
    visited[node] = true;
    component.add(node);

    for (int nei : reverseGraph[node]) {
      if (!visited[nei]) {
        dfsOnReverse(nei, visited, component);
      }
    }
  }

  // Main function to find SCCs
  public List<List<Integer>> getSCCs() {
    boolean[] visited = new boolean[V];
    Stack<Integer> stack = new Stack<>();
    for (int node = 0; node < V; node++) {
      if (!visited[node]) {
        fillOrder(node, visited, stack);
      }
    }
    
    Arrays.fill(visited, false);
    

    List<List<Integer>> ans = new ArrayList<>();
    while (!stack.isEmpty()) {
      int curr = stack.pop();
      if (!visited[curr]) {
        List<Integer> component = new ArrayList<>();
        dfsOnReverse(curr, visited, component);
        ans.add(component);
      }
    }

    return ans;
  }

  // Driver
  public static void main(String[] args) {
    StronglyConnectedComponents scc = new StronglyConnectedComponents(5);

    scc.addEdge(1, 0);
    scc.addEdge(0, 2);
    scc.addEdge(2, 1);
    scc.addEdge(0, 3);
    scc.addEdge(3, 4);

    List<List<Integer>> result = scc.getSCCs();

    System.out.println("Strongly Connected Components:");
    for (List<Integer> comp : result) {
      System.out.println(comp);
    }
  }
}


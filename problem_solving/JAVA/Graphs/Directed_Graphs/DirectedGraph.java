import java.util.LinkedList;

public class DirectedGraph {
  private int V;
  private int E;
  private LinkedList<Integer>[] adj;

  public DirectedGraph(int V) {
    this.V = V;
    //Initialise the array
    this.adj = new LinkedList[V];
    this.E = 0;
    for (int i = 0; i < V; i++) {
      this.adj[i] = new LinkedList<Integer>();
    }
  }

  /*
  * Method : to add edge between two vertices
  * @params : int v, int w
  */
  public void addEdge(int v, int w) {
    this.adj[v].add(w);
    this.E++;
  }

  public DirectedGraph reverse(DirectedGraph g) {
    return null;
  }
  
  public int V() {
    return this.V;
  }

  public int E() {
    return this.E;
  }

  public Iterable<Integer> adj(int v) {
    return this.adj[v];
  }

}

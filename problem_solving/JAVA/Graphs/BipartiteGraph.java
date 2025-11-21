import java.util.*;

public class BipartiteGraph {

  ArrayList<Integer> subset1, subset2;
  private final int src;
  int vis[];
  int color[];

  BipartiteGraph(Graph g) {
    subset1 = new ArrayList<>();
    subset2 = new ArrayList<>();

    this.src = 1;
    vis = new int[g.vertices() + 1];
    color = new int[g.vertices() + 1];

    if (isBipartite(g)) {

      for (int i = 1; i < color.length; i++) {
        if (color[i] == 0)
          subset1.add(i);
        else
          subset2.add(i);
      }

      System.out.println("Given graph is bipartite with these 2 subsets");

      System.out.println(subset1);
      System.out.println(subset2);
    } else
      System.out.println("Given graph is not bipartite");
  }

  public boolean isBipartite(Graph g) {
    Queue<Integer> q = new LinkedList<>();
    q.add(src);
    vis[src] = 1;
    color[src] = 0;
    ArrayList<ArrayList<Integer>> adj = g.adj;

    while (q.size() > 0) {
      int node = q.poll();

      for (int neigh : adj.get(node)) {
        if (vis[neigh] == 0) {
          int newColor = (color[node] == 0) ? 1 : 0;

          vis[neigh] = 1;
          color[neigh] = newColor;
          q.add(neigh);
        } else if (vis[neigh] == 1 && color[node] == color[neigh])
          return false;
      }

    }

    return true;
  }

}

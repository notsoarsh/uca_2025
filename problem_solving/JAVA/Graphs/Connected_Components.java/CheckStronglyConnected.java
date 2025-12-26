import java.util.*;
import java.lang.*;
import java.io.*;

public class CheckStronglyConnected 
{
  public static void main (String[] args) throws java.lang.Exception
  {
    // your code goes here
    int n = 4;
    int[][] edges = {
      {0,1},
      {1,2},
      {2,0},
      {0,3}
    };
    boolean isConnected = isStronglyConnected(n, edges);
    System.out.println("Graph is " + (isConnected ? "connected" : "not connected"));
  }

  static boolean isStronglyConnected(int n, int[][] edges) {
    List<List<Integer>> adj = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      adj.add(new ArrayList<>());
    }

    List<List<Integer>> rev = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      rev.add(new ArrayList<>());
    }

    for (int i[] : edges) {
      adj.get(i[0]).add(i[1]);
      rev.get(i[1]).add(i[0]);
    }

    boolean[] vis = new boolean[n];
    dfs(0, vis, adj);

    for (int i = 0; i < n; i++) {
      if (!vis[i]) return false;
    }

    Arrays.fill(vis, false);
    dfs(0, vis, rev);

    for (int i = 0; i < n; i++) {
      if (!vis[i]) return false;
    }

    return true;
  }

  static void dfs(int node, boolean[] vis, List<List<Integer>> adj) {
    vis[node] = true;

    for (int nei : adj.get(node)) {
      if (!vis[nei]) {
        dfs(nei, vis, adj);
      }
    }
  }

}


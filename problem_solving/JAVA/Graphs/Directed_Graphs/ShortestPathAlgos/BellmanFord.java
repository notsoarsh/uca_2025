/*
   Given a matix dist[][] of size N x N, where dist[i][j] represents the weight of the edge from node i to node j. If there is no direct edge the value must be set to large value/null , and the diagnol values are 0 means distance to self is 0. Determine the shortest distance between two nodes.
 */
public class FloydWarshall{
  public static void main(String[] args) {
    Graph g = new Graph();
  }
}

class Graph {
  int N;
  Integer[][] adj;

  Graph(int N) {
    this.N = N;
    adj = new Integer[N][N];

    for (int i = 0; i < N; i++) {
      adj[i][i] = 0;
    }
  }

  void addEdge(int src, int dest, int dist) {
    adj[src][dest] = dist;
  }

  public Integer[] bellmanFord(int start) {
    Integer[] dist = new Integer[N];

    // Start node distance = 0
    dist[start] = 0;

    // -------- STEP 1: RELAX ALL EDGES N-1 TIMES --------
    for (int k = 0; k < N - 1; k++) {

      boolean updated = false;

      for (int u = 0; u < N; u++) {
        for (int v = 0; v < N; v++) {

          if (adj[u][v] == null) continue; // No edge

          int weight = adj[u][v];

          if (dist[u] != null) {  // If src is reachable

            int newDist = dist[u] + weight;

            // If dest is null (unreachable till now) OR shorter path found
            if (dist[v] == null || newDist < dist[v]) {
              dist[v] = newDist;
              updated = true;
            }
          }
        }
      }

      if (!updated) break;
    }

    // -------- STEP 2: NEGATIVE CYCLE CHECK --------
    for (int u = 0; u < N; u++) {
      for (int v = 0; v < N; v++) {

        if (adj[u][v] == null) continue;

        int weight = adj[u][v];

        if (dist[u] != null) {
          int newDist = dist[u] + weight;

          // If still decreasing → negative cycle
          if (dist[v] == null || newDist < dist[v]) {
            throw new RuntimeException("Negative weight cycle detected");
          }
        }
      }
    }

    return dist;
  }
}


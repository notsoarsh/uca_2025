import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.LinkedList;
public class ShortestDistance {
  Integer[][] adj;
  int N;

  ShortestDistance(int N) {
    this.N = N;
    this.adj = new Integer[N][N];

    for (int i = 0; i < N; i++) {
      adj[i][i] = 0;
    }
  }

  class QueueNode {
    public int currNode;
    public int currDistances;

    QueueNode(int currNode, int currDistances) {
      this.currNode = currNode;
      this.currDistances = currDistances;
    }
  }

  public void addEdge(int a, int b, int d) {
    adj[a][b] = d;
  }

  public Integer[] shortestDistanceFromGivenNode(int a) {
    Integer[] shortestDistances = new Integer[N];

    PriorityQueue<QueueNode> q = new PriorityQueue<>((q1, q2) -> {
      return Integer.compare(q1.currDistances, q2.currDistances);
    });
    q.add(new QueueNode(a, 0));

    while (!q.isEmpty()) {
      QueueNode qNode = q.poll();
      int newNode = qNode.currNode;

      if (shortestDistances[newNode] != null && shortestDistances[newNode] <= qNode.currDistances) {
        continue;
      }
      shortestDistances[newNode] = qNode.currDistances;

      for (int i = 0; i < N; i++) {
        if (newNode != i && this.adj[newNode][i] != null) {
          q.add(new QueueNode(i, this.adj[newNode][i] + qNode.currDistances));
        }
      }
    }
    return shortestDistances;
  }

  public static void main(String[] args) {
    ShortestDistance graph = new ShortestDistance(3);
    graph.addEdge(0, 1, 10);
    graph.addEdge(0, 2, 2);
    graph.addEdge(2, 1, 3);

    Integer[] ans = graph.shortestDistanceFromGivenNode(0);
    System.out.println(Arrays.toString(ans));


  }
}

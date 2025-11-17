import java.lang.reflect.Array;
import java.util.ArrayList;

public class ConnectedComponents {
  public int totalcomponent = 0;
  int idOfAllVertices[];
  public int vis[];


  ConnectedComponents(Graph g){
    int v = g.vertices();
    idOfAllVertices = new int[v+1];
    vis = new int[v+1];

    ArrayList<ArrayList<Integer>> adj = g.adj;


    for(int i = 1; i <= v; i++){
      if(vis[i] == 0){
        dfs(adj,i,totalcomponent);
        totalcomponent++;
      }

    }

  }

  public void dfs(ArrayList<ArrayList<Integer>> adj, int node, int compo){
    vis[node] = 1;
    idOfAllVertices[node] = compo;

    for(int neigh : adj.get(node)){
      if(vis[neigh] == 0){
        dfs(adj,neigh,compo);
      }
    }
  }

  public boolean connected(int v, int w){
    return this.idOfAllVertices[v] == idOfAllVertices[w];
  }

  public int count(){
    return this.totalcomponent;
  }

  public int id(int v){
    return this.idOfAllVertices[v];
  }

}{

}

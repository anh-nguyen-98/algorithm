import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Graph {
    public List<List<Integer>> graph;
    
    /**
     * n: number of vertices
     */
    public Graph(int n) {
        this.graph = new ArrayList<List<Integer>>(n);
        for (int i = 0; i <n; i++) {
            this.graph.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v) {
        this.graph.get(u).add(v);
        // this.graph.get(v).add(u);
    }

    public String toString(){
        return this.graph.toString();
    }

    public void bfs(int source) {
        boolean[] visited = new boolean[this.graph.size()];
        Queue<Integer> q = new LinkedList<>();
        q.add(source);
        while (!q.isEmpty()) {
            int u = q.remove();
            visited[u] = true;
            for (int v: this.graph.get(u)) {
                if (!visited[v]) {
                    q.add(v);
                }
            }
            System.out.println(u);
        }
    }

    /**
     * DFS using stack
     */
    public void dfs(int source) {
        Stack<Integer> s = new Stack<>();
        boolean[] visited = new boolean[this.graph.size()];
        s.add(source);
        while (!s.isEmpty()) {
            int u = s.pop();
            visited[u] = true;
            System.out.println(u);
            for (int v: this.graph.get(u)) {
                if (!visited[v]) {
                    s.add(v);
                }
          
            }
        }
    }

    public boolean hasRoute(int source, int dest) {
        if (source == dest) {
            return true;
        }
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[this.graph.size()];
        q.add(source);
        while (!q.isEmpty()) {
            int u = q.remove();
            visited[u] = true;
            if (u == dest) {
                return true;
                
            }
            for (int v: this.graph.get(u)) {
                if (!visited[v]) {
                    q.add(v);
                }
            }
        }
    
        return false;
    }


}

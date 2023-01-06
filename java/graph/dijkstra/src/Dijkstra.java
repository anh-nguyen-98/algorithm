import java.util.*;

public class Dijkstra {
    public int[] getShortestDist(int[][] graph, int src){
        // set up min_dist arr contains shortest distance from src to other nodes
        int[] minDistArr = new int[graph.length];
        for (int i = 0; i < minDistArr.length; i++){
            minDistArr[i] =  Integer.MAX_VALUE;
        }
        minDistArr[src] = 0;

        // set up unvisited_node list
        Set<Integer> unvisited = new HashSet<>();
        for (int node = 0; node < graph.length; node++){
            unvisited.add(node);
        }

        // set up visited_node_arr
        boolean[] visitStatus = new boolean[graph.length];

        while (!unvisited.isEmpty()){
            // visits min_dist_node
            int mDN = minDistNode(minDistArr, visitStatus);

            // updates min_dist of its neighbors
            for (int v = 0; v < graph.length; v++){
                if (graph[mDN][v] > 0 && visitStatus[v] == false){
                    int newMinDist = minDistArr[mDN] + graph[mDN][v];
                    if (newMinDist < minDistArr[v]){
                        minDistArr[v] = newMinDist;
                    }

                }

            }
            unvisited.remove(mDN);
            visitStatus[mDN] = true;

        }
        return minDistArr;
    }

    private int minDistNode(int[] minDistArr, boolean[] visitStatus){
        if (minDistArr.length < 1){
            return -1;
        }
        int mDN = -1;
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < minDistArr.length; i++){
            if (visitStatus[i] == false && minDistArr[i] < minDist){
                mDN = i;
                minDist = minDistArr[i];
            }
        }
        return mDN;
    }
}

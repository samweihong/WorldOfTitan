import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class BestPathToKillTitan {

    public static class Edge{

        int from, to;

        public Edge(int from, int to){
            this.from = from;
            this.to = to;
        }

    }

    private static int n;
    private static int[] prev;
    private static List<List<Edge>> graph;

    public BestPathToKillTitan(List<List<Edge>> graph){
        if(graph == null) throw new IllegalArgumentException("Provided graph is null");
        n = graph.size();
        this.graph = graph;
    }

    public static List<Integer> reconstructPath(int start, int end) {
        bfs(start);
        List<Integer> path = new ArrayList<>();
        for (Integer at = end; at != null; at = prev[at]) path.add(at);
        Collections.reverse(path);
        if (path.get(0) == start) return path;
        path.clear();
        return path;
    }

    private static void bfs(int start) {
        prev = new int[n];
        boolean[] visited = new boolean[n];
        Deque<Integer> queue = new ArrayDeque<>(n);

        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            List<Edge> edges = graph.get(node);

            for (Edge edge : edges) {
                if (!visited[edge.to]) {
                    visited[edge.to] = true;
                    prev[edge.to] = node;
                    queue.offer(edge.to);
                }
            }
        }
    }

}

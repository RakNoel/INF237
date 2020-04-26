import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MinFlow {

    public static int minCutMaxFlow(int start, int end, int[][] resid) {
        int maxFlow = 0;
        var parentGraph = findPath(start, end, resid);
        while (parentGraph != null) {
            int previousNode;
            int currFlow = Integer.MAX_VALUE;
            for (int currentNode = end; currentNode != start; currentNode = parentGraph[currentNode]) {
                previousNode = parentGraph[currentNode];
                currFlow = Math.min(currFlow, resid[previousNode][currentNode]);
            }
            for (int v = end; v != start; v = parentGraph[v]) {
                previousNode = parentGraph[v];
                resid[v][previousNode] += currFlow;
                resid[previousNode][v] -= currFlow;
            }

            maxFlow += currFlow;
            parentGraph = findPath(start, end, resid);
        }

        return maxFlow;
    }

    public static int[] getMaxVertexCover(int graph[][]) {
        int[] connectedTo = new int[graph.length];
        Arrays.fill(connectedTo, -1);

        for (int node = 0; node < graph.length; node++) {
            boolean[] visited = new boolean[graph.length];
            connectNode(node, visited, connectedTo, graph);
        }

        return connectedTo;
    }

    private static int[] findPath(int from, int to, int[][] resid) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[resid.length];
        q.add(from);
        int[] parentList = new int[to + 1];
        parentList[from] = -1;

        while (!q.isEmpty()) {
            var working = q.poll();

            if (working == to) return parentList;
            if (visited[working]) continue;

            visited[working] = true;
            for (int i = 0; i < resid[0].length; i++) {
                if (!visited[i] && resid[working][i] > 0) {
                    parentList[i] = working;
                    q.add(i);
                }
            }
        }

        return null;
    }

    private static int connectNode(int node, boolean visited[], int connectedTo[], int graph[][]) {
        for (int i = 0; i < graph.length; i++)
            if (!visited[i] && graph[node][i] > 0) {
                visited[i] = true;
                if (connectedTo[i] < 0 || connectNode(connectedTo[i], visited, connectedTo, graph) > -1) {
                    connectedTo[i] = node;
                    return node;
                }
            }
        return -1;
    }
}

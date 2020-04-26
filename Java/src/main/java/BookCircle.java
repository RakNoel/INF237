import java.util.Arrays;
import java.util.HashMap;

public class BookCircle {
    public static int[][] graph;
    public static int n, m;

    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);
        n = kattio.getInt();
        m = kattio.getInt();
        var V = n + m;

        HashMap<String, Integer> bookReadBy = new HashMap<>();
        graph = new int[V][V];

        //All boys read
        for (int i = 0; i < n; i++) {
            kattio.getWord();
            int numBooks = kattio.getInt();
            for (int b = 0; b < numBooks; b++) bookReadBy.put(kattio.getWord(), i);
        }

        //All girls match
        for (int i = n; i < V; i++) {
            kattio.getWord();
            int numBooks = kattio.getInt();
            for (int b = 0; b < numBooks; b++) {
                var boy = bookReadBy.get(kattio.getWord());
                graph[boy][i] = 1;
            }
        }

        //System.out.println(MinFlow.bipartite(0, n + m + 1, resid));
        System.out.println(Arrays.stream(MinFlow.getMaxVertexCover(graph))
                .filter(x -> x > -1)
                .toArray().length);
    }
}
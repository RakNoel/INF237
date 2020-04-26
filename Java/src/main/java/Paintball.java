import java.util.Arrays;

public class Paintball {
    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);

        int n = kattio.getInt(), m = kattio.getInt();

        //i and j can hit each other
        int V = (n * 2);
        int[][] graph = new int[V][V];

        for (int i = 0; i < m; i++) {
            int a1 = kattio.getInt() - 1;
            int b1 = kattio.getInt() - 1;
            int a2 = a1 + n;
            int b2 = b1 + n;

            graph[a1][b2] = 1;
            graph[b1][a2] = 1;
        }

        var resArr = MinFlow.getMaxVertexCover(graph);
        var res = Arrays.stream(resArr)
                .filter(x -> x > -1)
                .toArray()
                .length;

        if (res == n) {
            for (int i = n; i < resArr.length; i++)
                System.out.println(resArr[i] + 1);
        } else {
            System.out.println("Impossible");
        }

    }
}

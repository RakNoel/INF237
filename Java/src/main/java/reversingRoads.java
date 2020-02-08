import java.util.ArrayList;
import java.util.BitSet;

public class reversingRoads {
    private static final String CORRECT = "valid";
    private static final String FAULTY = "invalid";
    private static Kattio kattio = new Kattio(System.in, System.out);
    static int lvl = 1;

    public static void main(String[] args) {

        int res = 0;

        main:
        while (kattio.hasMoreTokens()) {
            int m = kattio.getInt();
            int n = kattio.getInt();
            String[] input = new String[n];
            ArrayList<String> solutions = new ArrayList<>();
            AdjacencyMatrix g = new AdjacencyMatrix(m, m);

            //Read input
            for (int i = 0; i < n; i++) {
                int a = kattio.getInt();
                int b = kattio.getInt();
                g.connect(a, b);
                input[i] = a + " " + b;
            }

            if (m == 1) {
                putResult(CORRECT, ++res);
                continue;
            }
            //Visited now shows unreachable nodes
            if (n < m) {
                putResult(FAULTY, ++res);
                continue;
            }

            //Actual algorithm
            int[] pre = new int[m];
            int[] post = new int[m];
            BitSet visited = new BitSet();
            lvl = 1;
            prePostValue(pre, post, visited, g, 0);

            //Test first solution
            if (visited.cardinality() == m) {
                visited = new BitSet();
                reverseTest(visited, g, 0);
                if (visited.cardinality() == m) {
                    putResult(CORRECT, ++res);
                    continue;
                }
            }

            //Find first not visited
            visited = new BitSet();
            lvl = 1;
            prePostValue(pre, post, visited, g, 0);

            while (visited.cardinality() != m) {
                int comp2Start = -1;
                for (int i = 0; i < m && comp2Start < 0; i++)
                    if (!visited.get(i))
                        comp2Start = i;

                lvl = 1;
                if (comp2Start >= 0)
                    prePostValue(pre, post, visited, g, comp2Start);
            }


            //find max post
            int maxPost = 0;
            for (int i = 1; i < post.length; i++) {
                if (post[i] > post[maxPost])
                    maxPost = i;
            }

            for (int i = 0; i < m; i++)
                for (var nbr : g.getNeighbours(i)) {
                    g.flipEdge(i, nbr);
                    visited = new BitSet();
                    lvl = 1;
                    prePostValue(pre, post, visited, g, maxPost);

                    //Test first solution
                    if (visited.cardinality() == m) {
                        visited = new BitSet();
                        reverseTest(visited, g, maxPost);
                        if (visited.cardinality() == m) {
                            solutions.add(i + " " + nbr);
                            //putResult(i + " " + nbr, ++res);
                            //continue main;
                        }
                    }
                    g.flipEdge(nbr, i);
                }

            if (solutions.isEmpty())
                putResult(FAULTY, ++res);
            else {
                for (var s : input)
                    if (solutions.contains(s)) {
                        putResult(s, ++res);
                        continue main;
                    }
            }

        }
    }

    private static void reverseTest(BitSet visited, AdjacencyMatrix g, int S) {
        visited.set(S);
        for (var nbr : g.getPathsTo(S))
            if (!visited.get(nbr))
                reverseTest(visited, g, nbr);
    }

    private static void prePostValue(int[] pre, int[] post, BitSet visited, AdjacencyMatrix g, int S) {
        pre[S] = lvl++;
        visited.set(S);
        for (var nbr : g.getNeighbours(S))
            if (!visited.get(nbr))
                prePostValue(pre, post, visited, g, nbr);

        post[S] = lvl++;
    }

    private static void putResult(String res, int cnt) {
        System.out.printf("Case %d: %s %n", cnt, res);
        //kattio.printf("Case %d: %s", cnt, res);
    }
}

/**
 * Effectively a memory heavy O(m * m) solution but due to
 * use of BitSet we only store positions that are actually set
 * giving a size of O(n).
 */
class AdjacencyMatrix {
    private BitSet matrix;
    private int width, height;

    public AdjacencyMatrix(int width, int height) {
        this.width = width;
        this.height = height;
        matrix = new BitSet(width * height);
    }

    public ArrayList<Integer> getNeighbours(int a) {
        ArrayList<Integer> tmp = new ArrayList<>();
        for (int i = 0; i < width; i++)
            if (this.isConnected(a, i))
                tmp.add(i);

        return tmp;
    }

    public ArrayList<Integer> getPathsTo(int b) {
        ArrayList<Integer> tmp = new ArrayList<>();
        for (int i = 0; i < height; i++)
            if (this.cameFrom(b, i))
                tmp.add(i);

        return tmp;
    }

    public ArrayList<Integer> edges(int a) {
        ArrayList<Integer> tmp = new ArrayList<>();
        for (int i = 0; i < height; i++)
            if (this.cameFrom(a, i) || this.isConnected(a, i))
                tmp.add(i);

        return tmp;
    }

    public boolean isConnected(int a, int b) {
        return matrix.get(convert2dTo1D(a, b));
    }

    public boolean cameFrom(int a, int b) {
        return matrix.get(convert2dTo1D(b, a));
    }

    public void connect(int a, int b) {
        matrix.set(convert2dTo1D(a, b));
    }

    public void flipEdge(int a, int b) {
        if (!this.isConnected(a, b)) throw new RuntimeException();
        matrix.set(convert2dTo1D(a, b), false);
        matrix.set(convert2dTo1D(b, a), true);
    }

    private int convert2dTo1D(int x, int y) {
        var form = (y * this.width) + x;
        if (form >= height * width) throw new RuntimeException();
        return form;
    }
}
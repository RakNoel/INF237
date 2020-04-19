public class RebelPortals {
    public static Point[] points;
    public static double[][] distance;
    public static double[][][] state;
    public static int n;

    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);

        //Read number planets
        n = kattio.getInt();

        //Read all planets to list
        points = new Point[n];
        for (int i = 0; i < n; i++) points[i] = new Point(kattio.getInt(), kattio.getInt(), kattio.getInt());

        //Calculate all planet distances (fully connected graph)
        distance = new double[n][n];
        for (int i = 0; i < n; i++)
            for (int j = i; j < n; j++) {
                distance[i][j] = points[i].distance(points[j]);
                distance[j][i] = distance[i][j];
            }

        state = new double[n][1 << n][2];
        double result = backtrack(0, ((1 << n) - 1) ^ 1, (1 << n) - 1);
        System.out.println(result);
    }

    /**
     * Bottom up recursive branching backtracking algorithm solving the "Traveling salesperson" problem with
     * possible portals.
     *
     * @param node        The current working node not part of subset. Start at 0.
     * @param subset      The subset of nodes yet to visit, represented with a binary array of length n.
     *                    Start at ((1 << n) - 1) ^ 1 for full cover.
     * @param openPortals Boolean array of portals which are still open, represented with a boolean
     *                    array of length n. Start at (1 << n) - 1) for full cover.
     * @return Returns collected minimum travel distance.
     */
    public static double backtrack(int node, int subset, int openPortals) {
        boolean thisHasOpenPortal = ((openPortals & (1 << node)) > 0);

        //Test memory
        if (state[node][subset][thisHasOpenPortal ? 1 : 0] != 0)
            return state[node][subset][thisHasOpenPortal ? 1 : 0];

        //Bottom case. Should return to start
        if (subset == 0)
            if (thisHasOpenPortal && ((openPortals & 1) > 0)) return 0;
            else return distance[node][0];

        //Recursive branching backtracking
        double min = Double.MAX_VALUE;
        for (int i = 0; i < n; i++)
            if ((subset & (1 << i)) > 0) {
                if (thisHasOpenPortal && ((openPortals & (1 << i)) > 0)) {
                    int openPortalsUpdated = (openPortals ^ (1 << node) ^ (1 << i));
                    min = Math.min(min, backtrack(i, subset ^ (1 << i), openPortalsUpdated));
                }
                min = Math.min(min, distance[node][i] + backtrack(i, subset ^ (1 << i), openPortals));
            }

        //Memoize
        state[node][subset][thisHasOpenPortal ? 1 : 0] = min;
        return min;
    }
}

class Point {
    int x, y, z;

    public Point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double distance(Point b) {
        return Math.sqrt(Math.pow(b.x - this.x, 2) + Math.pow(b.y - this.y, 2) + Math.pow(b.z - this.z, 2));
    }
}
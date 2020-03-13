public class ReachableRoads {
    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);

        int n = kattio.getInt();

        for (int q = 0; q < n; q++) {
            int m = kattio.getInt();
            UnionFind unionFind = new UnionFind(m);
            int r = kattio.getInt();
            for (int line = 0; line < r; line++) {
                int a = kattio.getInt();
                int b = kattio.getInt();
                unionFind.union(a, b);
            }
            System.out.println(unionFind.getTotal() - 1);
        }
    }
}
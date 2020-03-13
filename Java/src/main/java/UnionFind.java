public class UnionFind {
    private int[] pointer;
    private int[] size;
    private long total;

    public UnionFind(int size) {
        this.pointer = new int[size];
        this.size = new int[size];
        this.total = size;
        for (int i = 0; i < pointer.length; i++)
            pointer[i] = i;
    }

    public int find(int a) {
        while (pointer[a] != a) a = pointer[a];
        return a;
    }

    public void union(int a, int b) {
        var topA = find(a);
        var topB = find(b);
        if (topA == topB) return;

        this.total--;

        if (size[topA] > size[topB]) {
            pointer[topB] = pointer[topA];
            size[topA] += size[topB];
        } else {
            pointer[topA] = pointer[topB];
            size[topB] += size[topA];
        }
    }

    public boolean isConnected(int a, int b) {
        return find(a) == find(b);
    }

    public long getTotal() {
        return total;
    }
}
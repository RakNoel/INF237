import java.util.HashSet;
import java.util.PriorityQueue;

public class Landline {
    public static void main(String[] args) {
        Kattio reader = new Kattio(System.in, System.out);
        int numHouses = reader.getInt();
        int possibleLines = reader.getInt();
        int numBadHouses = reader.getInt();

        long totalCost = 0;

        HashSet<Integer> badHouses = new HashSet<>(numBadHouses);
        for (int i = 0; i < numBadHouses; i++)
            badHouses.add(reader.getInt() - 1);

        PriorityQueue<Line> goodLines = new PriorityQueue<>();
        PriorityQueue<Line> badLines = new PriorityQueue<>();
        PriorityQueue<Line> dumpLines = new PriorityQueue<>();
        for (int i = 0; i < possibleLines; i++) {
            var tmp = new Line();
            tmp.from = reader.getInt() - 1;
            tmp.to = reader.getInt() - 1;
            tmp.cost = reader.getInt();

            if (badHouses.contains(tmp.from) && badHouses.contains(tmp.to))
                dumpLines.add(tmp);
            else if (badHouses.contains(tmp.from) || badHouses.contains(tmp.to))
                badLines.add(tmp);
            else
                goodLines.add(tmp);
        }

        //Edge case of 2 single bad houses
        if (numHouses == 2 && numBadHouses == 2 && possibleLines > 0) {
            if (!dumpLines.isEmpty()) System.out.println(dumpLines.poll().cost);
            else System.out.println("impossible");
            return;
        }

        UnionFind uf = new UnionFind(numHouses);

        while (!goodLines.isEmpty() && (uf.getTotal() - numBadHouses > 1)) {
            var nexLine = goodLines.poll();

            //Continue if loop
            if (uf.isConnected(nexLine.from, nexLine.to)) continue;

            totalCost += nexLine.cost;
            uf.union(nexLine.from, nexLine.to);
        }

        if (uf.getTotal() - numBadHouses > 1) {
            System.out.println("impossible");
            return;
        }

        while (!badLines.isEmpty() && (uf.getTotal() > 1)) {
            var nexLine = badLines.poll();

            //Continue if loop
            if (uf.isConnected(nexLine.from, nexLine.to)) continue;

            totalCost += nexLine.cost;
            uf.union(nexLine.from, nexLine.to);
        }

        if (uf.getTotal() > 1)
            System.out.println("impossible");
        else
            System.out.println(totalCost);

    }
}

class Line implements Comparable<Line> {
    int from, to, cost;

    @Override
    public int compareTo(Line l) {
        return this.cost - l.cost;
    }
}


class UnionFind {
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
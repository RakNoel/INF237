import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Landline {
    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);
        int numHouses = kattio.getInt();
        int possibleLines = kattio.getInt();
        int numBbadHouses = kattio.getInt();

        HashSet<Integer> badHouses = new HashSet<>(numBbadHouses);
        for (int i = 0; i < numBbadHouses; i++)
            badHouses.add(kattio.getInt());

        PriorityQueue<Line> lines = new PriorityQueue<>();
        for (int i = 0; i < possibleLines; i++){
            var tmp = new Line();
            tmp.from = kattio.getInt();
            tmp.to = kattio.getInt();
            tmp.cost = kattio.getInt();
            tmp.bad = (badHouses.contains(tmp.from) || badHouses.contains(tmp.to));
            if (badHouses.contains(tmp.from) && badHouses.contains(tmp.to)) continue;
            lines.add(tmp);

        }


    }

    //TODO: Implement kruskal with quickUnion
    //TODO: Filter bad-houses
    //TODO: Add bad houses after if possible
}

class Line implements Comparable<Line>{
    int from, to, cost;
    boolean bad;

    @Override
    public int compareTo(Line l) {
        return this.cost - l.cost;
    }
}
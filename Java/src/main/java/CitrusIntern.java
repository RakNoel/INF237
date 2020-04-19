import java.util.HashSet;
import java.util.Iterator;

public class CitrusIntern {
    public static Person[] people;

    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);

        int n = kattio.getInt();
        people = new Person[n];
        for (int i = 0; i < n; i++) {
            people[i] = new Person(kattio.getInt());
            int setSize = kattio.getInt();
            for (int j = 0; j < setSize; j++)
                people[i].add(kattio.getInt());
        }

        var res = minDominatingSet(findTreeTop());
        System.out.println(Math.min(res.in, res.outDown));
    }

    public static int findTreeTop(){
        int[] visited = new int[people.length];
        int maxNum = 0;
        for (int i = 0; i < people.length; i++)
            if (visited[i] == 0) if (topologicalSortHelper(i, visited) > visited[maxNum]) maxNum = i;
        return maxNum;
    }

    public static int topologicalSortHelper(int i, int[] visited){
        if (visited[i] != 0) return visited[i];
        if (people[i].size() < 1) return 1;
        for (var per : people[i]) visited[i] += topologicalSortHelper(per, visited);
        return visited[i];
    }

    public static ClusterObject minDominatingSet(int node){
        //Memoize
        if (people[node].me != null) return people[node].me;

        //Bottom case
        if (people[node].size() == 0) return new ClusterObject(people[node].cost, 0, Integer.MAX_VALUE);

        //Produce minimum dominating set
        long bestIn = people[node].cost, bestOutUp = 0, bestOutDown = 0, bestDelta = Integer.MAX_VALUE;
        boolean addDelta = true;
        for (int i : people[node]) {
            var tmp = minDominatingSet(i);
            bestIn += tmp.outUp; //Can't have two neighbors
            bestOutUp += Math.min(tmp.in, tmp.outDown);
            if (tmp.in <= tmp.outDown) addDelta = false;
            else bestDelta = Math.min(bestDelta, Math.max(tmp.in - tmp.outDown, 0));
        }
        bestOutDown = (addDelta) ? bestOutUp + bestDelta : bestOutUp;

        var res = new ClusterObject(bestIn, bestOutUp, bestOutDown);
        people[node].me = res;
        return res;
    }
}

class Person implements Iterable<Integer> {
    int cost;
    HashSet<Integer> subordinates;
    ClusterObject me;

    public Person(int cost) {
        this.cost = cost;
        this.subordinates = new HashSet<>();
        this.me = null;
    }

    void add(int x) {
        this.subordinates.add(x);
    }

    int size() {
        return this.subordinates.size();
    }

    @Override
    public Iterator<Integer> iterator() {
        return this.subordinates.iterator();
    }
}

class ClusterObject {
    long in, outUp, outDown;

    public ClusterObject(long in, long outUp, long outDown) {
        this.in = in;
        this.outUp = outUp;
        this.outDown = outDown;
    }
}
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ClosestPair2Test {
    public static Random rnd;
    public static Set<Double> uniques;
    public static ArrayList<dPoint> P;

    @BeforeAll
    public static void setup() {
        rnd = new Random(666);

        uniques = new HashSet<>();
        var elements = rnd.nextInt(1000000);
        for (int i = 0; i < elements; i++) uniques.add(rnd.nextDouble());

        P = new ArrayList<>();
        var r = new ArrayList<>(uniques);
        for (int i = 0; i < r.size() - 1; i++) P.add(new dPoint(r.get(i), r.get(++i)));
    }

    @Test
    public void GeneratedTest() {
        dPoint[] f = new dPoint[P.size()];
        for (int i = 0; i < f.length; i++) f[i] = P.get(i);

        var start = System.nanoTime();
        var res = ClosestPair2.getClosestPair(f);

        System.out.println(res);
        var stop = System.nanoTime();
        System.out.println("Time: " + (stop - start) / 1000000 + "ms");
    }
}

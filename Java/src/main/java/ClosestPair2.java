import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair2 {
    public static dPoint[] gP;

    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);

        for (int testcase = kattio.getInt(); testcase != 0; testcase = kattio.getInt()) {
            dPoint[] P = new dPoint[testcase];
            for (int i = 0; i < testcase; i++) P[i] = new dPoint(kattio.getDouble(), kattio.getDouble());
            System.out.println(getClosestPair(P));
        }
    }

    public static Tuple<dPoint> getClosestPair(dPoint[] P) {
        gP = P;
        dPoint[] Px = P.clone();
        dPoint[] Py = P.clone();
        Arrays.sort(Px, Comparator.comparingDouble(a -> a.x));
        Arrays.sort(Py, Comparator.comparingDouble(a -> a.y));

        return getClosestPairRec(Px, Py);
    }

    private static Tuple<dPoint> getClosestPairRec(dPoint[] Px, dPoint[] Py) {
        if (Px.length <= 3) {
            Tuple<dPoint> min = null;
            double minvalue = Double.MAX_VALUE;
            for (int i = 0; i < Px.length; i++) {
                for (int j = i + 1; j < Px.length; j++)
                    if (Px[i].distance(Px[j]) < minvalue){
                        minvalue = Px[i].distance(Px[j]);
                        min = new Tuple<>(Px[i], Px[j]);
                    }
            }
            return min;
        }

        int centerPoint = Px.length / 2;
        dPoint[] Qx = Arrays.copyOfRange(Px, 0, centerPoint);
        dPoint[] Rx = Arrays.copyOfRange(Px, centerPoint, Px.length);
        dPoint[] Qy = Arrays.copyOfRange(Py, 0, centerPoint);
        dPoint[] Ry = Arrays.copyOfRange(Py, centerPoint, Py.length);

        var minQ = getClosestPairRec(Qx, Qy);
        var minR = getClosestPairRec(Rx, Ry);

        var delta = Math.min(minQ.a.distance(minQ.b), minR.a.distance(minR.b));
        var L = Qx[Qx.length - 1];

        //dPoint[] S = Arrays.stream(gP).takeWhile(x -> L.distance(x) <= delta).toArray(dPoint[]::new);
        ArrayList<dPoint> S = new ArrayList<>();
        for (int i = 0; i < Px.length; i++) if (L.distance(Px[i]) <= delta) S.add(Px[i]);
        for (int i = 0; i < Py.length; i++) if (L.distance(Py[i]) <= delta) S.add(Py[i]);

        Tuple<dPoint> minS = null;
        double minvalue = Double.MAX_VALUE;
        for (int i = 0; i < S.size(); i++) {
            for (int j = i + 1; j <= i + 15 && j < S.size(); j++)
                if (S.get(i).distance(S.get(j)) < minvalue){
                    minvalue = S.get(i).distance(S.get(j));
                    minS = new Tuple<>(S.get(i), S.get(j));
                }
        }

        if (minS != null && minS.a.distance(minS.b) < delta)
            return minS;
        else if (minQ.a.distance(minQ.b) < minR.a.distance(minR.b))
            return minQ;
        else return minR;
    }
}


class dPoint {
    double x, y;

    public dPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(dPoint b) {
        return Math.sqrt(Math.pow(b.x - this.x, 2) + Math.pow(b.y - this.y, 2));
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}

class Tuple<T> {
    public final T a, b;

    public Tuple(T a, T b){
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return a.toString() + " " + b.toString();
    }
}

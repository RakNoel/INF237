import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class RobotProtection {
    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);

        while (true) {
            var posts = kattio.getInt();
            if (posts == 0) return;

            ArrayList<Point> postlist = new ArrayList<>();
            ArrayList<Point> postlistNeg = new ArrayList<>();

            for (int i = 0; i < posts; i++) postlist.add(new Point(kattio.getInt(), kattio.getInt()));

            if (posts < 3) {
                System.out.println("0.0");
                continue;
            }

            postlist.sort(Point::compareTo);

            for (var p : postlist) postlistNeg.add(new Point(-p.x, -p.y));
            Collections.reverse(postlistNeg);

            Stack<Point> stkTop = new Stack<>();
            stkTop.push(postlist.get(0));
            stkTop.push(postlist.get(1));

            //Top part
            for (int nextPoint = 2; nextPoint < postlist.size(); nextPoint++) {
                Point b = stkTop.pop(), a = stkTop.pop(), c = postlist.get(nextPoint);

                while (new Vector(a, b).pointIsLeftOrOn(c) && stkTop.size() > 0) {
                    b = a;
                    a = stkTop.pop();
                }

                stkTop.push(a);
                if (!new Vector(a, b).pointIsLeftOrOn(c)) stkTop.push(b);
                stkTop.push(c);
            }
            stkTop.pop();

            Stack<Point> stkBottom = new Stack<>();
            stkBottom.push(postlistNeg.get(0));
            stkBottom.push(postlistNeg.get(1));

            //Bottom part
            for (int nextPoint = 2; nextPoint < postlistNeg.size(); nextPoint++) {
                Point b = stkBottom.pop(), a = stkBottom.pop(), c = postlistNeg.get(nextPoint);

                while (new Vector(a, b).pointIsLeftOrOn(c) && stkBottom.size() > 0){
                    b = a;
                    a = stkBottom.pop();
                }

                stkBottom.push(a);
                if (!new Vector(a, b).pointIsLeftOrOn(c)) stkBottom.push(b);
                stkBottom.push(c);
            }
            stkBottom.pop();

            ArrayList<Point> hull = new ArrayList<>(stkTop);
            stkBottom.forEach(x -> hull.add(x.negate()));

            System.out.println(calculateArea(hull));
        }
    }

    static double calculateArea(ArrayList<Point> points) {
        double area = 0.0;
        int j = points.size() - 1;

        for (int i = 0; i < points.size(); i++) {
            area += (points.get(j).x + points.get(i).x) * (points.get(j).y - points.get(i).y);
            j = i;
        }

        return Math.abs(area / 2);
    }

    private static class Point implements Comparable<Point> {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Point negate() {
            this.x = -this.x;
            this.y = -this.y;
            return this;
        }

        @Override
        public int compareTo(Point b) {
            if (this.x == b.x) return b.y - this.y;
            return this.x - b.x;
        }

        @Override
        public String toString() {
            return x + " " + y;
        }
    }

    private static class Vector {
        Point a, b;

        Vector(Point a, Point b) {
            this.a = a;
            this.b = b;
        }

        boolean pointIsLeftOrOn(Point test) {
            return (b.x - a.x) * (test.y - a.y) >= (b.y - a.y) * (test.x - a.x);
        }
    }
}

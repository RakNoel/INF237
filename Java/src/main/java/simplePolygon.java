import java.util.Arrays;
import java.util.Stack;

public class simplePolygon {
    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);

        for (int itt = kattio.getInt(); itt > 0; itt--) {
            int n = kattio.getInt();
            Point[] points = new Point[n];
            for (int i = 0; i < n; i++) points[i] = new Point(kattio.getInt(), kattio.getInt(), i);

            Arrays.sort(points);

            Stack<Point> stkTop = new Stack<>();
            stkTop.push(points[0]);
            stkTop.push(points[1]);

            //Find hull
            for (int nextPoint = 2; nextPoint < n; nextPoint++) {
                Point b = stkTop.pop(), a = stkTop.pop(), c = points[nextPoint];
                b.isListed = false;
                a.isListed = false;

                while (new Vector(a, b).pointIsLeftOrOn(c) && stkTop.size() > 0) {
                    b = a;
                    a = stkTop.pop();
                    a.isListed = false;
                }

                stkTop.push(a);
                a.isListed = true;
                if (!new Vector(a, b).pointIsLeftOrOn(c)) {
                    stkTop.push(b);
                    b.isListed = true;
                }
                stkTop.push(c);
                c.isListed = true;
            }

            //Track backwards and fill rest
            for (int i = points.length - 1; i >= 0; i--)
                if (!points[i].isListed) stkTop.push(points[i]);

            //Print results
            while (!stkTop.isEmpty()) System.out.print(stkTop.pop());
            System.out.println();
        }
    }

    private static class Point implements Comparable<Point> {
        int x;
        int y;
        int position;
        boolean isListed;

        public Point(int x, int y, int position) {
            this.x = x;
            this.y = y;
            this.position = position;
        }

        @Override
        public int compareTo(Point b) {
            if (this.x == b.x) return b.y - this.y;
            return this.x - b.x;
        }

        @Override
        public String toString() {
            return position + " ";
        }
    }

    private static class Vector {
        Point a, b;

        Vector(Point a, Point b) {
            this.a = a;
            this.b = b;
        }

        boolean pointIsLeftOrOn(Point test) {
            return (b.x - a.x) * (test.y - a.y) > (b.y - a.y) * (test.x - a.x);
        }
    }
}

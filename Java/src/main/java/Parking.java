import java.util.Arrays;

import static java.lang.Math.abs;

public class Parking {
    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);
        int tst = kattio.getInt();
        for (int testcase = 0; testcase < tst; testcase++){
            int shops = kattio.getInt();
            int[] placements = new int[shops];
            for (int i = 0; i < shops; i++) placements[i] = kattio.getInt();
            Arrays.sort(placements);
            int min = placements[0];
            int max = placements[placements.length - 1];

            long best = Integer.MAX_VALUE;
            for (int i = 0; i <= 99; i++){
                long dist = abs(max - i) + abs(min - i);
                dist *= 2;
                if (best > dist) best = dist;
            }

            System.out.println(best);
        }
    }
}

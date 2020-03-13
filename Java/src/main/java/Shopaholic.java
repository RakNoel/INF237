import java.util.Arrays;
import java.util.Collections;

public class Shopaholic {
    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);
        int n = kattio.getInt();
        Integer[] p = new Integer[n];
        for (int i = 0; i < n; i++) p[i] = kattio.getInt();
        Arrays.sort(p, Collections.reverseOrder());

        if (n < 3) System.out.println(0);

        long sumdiscount = 0;
        for (int i = 2; i < n; i += 3) sumdiscount += p[i];

        System.out.println(sumdiscount);
    }
}

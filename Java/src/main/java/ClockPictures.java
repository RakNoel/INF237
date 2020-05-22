import java.util.Arrays;

public class ClockPictures {
    public static final int CLOCK_MAX = 360000;

    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);
        int hands = kattio.getInt();

        int[] c1 = new int[hands];
        int[] c2 = new int[hands];
        int[] c3 = new int[hands * 2];
        int[] c4 = new int[hands];

        for (int i = 0; i < hands * 2; i++)
            if (i < hands) c1[i] = kattio.getInt();
            else c2[i % hands] = kattio.getInt();

        getDifferenceLists(c1, c2, c3, c4);

        boolean res = isSubArray(c3, c4);
        System.out.println(res ? "possible" : "impossible");
    }

    static void getDifferenceLists(int[] c1, int[] c2, int[] c3, int[] c4) {
        Arrays.sort(c1);
        Arrays.sort(c2);
        var prev1 = c1[c1.length - 1];
        var prev2 = c2[c2.length - 1];
        for (int i = 0; i < c1.length; i++) {
            c3[i] = ((c1[i] - prev1) + CLOCK_MAX) % CLOCK_MAX;
            c3[i + c1.length] = c3[i];
            c4[i] = ((c2[i] - prev2) + CLOCK_MAX) % CLOCK_MAX;

            prev1 = c1[i];
            prev2 = c2[i];
        }
    }

    static int[] getPrefixTable(int[] c4) {
        int[] t = new int[c4.length + 1];
        t[0] = -1;
        int i = 1, j = 0;
        for (; i < c4.length; i++, j++) {
            if (c4[j] == c4[i])
                t[i] = t[j];
            else {
                t[i] = j;
                j = t[j];
                while (j >= 0 && c4[i] != c4[j])
                    j = t[j];
            }
        }
        t[i] = j;
        return t;
    }

    static boolean isSubArray(int[] s, int[] w) {
        int[] t = getPrefixTable(w);

        for (int i = 0, j = 0; i < s.length; ) {
            if (s[i] == w[j]) {
                i++;
                j++;
                if (j == w.length) return true;
            } else {
                j = t[j];
                if (j < 0) {
                    i++;
                    j++;
                }
            }
        }

        return false;
    }
}

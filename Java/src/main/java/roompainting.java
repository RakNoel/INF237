import java.util.Arrays;

public class roompainting {
    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);

        int n = kattio.getInt();
        int m = kattio.getInt();

        int[] possible = new int[n];
        int[] wanted = new int[m];

        for (int i = 0; i < n; i++)
            possible[i] = kattio.getInt();
        for (int i = 0; i < m; i++)
            wanted[i] = kattio.getInt();

        Arrays.sort(possible);
        Arrays.sort(wanted);

        int lastTaken = 0;
        long waste = 0;

        for (int i = 0; i < m; i++)
            for (int j = lastTaken; j < n; j++)
                if (wanted[i] <= possible[j]) {
                    waste += possible[j] - wanted[i];
                    lastTaken = j;
                    break;
                }

        System.out.println(waste);
    }
}

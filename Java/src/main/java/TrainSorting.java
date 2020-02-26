import java.util.Arrays;

public class TrainSorting {

    public static int solveProblem(int[] a) {
        if (a.length <= 2) return a.length;

        int[] lds = new int[a.length];
        int[] lis = new int[a.length];
        int res = 1;
        Arrays.fill(lds, 1);
        Arrays.fill(lis, 1);
        for (int i = a.length - 2; i >= 0; i--)
            for (int k = a.length - 1; k > i; k--) {
                if (a[k] < a[i] && lds[i] < lds[k] + 1)
                    lds[i] = lds[k] + 1;
                else if (a[k] > a[i] && lis[i] < lis[k] + 1)
                    lis[i] = lis[k] + 1;

                res = Math.max(res, (lis[i] + lds[i]) - 1);
            }
        return res;
    }

    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);
        int n = kattio.getInt();

        int[] searchArray = new int[n];
        for (int i = 0; i < n; i++) searchArray[i] = kattio.getInt();

        System.out.println(solveProblem(searchArray));
    }
}

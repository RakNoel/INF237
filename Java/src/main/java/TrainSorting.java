import java.util.Arrays;

public class TrainSorting {
    public static int[] LIS(int[] a) {
        int[] dp = new int[a.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < a.length; i++)
            for (int k = 0; k < i; k++) {
                if (a[k] < a[i] && dp[i] < dp[k] + 1)
                    dp[i] = dp[k] + 1;
            }
        return dp;
    }

    public static int[] LDS(int[] a) {
        int[] dp = new int[a.length];
        Arrays.fill(dp, 1);
        for (int i = a.length - 2; i >= 0; i--)
            for (int k = a.length - 1; k > i; k--) {
                if (a[k] < a[i] && dp[i] < dp[k] + 1)
                    dp[i] = dp[k] + 1;
            }
        return dp;
    }

    public static int LBS(int[] a) {
        if (a.length <= 2) return a.length;

        var lis = LIS(a);
        var lds = LDS(a);
        int max = (lis[0] + lds[0] - 1);
        for (int i = 1; i < lis.length; i++) max = Math.max(max, (lis[i] + lds[i]) - 1);
        return max;
    }

    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);
        int n = kattio.getInt();

        int[] searchArray = new int[n];
        for (int i = 0; i < n; i++) searchArray[i] = kattio.getInt();

        System.out.println(LBS(searchArray));
    }
}

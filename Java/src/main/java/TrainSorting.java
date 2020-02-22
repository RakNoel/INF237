public class TrainSorting {
    public static int[] LIS(int[] a) {
        int[] dp = new int[a.length];
        dp[0] = 1;
        for (int i = 0; i < a.length; i++)
            for (int k = 0; k < i; k++) {
                var p = 1;
                if (a[k] < a[i])
                    p = dp[k] + 1;
                dp[i] = Math.max(dp[i], p);
            }
        return dp;
    }

    public static int[] LDS(int[] a) {
        //Flip array a
        for (int i = 0; i < a.length / 2; i++) {
            int temp = a[i];
            a[i] = a[a.length - i - 1];
            a[a.length - i - 1] = temp;
        }
        return LIS(a);
    }

    public static int LBS(int[] a) {
        var lis = LIS(a);
        var lds = LDS(a);
        int max = (lis[0] + lds[0] - 1);
        for (int i = 0; i < lis.length; i++) max = Math.max(max, (lis[i] + lds[i] - 1));
        return max;
    }

    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);
        int n = kattio.getInt();
        if (n <= 3) {
            System.out.println(n);
            return;
        }

        int[] searchArray = new int[n];
        for (int i = 0; i < n; i++) searchArray[i] = kattio.getInt();

        System.out.println(LBS(searchArray));
    }
}

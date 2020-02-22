public class TrainSorting {
    public static int LIS(int arr[]) {
        if (arr.length <= 2) return arr.length;

        int[] T = new int[arr.length];
        int tp = 0;
        int longest = 1;
        T[0] = arr[0];

        for (int i = 1; i < arr.length; i++) {
            var working = arr[i];
            if (working >= T[tp]) {
                T[++tp] = working;
                longest++;
                continue;
            }

            //BinarySearch T
            int minP = 0;
            int maxP = longest;
            int searcher = 0;
            while (minP < maxP){
                searcher = (int) Math.ceil((maxP + minP) / 2);
                if (working < T[searcher]){
                    maxP = searcher - 1;
                } else if (working > T[searcher]) {
                    minP = searcher + 1;
                }
            }
            T[searcher] = working;
        }

        return longest;
    }

    public static int LDS(int arr[]) {
        if (arr.length <= 1) return arr.length;

        int[] T = new int[arr.length];
        int tp = 0;
        int longest = 1;
        T[0] = arr[0];

        for (int i = 1; i < arr.length; i++) {
            var working = arr[i];
            if (working <= T[tp]) {
                T[++tp] = working;
                longest++;
                continue;
            }

            //BinarySearch T
            int minP = 0;
            int maxP = longest;
            int searcher = 0;
            while (minP < maxP){
                searcher = (int) Math.floor((maxP + minP) / 2);
                if (working > T[searcher]){
                    maxP = searcher - 1;
                } else if (working < T[searcher]) {
                    minP = searcher + 1;
                }
            }
            searcher = (int) Math.floor((maxP + minP) / 2);
            if (T[searcher] > working) searcher++;
            T[searcher] = working;
        }

        return longest;
    }

    public static void main(String args[]) {
        Kattio kattio = new Kattio(System.in, System.out);
        int n = kattio.getInt();

        int[] searchArray = new int[n];
        for (int i = 0; i < n; i++)
            searchArray[i] = kattio.getInt();

        System.out.println(Math.max(LIS(searchArray), LDS(searchArray)));
    }
}

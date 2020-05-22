public class Base2Palindrome {
    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);
        int N = kattio.getInt();

        int greatest = -1;
        int n = 1, r = 2, x = 0;
        while (x < N) {
            for (int i = n; i < r && x < N; i++) {
                int p = i >>> 1;
                int leading = Integer.numberOfLeadingZeros(p);
                int revI = Integer.reverse(p);
                revI >>>= leading;
                greatest = (i << (32 - leading)) | revI;
                x++;
            }
            for (int i = n; i < r && x < N; i++) {
                int leading = Integer.numberOfLeadingZeros(i);
                int revI = Integer.reverse(i);
                revI >>>= leading;
                greatest = (i << (32 - leading)) | revI;
                x++;
            }
            n *= 2;
            r *= 2;
        }
        System.out.println(greatest);
    }
}

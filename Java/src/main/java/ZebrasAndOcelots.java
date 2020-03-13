public class ZebrasAndOcelots {
    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);
        int n = kattio.getInt();

        long full = 0;
        long animals = 0;
        for (int i = 0; i < n; i++) {
            full <<= 1;
            full += 1;

            animals <<= 1;
            animals += (kattio.getWord().equals("Z")) ? 1 : 0;
        }

        System.out.println(full - animals);

    }
}

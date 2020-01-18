public class Oddities {
    public static void main(String[] args) {
        Kattio k = new Kattio(System.in, System.out);
        int f = k.getInt();
        for (int i = 0; i < f; i++) {
            var t = k.getInt();
            System.out.printf("%d is %s%n", t, (t%2==0) ? "even" : "odd");
        }
    }
}
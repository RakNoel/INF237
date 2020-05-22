import java.math.BigInteger;

public class Thermostat {
    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);

        int n = kattio.getInt();
        int q = kattio.getInt();

        Converter[] converters = new Converter[n];

        for (int i = 0; i < n; i++) converters[i] = new Converter(kattio.getInt(), kattio.getInt());

        for (int i = 0; i < q; i++)
            System.out.println(
                    solve(converters[kattio.getInt() - 1], converters[kattio.getInt() - 1], kattio.getInt())
            );

    }

    public static String solve(Converter from, Converter to, int value) {
        BigInteger top = BigInteger.valueOf(((value - from.editor) * to.constant) + (to.editor * from.constant));
        BigInteger bot = BigInteger.valueOf(from.constant);
        BigInteger gcd = top.gcd(bot);
        top = top.divide(gcd);
        bot = bot.divide(gcd);
        if (bot.compareTo(BigInteger.valueOf(0)) < 0) {
            bot = bot.negate();
            top = top.negate();
        }

        return top.toString(10) + "/" + bot.toString(10);
    }

    private static class Converter {
        long constant, editor;

        public Converter(int freeze, int boil) {
            editor = freeze;
            constant = (boil - freeze);
        }
    }
}

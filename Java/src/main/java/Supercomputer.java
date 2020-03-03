import java.util.BitSet;

public class Supercomputer {
    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);
        int n = kattio.getInt(), k = kattio.getInt();
        BitSet maybeMagic = new BitSet(n);
        for (int i = 0; i < k; i++)
            if (kattio.getWord().charAt(0) == 'F') maybeMagic.flip(kattio.getInt());
            else System.out.println(maybeMagic.get(kattio.getInt(), kattio.getInt() + 1).cardinality());
    }
}
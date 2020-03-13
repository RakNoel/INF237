import java.util.Arrays;

public class SupercomputerV2 {
    public static void main(String[] args) throws NoSuchMethodException {
        Kattio kattio = new Kattio(System.in, System.out);
        int n = kattio.getInt(), k = kattio.getInt();

        Integer[] r = new Integer[n];
        Arrays.fill(r, 0);
        SegmentTree<Integer> st = new SegmentTree<>(Integer.class, r, 0, (Number number, Number number2) -> (int) number + (int) number2);

        for (int i = 0; i < k; i++)
            if (kattio.getWord().charAt(0) == 'F') {
                var edit = kattio.getInt();
                st.update(edit, (int) st.get(edit) == 0 ? 1 : 0);
            } else System.out.println(st.query(kattio.getInt(), kattio.getInt(), 0));
    }
}

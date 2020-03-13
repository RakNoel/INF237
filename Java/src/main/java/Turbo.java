import java.util.Arrays;

public class Turbo {
    public static void main(String[] args) throws NoSuchMethodException {
        Kattio kattio = new Kattio(System.in, System.out);
        int n = kattio.getInt();

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            var tmp = kattio.getInt() - 1;
            nums[tmp] = i;
        }

        Integer[] r = new Integer[n];
        Arrays.fill(r, 1);
        SegmentTree<Integer> st = new SegmentTree<>(Integer.class, r, 0, (Number x, Number y) -> (int) x + (int) y);

        for (int i = 0, min = 0, max = 0; i < n; i++) {
            int moveTo = i % 2 == 0 ? 0 : n - 1;
            int moveFrom = i % 2 == 0 ? nums[min++] : nums[n - 1 - max++];
            st.update(moveFrom, 0);
            var res = st.query(Math.min(moveTo, moveFrom), Math.max(moveTo, moveFrom), 0);
            System.out.println(res);
        }
    }
}
import java.util.HashMap;

public class bringItOn {
    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);
        HashMap<String, Integer> hm = new HashMap<>();
        for (int tst = kattio.getInt(); tst > 0; tst--) {
            String tmp = kattio.getWord();
            System.out.println(hm.getOrDefault(tmp, 0));
            for (int i = 1; i <= tmp.length(); i++) {
                var substring = tmp.substring(0, i);
                hm.put(substring, hm.getOrDefault(substring, 0) + 1);
            }
        }
    }
}

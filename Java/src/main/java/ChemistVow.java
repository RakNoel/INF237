import java.util.Arrays;
import java.util.HashMap;

public class ChemistVow {
    public static final String[] TableOfDoubleElements = {"ac", "ag", "al", "am", "ar", "as", "at", "au", "ba", "be", "bh", "bi",
            "bk", "br", "ca", "cd", "ce", "cf", "cl", "cm", "cn", "co", "cr", "cs", "cu", "db", "ds", "dy", "er",
            "es", "eu", "fe", "fl", "fm", "fr", "ga", "gd", "ge", "he", "hf", "hg", "ho", "hs", "in", "ir", "kr",
            "la", "li", "lr", "lu", "lv", "md", "mg", "mn", "mo", "mt", "na", "nb", "nd", "ne", "ni", "no", "np",
            "os", "pa", "pb", "pd", "pm", "po", "pr", "pt", "pu", "ra", "rb", "re", "rf", "rg", "rh", "rn", "ru",
            "sb", "sc", "se", "sg", "si", "sm", "sn", "sr", "ta", "tb", "tc", "te", "th", "ti", "tl", "tm", "xe",
            "yb", "zn", "zr"};

    public static final char[] TableOfSingleElements = {'b', 'c', 'f', 'h', 'i', 'k', 'n', 'o', 'p', 's', 'u', 'v', 'w', 'y'};


    static HashMap<Character, Boolean> sta = new HashMap<>();
    static HashMap<String, Boolean> stb = new HashMap<>();

    public static boolean isWritableInTableOfElements(String word) {
        var chars = word.toCharArray();
        boolean[] singles = new boolean[chars.length];
        boolean[] doubles = new boolean[chars.length];
        for (int i = 0, j = 2; i < chars.length; i++, j++) {
            char a = word.charAt(i);

            if (sta.containsKey(a)) singles[i] = sta.get(a);
            else {
                singles[i] = Arrays.binarySearch(TableOfSingleElements, a) >= 0;
                sta.put(a, singles[i]);
            }
            if (j <= chars.length) {
                var x = word.substring(i, j);
                if (stb.containsKey(word.substring(i, j))) doubles[i] = stb.get(x);
                else {
                    doubles[i] = Arrays.binarySearch(TableOfDoubleElements, x) >= 0;
                    stb.put(x, doubles[i]);
                }
            }
        }
        return doubleSearch(singles, doubles, 0, new boolean[chars.length + 1]);
    }

    public static boolean doubleSearch(boolean[] singles, boolean[] doubles, int sp, boolean[] working) {
        if (working[sp]) return false;
        for (int i = sp; i < singles.length; ) {
            working[i] = true;
            if (singles[i] && doubles[i]) {
                return doubleSearch(singles, doubles, i + 1, working) || doubleSearch(singles, doubles, i + 2, working);
            } else if (doubles[i]) {
                i += 2;
            } else if (singles[i]) {
                i += 1;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Kattio katto = new Kattio(System.in);
        int n = katto.getInt();

        for (int i = 0; i < n; i++) {
            var x = katto.getWord();
            var res = isWritableInTableOfElements(x.toLowerCase()) ? "YES" : "NO";
            if (x.length() == 0) res = "NO";
            System.out.println(res);
        }
    }
}

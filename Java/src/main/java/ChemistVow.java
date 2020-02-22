import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class ChemistVow {
    public static final String[] TableOfDoubleElements = {"ac", "ag", "al", "am", "ar", "as", "at", "au", "ba", "be", "bh", "bi",
            "bk", "br", "ca", "cd", "ce", "cf", "cl", "cm", "cn", "co", "cr", "cs", "cu", "db", "ds", "dy", "er",
            "es", "eu", "fe", "fl", "fm", "fr", "ga", "gd", "ge", "he", "hf", "hg", "ho", "hs", "in", "ir", "kr",
            "la", "li", "lr", "lu", "lv", "md", "mg", "mn", "mo", "mt", "na", "nb", "nd", "ne", "ni", "no", "np",
            "os", "pa", "pb", "pd", "pm", "po", "pr", "pt", "pu", "ra", "rb", "re", "rf", "rg", "rh", "rn", "ru",
            "sb", "sc", "se", "sg", "si", "sm", "sn", "sr", "ta", "tb", "tc", "te", "th", "ti", "tl", "tm", "xe",
            "yb", "zn", "zr"};

    public static final char[] TableOfSingleElements = {'b', 'c', 'f', 'h', 'i', 'k', 'n', 'o', 'p', 's', 'u', 'v', 'w', 'y'};

    static HashMap<String, Boolean> storage;

    public static boolean isWritableInTableOfElements(String word) {
        if (storage == null) storage = new HashMap<>();
        if (storage.containsKey(word)) return storage.get(word);

        if (word.length() == 0) return true;
        else if (word.length() == 1) {
            var existInSmall = Arrays.binarySearch(TableOfSingleElements, word.charAt(0)) >= 0;
            storage.put(word, existInSmall);
            return existInSmall;
        }

        var letter = word.charAt(0);
        var dLetter = word.substring(0, 2);

        var a = Arrays.binarySearch(TableOfSingleElements, letter) >= 0;
        var b = Arrays.binarySearch(TableOfDoubleElements, dLetter) >= 0;

        if (a && b)
            return isWritableInTableOfElements(word.substring(1)) || isWritableInTableOfElements(word.substring(2));
        else if (a) {
            var res = isWritableInTableOfElements(word.substring(1));
            if (word.length() < 500)
                storage.put(word, res);
            return res;
        } else if (b) {
            var res = isWritableInTableOfElements(word.substring(2));
            if (word.length() < 500)
                storage.put(word, res);
            return res;
        } else {
            if (word.length() < 500)
                storage.put(word, false);
            return false;
        }
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

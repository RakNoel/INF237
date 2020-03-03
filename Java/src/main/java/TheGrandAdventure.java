import java.util.EmptyStackException;
import java.util.Stack;

public class TheGrandAdventure {
    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);

        int tests = kattio.getInt();

        main:
        for (int round = 0; round < tests; round++) {
            Stack<Character> backpack = new Stack<>();
            var line = kattio.getWord().toCharArray();

            try {
                for (var s : line) {
                    switch (s) {
                        case '.':
                            continue;
                        case '$':
                        case '*':
                        case '|':
                            backpack.push(s);
                            break;
                        case 't':
                            if (backpack.pop() != '|') {
                                System.out.println("NO");
                                continue main;
                            }
                            break;
                        case 'j':
                            if (backpack.pop() != '*') {
                                System.out.println("NO");
                                continue main;
                            }
                            break;
                        case 'b':
                            if (backpack.pop() != '$') {
                                System.out.println("NO");
                                continue main;
                            }
                    }
                }
                if (backpack.size() == 0)
                    System.out.println("YES");
                else
                    System.out.println("NO");
            } catch (EmptyStackException e) {
                System.out.println("NO");
            }
        }
    }
}

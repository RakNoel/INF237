import java.util.Scanner;

public class SumOfTheOthers {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        while (scn.hasNextLine()) {
            var sints = scn.nextLine().split(" ");
            int[] ints = new int[sints.length];
            long sum = 0;

            for (int i = 0; i < sints.length; i++) {
                ints[i] = Integer.parseInt(sints[i]);
                sum += ints[i];
            }

            for (int master : ints) {
                if (sum - master == master) {
                    System.out.println(master);
                    break;
                }
            }
        }
    }
}

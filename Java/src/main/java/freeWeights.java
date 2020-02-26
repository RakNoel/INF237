import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class freeWeights {
    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);

        int pairs = kattio.getInt();

        int[] row1 = new int[pairs];
        int[] row2 = new int[pairs];

        Set<Integer> uniqueWeights = new HashSet<>();

        for (int i = 0; i < pairs; i++) {
            row1[i] = kattio.getInt();
            uniqueWeights.add(row1[i]);
        }
        for (int i = 0; i < pairs; i++) {
            row2[i] = kattio.getInt();
            uniqueWeights.add(row2[i]);
        }

        uniqueWeights.add(0);
        int[] sortedUniqueWeights = uniqueWeights.stream().mapToInt(x -> x).sorted().toArray();

        //BinarySearch / Bisection
        int minPointer = 0;
        int maxPointer = sortedUniqueWeights.length;

        while (minPointer < maxPointer) {
            int pointer = (maxPointer + minPointer) / 2;
            var tmpWeight = sortedUniqueWeights[pointer];
            if (testSolution(row1, row2, tmpWeight)) maxPointer = pointer;
            else minPointer = pointer + 1;
        }

        System.out.println(sortedUniqueWeights[minPointer]);
    }

    public static boolean testSolution(int[] list1, int[] list2, int minWeight) {
        int[] l1 = Arrays.stream(list1).filter(x -> x > minWeight).toArray();
        int[] l2 = Arrays.stream(list2).filter(x -> x > minWeight).toArray();
        return isOrdered(l1) && isOrdered(l2);
    }

    public static boolean isOrdered(int[] list) {
        if (list.length == 0) return true;
        if (list.length % 2 != 0) return false;
        for (int i = 0; i < list.length; i++)
            if (list[i] != list[++i])
                return false;
        return true;
    }
}

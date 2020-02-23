import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrainSortingTest {
    @Test
    public void LIS_Test() {
        int[] x = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
        assertEquals(6, maxValue(TrainSorting.LIS(x)));

        int[] y = {5, 8, 3, 7, 9, 1};
        assertEquals(3, maxValue(TrainSorting.LIS(y)));

        int[] z = {10,  22,  9,  33,  21,  50,  41,  60,  80};
        assertEquals(6, maxValue(TrainSorting.LIS(z)));
    }

    @Test
    public void LDS_Test() {
        int[] x = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
        assertEquals(5, maxValue(TrainSorting.LDS(x)));
    }

    @Test
    public void LBS_Test(){
        int[] x = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
        assertEquals(7, TrainSorting.LBS(x));

        int[] y = {1000, 1, 2, 3, 4, 5, 6};
        assertEquals(6, TrainSorting.LBS(y));
    }

    @Test
    public void shortTests(){
        int[] x = {1};
        assertEquals(1, TrainSorting.solveProblem(x));

        int[] y = {1, 2, 3};
        assertEquals(3, TrainSorting.solveProblem(y));

        int[] z = {3, 2, 1};
        assertEquals(3, TrainSorting.solveProblem(z));

        int[] j = {1, 3, 2};
        assertEquals(2, TrainSorting.solveProblem(j));

        int[] p = {5, 1, 2, 3, 4, 1000, 0};
        assertEquals(6, TrainSorting.solveProblem(p));
    }

    private int maxValue(int[] a){
        int max = a[0];
        for (int i = 1; i < a.length; i++) max = Math.max(max, a[i]);
        return max;
    }
}

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
    }

    private int maxValue(int[] a){
        int max = a[0];
        for (int i = 1; i < a.length; i++) max = Math.max(max, a[i]);
        return max;
    }
}

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrainSortingTest {

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
}

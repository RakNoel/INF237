import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GruesomeCaveTest {

    public static final char[][] BASICTEST = {
            {'#', '#', '#', '#', '#', '#'},
            {'#', '#', ' ', '#', '#', '#'},
            {'E', ' ', ' ', ' ', 'D', '#'},
            {'#', '#', ' ', '#', '#', '#'},
            {'#', '#', '#', '#', '#', '#'}
    };

    @Test
    public void calculateGrueRiskKattisTest() {
        assertEquals(0.75f, GruesomeCave.calculateGrueRisk(BASICTEST));
    }

    @Test
    public void getTotalRiskTest(){
        var riskTable = GruesomeCave.getRiskTable(BASICTEST);
        assertEquals(8, GruesomeCave.getCaveInfo(riskTable).maxRisk);
    }

    @Test
    public void getRiskTableTest() {
        var riskTable = GruesomeCave.getRiskTable(BASICTEST);
        for (int i = 0; i < riskTable.length; i++)
            for (int j = 0; j < riskTable[0].length; j++)
                if (BASICTEST[i][j] == '#')
                    assertEquals(-1, riskTable[i][j]);
                else if (BASICTEST[i][j] == 'D' || BASICTEST[i][j] == 'E')
                    assertEquals(0, riskTable[i][j]);
                else
                    assertTrue(riskTable[i][j] > 0);
    }
}

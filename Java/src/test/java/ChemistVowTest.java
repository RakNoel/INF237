import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class ChemistVowTest {
    @Test
    public void KattisWords() {
        String[] inputs = {"international", "collegiate", "programming", "contest"};
        boolean[] results = {true, false, false, false};

        for (int i = 0; i < inputs.length; i++)
            assertEquals(results[i], ChemistVow.isWritableInTableOfElements(inputs[i]));
    }

    @Test
    public void simpleWords() {
        String[] inputs = {"hhhhhhhh", "CoCoCoCoCoCoCo"};
        boolean[] results = {true, true};

        for (int i = 0; i < inputs.length; i++)
            assertEquals(results[i], ChemistVow.isWritableInTableOfElements(inputs[i]));
    }

    @Test
    public void longWord() {
        String[] inputs = {"hohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohoho" +
                "hohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohoho" +
                "hohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohoh" +
                "ohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohoho" +
                "hohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohohoh" +
                "ohohohohohohohohohohoho", "CoCoCoCoCoCoCoHOHOHOHOHOHOHOHOHOHOHOHOCOoACOoAC"};

        for (String input : inputs) assertTrue(ChemistVow.isWritableInTableOfElements(input));
    }

    @Test
    public void falseCases(){
        String[] inputs = {"aahaaho"};
        for (String input : inputs) assertFalse(ChemistVow.isWritableInTableOfElements(input));
    }

    @Test
    public void allKnownWords() throws FileNotFoundException {
        Scanner scn = new Scanner(new File("target/test-classes/chemistryWords.txt"));
        while (scn.hasNextLine()) {
            var x = scn.nextLine();
            assertTrue(ChemistVow.isWritableInTableOfElements(x.toLowerCase()), "Failing on word: " + x);
        }
    }

    @Test
    public void buildLargePositiveTest() {
        Random rnd = new Random();
        for (int i = 0; i < 1000; i++) {
            var r = rnd.nextInt(50000);
            StringBuilder bldr = new StringBuilder();
            for (int j = 0; j < r; j++) {
                if (bldr.length() >= 1000) break;
                bldr.append(rnd.nextBoolean() ?
                        ChemistVow.TableOfSingleElements[rnd.nextInt(ChemistVow.TableOfSingleElements.length - 1)] :
                        ChemistVow.TableOfDoubleElements[rnd.nextInt(ChemistVow.TableOfDoubleElements.length - 1)]
                );
            }
            System.out.println(bldr);
            assertTrue(ChemistVow.isWritableInTableOfElements(bldr.toString()));
        }
    }
}

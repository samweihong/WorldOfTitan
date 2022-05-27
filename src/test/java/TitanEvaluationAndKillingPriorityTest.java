import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TitanEvaluationAndKillingPriorityTest {

    @Test
    void testEvaluateDangerRisk() {

    }

    private Queue<List<String>> getTestData() {
        Queue<List<String>> data = new ArrayDeque<>();
        try (Scanner input = new Scanner(new FileInputStream("src/test/resources/test-data/evaluateDangerRiskTest.txt"))) {
            while (input.hasNextLine()) {
                List<String> unitTest = new ArrayList<>();
                int noOfTitans = input.nextInt();
                input.nextLine();
                for (int i = 0; i < noOfTitans; i++) {
                    String titan = input.nextLine();
                    unitTest.add(titan);
                }
                data.offer(unitTest);
            }
        } catch (FileNotFoundException e) {
            System.out.println("evaluateDangerRisk: Test data file is not found!");
            return null;
        }
        return data;
    }

}
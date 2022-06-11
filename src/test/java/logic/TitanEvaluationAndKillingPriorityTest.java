package logic;

import logic.Titan;
import logic.TitanEvaluationAndKillingPriority;
import org.junit.jupiter.api.Test;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TitanEvaluationAndKillingPriorityTest {

    @Test @SuppressWarnings("ConstantConditions")
    void testEvaluateDangerRisk() {
        Queue<List<Titan>> data = getTestData();
        assertNotNull(data);

        assertEquals(Arrays.asList(19, 13, 15), TitanEvaluationAndKillingPriority.evaluateDangerRisk(data.poll()));
        assertEquals(Arrays.asList(19, 15), TitanEvaluationAndKillingPriority.evaluateDangerRisk(data.poll()));
        assertEquals(List.of(19), TitanEvaluationAndKillingPriority.evaluateDangerRisk(data.poll()));
        assertEquals(List.of(15), TitanEvaluationAndKillingPriority.evaluateDangerRisk(data.poll()));
        assertEquals(List.of(8), TitanEvaluationAndKillingPriority.evaluateDangerRisk(data.poll()));
        assertEquals(Arrays.asList(15, 10), TitanEvaluationAndKillingPriority.evaluateDangerRisk(data.poll()));
        assertEquals(Arrays.asList(15, 5, 12), TitanEvaluationAndKillingPriority.evaluateDangerRisk(data.poll()));
        assertTrue(data.isEmpty());
    }

    private Queue<List<Titan>> getTestData() {
        Queue<List<Titan>> data = new ArrayDeque<>();
        try (Scanner input = new Scanner(new FileInputStream("src/test/resources/test-data/evaluateDangerRiskTest.txt"))) {
            while (input.hasNextLine()) {
                List<Titan> unitTest = new ArrayList<>();
                int noOfTitans = input.nextInt();
                input.nextLine();
                for (int i = 0; i < noOfTitans; i++) {
                    String titan = input.nextLine();
                    unitTest.add(Titan.toTitan(titan));
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
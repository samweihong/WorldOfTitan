import logic.Graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    void testFindPaths(){

        Queue<List<String>> data = getTestData();
        assertNotNull(data);

        assertEquals(Arrays.asList(0, 1, 6), Graph.findPaths(data.poll()));

    }

    private Queue<List<List<Integer>>> getTestData() {
        Queue<List<List<Integer>>> data = new ArrayDeque<>();
        try (Scanner input = new Scanner(new FileInputStream("src/test/resources/test-data/bestPathToKillTitanTest.txt"))) {
            while (input.hasNextLine()) {
                List<List<Integer>> unitTest = new ArrayList<>();
                int dest = input.nextInt();
                int noOfPaths = input.nextInt();
                input.nextLine();
                for (int i = 0; i < noOfPaths; i++) {
                    String path = input.nextLine();
                    String[] pathIndices = path.split(" ");
                    ArrayList<Integer> intPathIndices = new ArrayList<>();
                    for(int j = 0; j < pathIndices.length; j++){
                        int a = Integer.parseInt(pathIndices[j]);
                        intPathIndices.add(a);
                    }
                    unitTest.add(intPathIndices);
                }
                data.offer(unitTest);
            }
        } catch (FileNotFoundException e) {
            System.out.println("bestPathToKillTitan: Test data file is not found!");
            return null;
        }
        return data;
    }

}
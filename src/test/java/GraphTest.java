import logic.Graph;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @Test
    void testFindPaths(){

        Queue<List<List<Integer>>> data = getTestData();
        assertNotNull(data);

        assertEquals(Arrays.asList(
                Arrays.asList(0, 1, 6),
                Arrays.asList(0, 5, 6)
        ), Graph.findPaths(data.poll(), 0, 6));
        assertEquals(Arrays.asList(
                Arrays.asList(0, 1, 6, 15),
                Arrays.asList(0, 7, 9, 15)
        ), Graph.findPaths(data.poll(), 0, 15));
        assertEquals(Arrays.asList(
                Arrays.asList(0, 1)
        ), Graph.findPaths(data.poll(), 0, 1));
        assertEquals(Arrays.asList(
                Arrays.asList(0, 5)
        ), Graph.findPaths(data.poll(), 0, 5));
        assertEquals(Arrays.asList(
                Arrays.asList(0, 7, 9)
        ), Graph.findPaths(data.poll(), 0, 9));
        assertEquals(Arrays.asList(
                Arrays.asList(0, 1, 4, 10, 14),
                Arrays.asList(0, 1, 6, 15, 14),
                Arrays.asList(0, 1, 2, 13, 14)
        ), Graph.findPaths(data.poll(), 0, 14));
        assertEquals(Arrays.asList(
                Arrays.asList(0, 5, 12)
        ), Graph.findPaths(Graph.mapOfParadis, 0, 12));
        assertEquals(Arrays.asList(
                Arrays.asList(0, 1, 6, 8)
        ), Graph.findPaths(data.poll(), 0, 8));


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
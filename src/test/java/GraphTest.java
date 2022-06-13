import logic.Graph;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @Test
    void testFindPaths(){

        List<List<Integer>> map = Graph.mapOfParadis;
        
        Queue<Integer> data = getTestData();
        assertNotNull(data);

        assertEquals(Arrays.asList(
                Arrays.asList(0, 1, 6),
                Arrays.asList(0, 5, 6)
        ), Graph.findPaths(map, 0, data.poll()));
        assertEquals(Arrays.asList(
                Arrays.asList(0, 1, 6, 15),
                Arrays.asList(0, 5, 6, 15),
                Arrays.asList(0, 7, 9, 15)
        ), Graph.findPaths(map, 0, data.poll()));
        assertEquals(Arrays.asList(
                Arrays.asList(0, 1)
        ), Graph.findPaths(map, 0, data.poll()));
        assertEquals(Arrays.asList(
                Arrays.asList(0, 5)
        ), Graph.findPaths(map, 0, data.poll()));
        assertEquals(Arrays.asList(
                Arrays.asList(0, 7, 9)
        ), Graph.findPaths(map, 0, data.poll()));
        assertEquals(Arrays.asList(
                Arrays.asList(0, 1, 2, 13, 14),
                Arrays.asList(0, 1, 4, 10, 14),
                Arrays.asList(0, 1, 6, 15, 14),
                Arrays.asList(0, 5, 6, 15, 14),
                Arrays.asList(0, 7, 9, 15, 14)
        ), Graph.findPaths(map, 0, data.poll()));
        assertEquals(Arrays.asList(
                Arrays.asList(0, 5, 12)
        ), Graph.findPaths(map, 0, data.poll()));
        assertEquals(Arrays.asList(
                Arrays.asList(0, 1, 6, 8),
                Arrays.asList(0, 5, 6, 8)
        ), Graph.findPaths(map, 0, data.poll()));


    }

    private Queue<Integer> getTestData() {
        Queue<Integer> data = new ArrayDeque<>();
        try (Scanner input = new Scanner(new FileInputStream("src/test/resources/test-data/bestPathToKillTitanTest.txt"))) {
            while (input.hasNextInt()) {
                Integer unitTest = input.nextInt();
                data.offer(unitTest);
            }
        } catch (FileNotFoundException e) {
            System.out.println("bestPathToKillTitan: Test data file is not found!");
            return null;
        }
        return data;
    }

}
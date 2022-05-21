import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class WallOfMariaTest {

    @Test
    void testGetWeakestPoints() {
        Queue<List<List<Integer>>> data = getTestData();
        assertNotNull(data);
    }

    private Queue<List<List<Integer>>> getTestData() {
        Queue<List<List<Integer>>> data = new ArrayDeque<>();
        try (Scanner input = new Scanner(new FileInputStream("src/test/resources/test-data/wall-of-maria.txt"))) {
            while (input.hasNextLine()) {
                List<List<Integer>> wallStructure = new ArrayList<>();
                int numOfLayers = input.nextInt();
                input.nextLine();
                for (int i = 0; i < numOfLayers; i++) {
                    List<Integer> edges = Arrays.stream(input.nextLine()
                                    .split(" "))
                                    .mapToInt(Integer::parseInt)
                                    .boxed()
                                    .toList();
                    wallStructure.add(edges);
                }
                data.offer(wallStructure);
            }
        } catch (FileNotFoundException e) {
            System.out.println("WallOfMaria: Test data file is not found!");
            return null;
        }
        return data;
    }
}

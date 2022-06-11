package logic;

import logic.WallOfMaria;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class WallOfMariaTest {

    @Test @SuppressWarnings("ConstantConditions")
    void testGetWeakestPoints() {
        Queue<List<List<Integer>>> data = getTestData();
        assertNotNull(data);

        assertEquals(List.of(9), WallOfMaria.getWeakestPoints(data.poll()));
        assertEquals(Arrays.asList(20, 50), WallOfMaria.getWeakestPoints(data.poll()));
        assertEquals(List.of(11), WallOfMaria.getWeakestPoints(data.poll()));
        assertEquals(Arrays.asList(5, 10, 15), WallOfMaria.getWeakestPoints(data.poll()));
        assertTrue(data.isEmpty());
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

    @Test
    void testReadInput() {
        String inputText = """
                4
                3 6 9
                2 5 8 9
                1 4 10
                5 7 9
                """;
        InputStream input = new ByteArrayInputStream(inputText.getBytes());
        System.setIn(input);
        List<List<Integer>> actualList = WallOfMaria.readInput();

        int[][] a = {{3,6,9},{2,5,8,9},{1,4,10},{5,7,9}};
        List<List<Integer>> expectedList = Arrays.stream(a).map(e -> Arrays.stream(e)
                                                                    .boxed()
                                                                    .toList())
                                                            .toList();
        assertEquals(expectedList, actualList);
    }
}

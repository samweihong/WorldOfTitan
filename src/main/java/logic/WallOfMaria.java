package logic;

import java.util.*;

public class WallOfMaria {
    /**
     * Returns the weakest parts of the wall in a List.
     * @param wallStructure the brick edges in all layers, where each inner List represents a layer
     * @return the weakest parts of the wall
     */
    public static List<Integer> getWeakestPoints(List<List<Integer>> wallStructure) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int max = 0; // the maximum number of edges in one position

        for (List<Integer> edges : wallStructure) {
            for (int edge : edges) {
                // increment the number of edges at that position by 1
                int newCount = countMap.getOrDefault(edge, 0) + 1;
                // update the HashMap with the new value
                countMap.put(edge, newCount);
                max = Math.max(max, newCount); // update the max counter
            }
        }

        List<Integer> res = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet())
            if (entry.getValue() == max)
                // add the index into the list if it has the maximum number of edges
                res.add(entry.getKey());
        Collections.sort(res);
        return res;
    }

    /**
     * Accepts the wall structure from standard input and stores it in a List
     * @return the brick edges in all layers, where each inner List represents a layer
     */
    public static List<List<Integer>> readInput() {
        List<List<Integer>> list = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        System.out.print("Enter number of layers: ");
        int numOfLayers = input.nextInt();
        input.nextLine();
        for (int i = 1; i <= numOfLayers; i++) {
            System.out.printf("Enter number of bricks for layer %d: ", i);
            list.add(Arrays.stream(input.nextLine()
                                    .split(" "))
                                    .mapToInt(Integer::parseInt)
                                    .boxed()
                                    .toList());
        }
        return list;
    }

    // Tester method
    public static void main(String[] args) {
        List<List<Integer>> input = readInput();
        List<Integer> output = getWeakestPoints(input);
        System.out.println();
        if (output.size() == 1)
            System.out.println("Weakest part of the wall is at position " + output.get(0));
        else
            System.out.println("Weakest parts of the wall are at position " + Utils.formatList(output));
    }
}

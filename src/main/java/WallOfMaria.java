import java.util.*;

public class WallOfMaria {
    public static List<Integer> getWeakestPoints(List<List<Integer>> wallStructure) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int max = 0;

        for (List<Integer> edges : wallStructure) {
            for (int edge : edges) {
                int newCount = countMap.getOrDefault(edge, 0) + 1;
                countMap.put(edge, newCount);
                max = Math.max(max, newCount);
            }
        }

        List<Integer> res = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet())
            if (entry.getValue() == max)
                res.add(entry.getKey());
        Collections.sort(res);
        return res;
    }

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

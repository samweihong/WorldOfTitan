import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return res;
    }
}

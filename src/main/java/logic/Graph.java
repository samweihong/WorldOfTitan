package logic;

import data_objects.GameCharacter;
import data_objects.MapOfParadis;
import javafx.util.Pair;

import java.util.*;
import java.util.HashMap;

public class Graph {
    public static String printPath(List<Integer> path) {
        StringBuilder direction = new StringBuilder();
        for (Integer v : path) direction.append(v).append("-->");
        return direction.substring(0, direction.length() - 3);
    }

    public static boolean isNotVisited(int x, List<Integer> path) {
        for (Integer i : path)
            if (i == x)
                return false;
        return true;
    }

    public static List<Integer> findPath(List<List<Integer>> graph, int source, int destination) {
        Queue<List<Integer>> queue = new LinkedList<>();

        List<Integer> path = new ArrayList<>();
        path.add(source);
        queue.offer(path);

        List<List<Integer>> solutions = new ArrayList<>();

        while (!queue.isEmpty()) {
            path = queue.poll();
            int last = path.get(path.size() - 1);
            if (last == destination) return path;
            List<Integer> lastNode = graph.get(last);
            for (Integer node : lastNode) {
                if (isNotVisited(node, path)) {
                    List<Integer> newpath = new ArrayList<>(path);
                    newpath.add(node);
                    queue.offer(newpath);
                }
            }
        }
        return path;
    }

    public static int pathDistance(List<Integer> path){ return path.size() - 1; }

    public static List<List<Integer>> findPaths(List<List<Integer>> graph, int source, int destination) {
        Queue<List<Integer>> queue = new LinkedList<>();

        List<Integer> path = new ArrayList<>();
        path.add(source);
        queue.offer(path);

        List<List<Integer>> solutions = new ArrayList<>();

        while (!queue.isEmpty()) {
            path = queue.poll();
            int last = path.get(path.size() - 1);
            if (last == destination) solutions.add(path);
            List<Integer> lastNode = graph.get(last);
            for (Integer node : lastNode) {
                if (isNotVisited(node, path)) {
                    List<Integer> newpath = new ArrayList<>(path);
                    newpath.add(node);
                    queue.offer(newpath);
                }
            }
        }
        int size = solutions.get(0).size();
        List<List<Integer>> optimalSolutions = new ArrayList<>();
        for (List<Integer> solution : solutions) {
            if (solution.size() > size) break;
            else optimalSolutions.add(solution);
        }
        for (List<Integer> optimalSolution : optimalSolutions) System.out.println(printPath(optimalSolution));
        return optimalSolutions;
    }

    public static void hamiltonianCycle(List<List<Integer>> map, int start, boolean[] visited, List<Integer> path, int n, int s) {
        if (path.size() == n && path.get(0) == s && map.get(path.get(15)).contains(s)) {
            System.out.println();
            System.out.println("Path found!");
            System.out.println(printPath(path) + "-->" + s);
            return;
        }

        for (int w : map.get(start)) {
            if (!visited[w]) {
                visited[w] = true;
                path.add(w);

                hamiltonianCycle(map, w, visited, path, n, s);

                visited[w] = false;
                path.remove(path.size() - 1);
            }
        }
    }

    public static void findHamiltonianCycle(List<List<Integer>> map, int n, int s) {
        for (int start = 0; start < n; start++) {
            List<Integer> path = new ArrayList<>();
            path.add(start);

            boolean[] visited = new boolean[n];
            visited[start] = true;

            hamiltonianCycle(map, start, visited, path, n, s);
        }
    }

    /**
     * Returns a list of the shortest paths to kill a moving titan.
     * @param map the graph represented as adjacency list
     * @param titanPath the path of the moving titan
     * @param startNode the node to start
     * @return a list of the shortest paths to kill a moving titan
     */
    public static LinkedList<LinkedList<Integer>> killMovingTitan(List<List<Integer>> map, int[] titanPath, int startNode) {
        for (int steps = 1; ; steps++) {
            int target = titanPath[Math.min(steps/2, titanPath.length-1)];
            LinkedList<LinkedList<Integer>> result = killMovingTitan(map, startNode, target, steps);
            if (!result.isEmpty()) return result;
        }
    }

    /**
     * Returns a list of paths where the titan can be killed in the specified steps.
     * @param map the graph represented as adjacency list
     * @param start the index to start searching
     * @param target the index of titan
     * @param steps the exact number of steps required to kill the titan
     */
    private static LinkedList<LinkedList<Integer>> killMovingTitan(List<List<Integer>> map,
                                                                   int start, int target, int steps) {
        Queue<LinkedList<Integer>> q = new ArrayDeque<>();
        q.offer(new LinkedList<>(List.of(start)));

        // Search for all paths with the length of specified steps
        for (int i = 0; i <= steps; i++) {
            for (int size = q.size(); size > 0; size--) {
                LinkedList<Integer> currentPath = q.poll();
                @SuppressWarnings("ConstantConditions")
                List<Integer> neighbours = map.get(currentPath.getLast());
                for (Integer neighbour : neighbours) {
                    LinkedList<Integer> newPath = new LinkedList<>(currentPath);
                    newPath.add(neighbour);
                    q.offer(newPath);
                }
            }
        }

        // Stores all paths that reach the target destination in the end
        LinkedList<LinkedList<Integer>> result = new LinkedList<>();
        while (!q.isEmpty()) {
            LinkedList<Integer> path = q.poll();
            if (path.getLast() == target)
                result.add(path);
        }
        return result;
    }

    public static List<Integer> killBeastTitan(GameCharacter gameCharacter, int startIndex, int titanIndex) {
        int[] labels = new int[MapOfParadis.MAP.size()];
        Arrays.fill(labels, Integer.MAX_VALUE);
        labels[startIndex] = MapOfParadis.getTime(gameCharacter, startIndex);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(index -> labels[index]));
        pq.offer(startIndex);

        Map<Integer, Integer> previousNodeMap = new HashMap<>();
        previousNodeMap.put(startIndex, null);

        Set<Integer> removedNodes = new HashSet<>();

        while (!pq.isEmpty()) {
            int current = pq.poll();
            if (current == titanIndex) break;
            removedNodes.add(current);

            for (int neighbour : MapOfParadis.MAP.get(current)) {
                if (removedNodes.contains(neighbour)) continue;

                int newLabel = labels[current] + MapOfParadis.getTime(gameCharacter, neighbour);
                if (newLabel < labels[neighbour]) {
                    labels[neighbour] = newLabel;
                    pq.remove(neighbour);
                    pq.offer(neighbour);
                    previousNodeMap.put(neighbour, current);
                }
            }
        }

        LinkedList<Integer> result = new LinkedList<>();
        for (Integer current = titanIndex; current != null; current = previousNodeMap.get(current))
            result.addFirst(current);
        return result;
    }

    public static void startKillBeastTitan() {
        Scanner scanner = new Scanner(System.in);

        String name = "";
        boolean valid1 = false;
        while(!valid1){
            try{
                System.out.print("Choose soldier: ");
                name = scanner.nextLine();
                if(GameCharacterList.getGameCharacter(name).name().equals(name)) valid1 = true;
            }
            catch (NullPointerException e){
                System.out.println("Invalid input! Please try again");
            }
        }

        int startIndex = 0;
        boolean valid2 = false;
        while(!valid2){
            try{
                System.out.print("Where will you start? ");
                startIndex = scanner.nextInt();
                if(startIndex >= 1 && startIndex <= 15) valid2 = true;
                else System.out.println("Invalid input! Please try again");
            }
            catch (NullPointerException e){
                System.out.println("Invalid input! Please try again");
            }
        }

        int titanIndex = 0;
        boolean valid3 = false;
        while(!valid3){
            try{
                System.out.print("Where is Beast Titan? ");
                titanIndex = scanner.nextInt();
                if(titanIndex >= 1 && titanIndex <= 15) valid3 = true;
                else System.out.println("Invalid input! Please try again");
            }
            catch (NullPointerException e){
                System.out.println("Invalid input! Please try again");
            }
        }

        System.out.println(Graph.printPath(Graph.killBeastTitan(GameCharacterList.getGameCharacter(name), startIndex, titanIndex)));

    }

    public static void startHamiltonianCycle() {
        System.out.println("Finding Hamiltonian cycle...");
        Scanner scanner = new Scanner(System.in);
        int point = -1;
        while (point < 0 || point > 15) {
            System.out.print("Enter starting point: ");
            try {
                point = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter again.");
            }
            if (point < 0 || point > 15) {
                System.out.println("Starting point is not on the Map of Paradis! Please enter again.");
                scanner = new Scanner(System.in);
            } else findHamiltonianCycle(MapOfParadis.MAP, 16, point);
            System.out.println();
        }
    }

    public static void startBestPathToKillTitan() {
        System.out.println("Kill the titan using the best path!");
        Scanner scanner = new Scanner(System.in);

        int location = -1;
        while (location < 0 || location > 15) {
            System.out.print("Enter location of Titan: ");
            try {
                location = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter again.");
            }
            if (location < 0 || location > 15) {
                System.out.println("Location unavailable - it's not on the Map of Paradis! Please enter again.");
                scanner = new Scanner(System.in);
            } else findPaths(MapOfParadis.MAP, 0, location);
            System.out.println();
        }
    }
}

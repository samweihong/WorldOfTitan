package logic;

import java.util.*;

public class Graph {
    public static final List<List<Integer>> MAP_OF_PARADIS = Arrays.asList(
        Arrays.asList(1, 5, 7),
        Arrays.asList(0, 2, 4, 6),
        Arrays.asList(1, 3, 11, 13),
        Arrays.asList(2, 10),
        Arrays.asList(1, 6, 10),
        Arrays.asList(0, 6, 7, 12),
        Arrays.asList(1, 4, 5, 8, 15),
        Arrays.asList(0, 5, 9),
        Arrays.asList(6, 10),
        Arrays.asList(7, 12, 15),
        Arrays.asList(3, 4, 8, 14),
        Arrays.asList(2, 13),
        Arrays.asList(5, 9),
        Arrays.asList(2, 11, 14),
        Arrays.asList(10, 13, 15),
        Arrays.asList(6, 9, 14)
    );

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

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int selection;
        do {
            System.out.print("""
                    Make selection:
                    1. Find Hamiltonian Cycle
                    2. Find best path to kill titan
                                            
                    Input:  """);
            selection = scanner.nextInt();
            int point;
            if (selection == 1) {
                do {
                    System.out.print("Enter starting point: ");
                    point = scanner.nextInt();

                    if (point < 0 || point > 15) System.out.println("Starting point not on Map of Paradis");
                    else findHamiltonianCycle(MAP_OF_PARADIS, 16, point);
                } while (point < 0 || point > 15);
            } else if (selection == 2) {
                System.out.print("Enter location of Titan: ");
                int location = scanner.nextInt();
                scanner.close();


                // finding shortest path
                System.out.println("Best path(s):");
                try {
                    findPaths(Graph.MAP_OF_PARADIS, 0, location);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Location unavailable: not on Map of Paradis");
                }
            } else {
                System.out.println("Invalid selection!");
            }
        } while (selection != 1 && selection != 2);
    }
}

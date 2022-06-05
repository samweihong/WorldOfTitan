package logic;

import java.util.*;

public class Graph{

    public static List<List<Integer>> mapOfParadis = new ArrayList(Arrays.asList(
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
    ));

    public static String printPath(List<Integer> path){
        String direction = "";
        for(Integer v : path) direction += (v + "-->");
        return direction.substring(0, direction.length() - 3);
    }
    public static boolean isNotVisited(int x, List<Integer> path){
        for(int i = 0; i < path.size(); i++){
            if (path.get(i) == x)
                return false;
        }
        return true;
    }
    public static void findpaths(List<List<Integer>> graph, int source, int destination) {
        java.util.Queue<List<Integer>> queue = new LinkedList<>();
        
        List<Integer> path = new ArrayList<>();
        path.add(source);
        queue.offer(path);

        List<List<Integer>> solutions = new ArrayList<>();

        while (!queue.isEmpty()){
            path = queue.poll();
            int last = path.get(path.size() - 1);
            if (last == destination) solutions.add(path);
            List<Integer> lastNode = graph.get(last);
            for(int i = 0; i < lastNode.size(); i++){
                if (isNotVisited(lastNode.get(i), path)){
                    List<Integer> newpath = new ArrayList<>(path);
                    newpath.add(lastNode.get(i));
                    queue.offer(newpath);
                }
            }
        }
        int size = solutions.get(0).size();
        List<List<Integer>> optimalSolutions = new ArrayList<>();
        for(int i = 0; i < solutions.size(); i++){
            if(solutions.get(i).size() > size) break;
            else optimalSolutions.add(solutions.get(i));
        }
        for(List<Integer> optimalSolution : optimalSolutions) System.out.println(printPath(optimalSolution));
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter location of Titan: ");
        int location = scanner.nextInt();
        scanner.close();


        // finding shortest path
        System.out.println("Best path(s):");
        findpaths(Graph.mapOfParadis, 0, location);

    }
}
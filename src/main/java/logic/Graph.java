package logic;

import java.util.*;

public class Graph{

    public static String printPath(List<Integer> path){
        String direction = "";
        for(Integer v : path) direction += (v + "->");
        return direction.substring(0, direction.length() - 2);
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

        // Creating map of Paradis
        List<List<Integer>> map = new ArrayList<>();
        for(int i = 0; i < 16; i++) map.add(new ArrayList<>());

        map.get(0).addAll(Arrays.asList(1, 5, 7));
        map.get(1).addAll(Arrays.asList(0, 2, 4, 6));
        map.get(2).addAll(Arrays.asList(1, 3, 11, 13));
        map.get(3).addAll(Arrays.asList(2, 10));
        map.get(4).addAll(Arrays.asList(1, 6, 10));
        map.get(5).addAll(Arrays.asList(0, 6, 7, 12));
        map.get(6).addAll(Arrays.asList(1, 4, 5, 8, 15));
        map.get(7).addAll(Arrays.asList(0, 5, 9));
        map.get(8).addAll(Arrays.asList(6, 10));
        map.get(9).addAll(Arrays.asList(7, 12, 15));
        map.get(10).addAll(Arrays.asList(3, 4, 8, 14));
        map.get(11).addAll(Arrays.asList(2, 13));
        map.get(12).addAll(Arrays.asList(5, 9));
        map.get(13).addAll(Arrays.asList(2, 11, 14));
        map.get(14).addAll(Arrays.asList(10, 13, 15));
        map.get(15).addAll(Arrays.asList(6, 9, 14));

        // finding shortest path
        findpaths(map, 0, 6);

    }
}
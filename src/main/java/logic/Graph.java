package logic;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {

    public static class Vertex<T>{
        private T data;
        private boolean visited;
        private List<Vertex<T>> neighbours = new LinkedList<>();
        public Vertex(T data) { this.data = data; }

        public boolean isVisited() { return visited; }

        public void setVisited(boolean visited) { this.visited = visited; }

        public List<Vertex<T>> getNeighbours() { return neighbours; }

        public void setNeighbours(List<Vertex<T>> neighbours) { this.neighbours = neighbours; }
    }

    public static class BFS<T>{
        private Vertex<T> startVertex;
        public BFS(Vertex<T> startVertex){ this.startVertex = startVertex; }
        public void traverse(){
            Queue<Vertex<T>> queue = new LinkedList<>();
            queue.add(startVertex);
            while(!queue.isEmpty()){
                Vertex<T> current = queue.poll();
                if(!current.isVisited()){
                    current.setVisited(true);
                    queue.addAll(current.getNeighbours());
                }
            }
        }

    }

//    public static int[][] map = {
//           /* 0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 */
//        /*0*/{0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
//        /*1*/{1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//        /*2*/{0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0},
//        /*3*/{0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
//        /*4*/{0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
//        /*5*/{1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0},
//        /*6*/{0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0 ,1},
//        /*7*/{1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
//        /*8*/{0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
//        /*9*/{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1},
//       /*10*/{0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0},
//       /*11*/{0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
//       /*12*/{0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
//       /*13*/{0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0},
//       /*14*/{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1},
//       /*15*/{0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0}
//    };

    public static void main(String[] args) {

        // Creating map of Paradis
        Vertex<Integer> v0 = new Vertex<>(0);
        Vertex<Integer> v1 = new Vertex<>(1);
        Vertex<Integer> v2 = new Vertex<>(2);
        Vertex<Integer> v3 = new Vertex<>(3);
        Vertex<Integer> v4 = new Vertex<>(4);
        Vertex<Integer> v5 = new Vertex<>(5);
        Vertex<Integer> v6 = new Vertex<>(6);
        Vertex<Integer> v7 = new Vertex<>(7);
        Vertex<Integer> v8 = new Vertex<>(8);
        Vertex<Integer> v9 = new Vertex<>(9);
        Vertex<Integer> v10 = new Vertex<>(10);
        Vertex<Integer> v11 = new Vertex<>(11);
        Vertex<Integer> v12 = new Vertex<>(12);
        Vertex<Integer> v13 = new Vertex<>(13);
        Vertex<Integer> v14 = new Vertex<>(14);
        Vertex<Integer> v15 = new Vertex<>(15);

        v0.setNeighbours(Arrays.asList(v1, v5, v7));
        v1.setNeighbours(Arrays.asList(v0, v2, v4, v6));
        v2.setNeighbours(Arrays.asList(v1, v3, v11, v13));
        v3.setNeighbours(Arrays.asList(v2, v10));
        v4.setNeighbours(Arrays.asList(v1, v6, v10));
        v5.setNeighbours(Arrays.asList(v0, v6, v7, v12));
        v6.setNeighbours(Arrays.asList(v1, v4, v5, v8, v15));
        v7.setNeighbours(Arrays.asList(v0, v5, v9));
        v8.setNeighbours(Arrays.asList(v6, v10));
        v9.setNeighbours(Arrays.asList(v7, v12, v15));
        v10.setNeighbours(Arrays.asList(v3, v4, v8, v14));
        v11.setNeighbours(Arrays.asList(v2, v13));
        v12.setNeighbours(Arrays.asList(v5, v9));
        v13.setNeighbours(Arrays.asList(v2, v11, v14));
        v14.setNeighbours(Arrays.asList(v10, v13, v15));
        v15.setNeighbours(Arrays.asList(v6, v9, v14));

    }

}

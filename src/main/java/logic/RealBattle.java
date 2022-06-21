package logic;

import collections.PriorityQueue;
import data_objects.MapOfParadis;
import data_objects.Titan;

import java.util.List;
import java.util.Scanner;

public class RealBattle extends TitanEvaluationAndKillingPriority{

    public static void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Choose soldier: ");
        String name = scanner.nextLine();
        int agility = GameCharacterList.getGameCharacter(name).agility();
        int strength = GameCharacterList.getGameCharacter(name).strength();
        int ability = agility + strength;
        int totalDistance = 0, previousIndex = 0;

        System.out.println();
        System.out.printf("%s's agility: %d\n%s's strength: %d\n%s's ability to kill titan: %d\n",
                name, agility, name, strength, name, ability);
        System.out.println();

        PriorityQueue<Titan> titanKillQueue = new PriorityQueue<>();
        Graph graph = new Graph();
        List<List<Integer>> map = MapOfParadis.MAP;

        System.out.print("Initial number of Titans: ");
        int noOfTitans = scanner.nextInt();

        System.out.println();
        System.out.printf("Generating %d Titans ...\n", noOfTitans);
        for (int i = 1; i <= noOfTitans; i++) {
            Titan titan = Titan.generateTitan();
            titan.setIndex(i);
            titanKillQueue.enqueue(titan);
            System.out.printf("Titan %d: %-80sRisk: %d\n", titan.getIndex(), titan, titan.getRisk());
        }

        while(!titanKillQueue.isEmpty()){
            System.out.println();
            System.out.println("Titans to be killed");
            for(int i = 0; i < titanKillQueue.getSize(); i++){
                System.out.printf("Titan %d: %-80sRisk: %d\n",
                        titanKillQueue.getElement(i).getIndex(),
                        titanKillQueue.getElement(i),
                        titanKillQueue.getElement(i).getRisk());
            }

            System.out.println();
            int maxDangerRisk = titanKillQueue.getElement(0).getRisk();
            if(maxDangerRisk > ability){
                System.out.println("ABORT MISSION! RUN FOR YOUR LIFE!!!");
                break;
            }
            else{
                System.out.println("Proceed with mission...");
                System.out.print("Sequence to kill: ");
                System.out.println(killPriority(titanKillQueue));
            }

            System.out.println();
            int currentIndex = titanKillQueue.peek().getIndex();
            System.out.printf("Best path to get from node %d to node %d: ", previousIndex, currentIndex);
            List<Integer> bestPath = Graph.findPath(map, previousIndex, currentIndex);
            System.out.println(Graph.printPath(bestPath));
            System.out.println();
            titanKillQueue.dequeue();
            System.out.printf("Killed Titan %d", currentIndex);
            int currentPathLength = Graph.pathDistance(bestPath);
            totalDistance += currentPathLength;
            previousIndex = currentIndex;
            System.out.println();
            System.out.printf("Current path length: %d\n", currentPathLength);
            System.out.printf("Total distance travelled so far: %d\n", totalDistance);

            System.out.print("Detected more titans? (y/n) ");
            scanner.nextLine();
            char moreTitans = scanner.next().toLowerCase().charAt(0);
            System.out.println();

            if(moreTitans == 'y'){
                System.out.print("How many more? ");
                int extraTitans = scanner.nextInt();
                System.out.println();
                System.out.printf("Generating %d more titans\n", extraTitans);
                for (int i = noOfTitans + 1; i <= noOfTitans + extraTitans; i++) {
                    Titan titan = Titan.generateTitan();
                    titan.setIndex(i);
                    titanKillQueue.enqueue(titan);
                    System.out.printf("Titan %d: %-80sRisk: %d\n", titan.getIndex(), titan, titan.getRisk());
                }
                noOfTitans += extraTitans;
            }

        }

        System.out.println("Total distance travelled: " + totalDistance);
        System.out.println("Leaving Paradis...");

    }

}

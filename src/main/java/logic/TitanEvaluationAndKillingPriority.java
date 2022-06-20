package logic;

import collections.PriorityQueue;
import collections.Queue;
import data_objects.GameCharacter;
import data_objects.NineTitan;
import data_objects.NormalTitan;
import data_objects.Titan;

import java.util.*;

public class TitanEvaluationAndKillingPriority {
    public static List<Integer> evaluateDangerRisk(List<Titan> titans){
        List<Integer> dangerRisks = new ArrayList<>();
        for (Titan titan : titans)
            dangerRisks.add(titan.getRisk());
        return dangerRisks;
    }

    public static PriorityQueue<Titan> createKillQueue(List<Titan> titans) {
        PriorityQueue<Titan> killQueue = new PriorityQueue<>();
        for (Titan titan : titans)
            killQueue.enqueue(titan);
        return killQueue;
    }

    public static String killPriority(PriorityQueue<Titan> killQueue) {
        StringBuilder killSequence = new StringBuilder();
        for (int i = 0; i < killQueue.getSize(); i++)
            killSequence.append("Titan ")
                        .append(killQueue.getElement(i).getIndex())
                        .append(" --> ");
        return killSequence.substring(0, killSequence.length() - 5);
    }

    public static int calculateDistance(PriorityQueue<Titan> killQueue) {
        int totalDistance = 0;
        int previousIndex = 0;
        for (int i = 0; i < killQueue.getSize(); i++) {
            int currentIndex = killQueue.getElement(i).getIndex();
            totalDistance += Math.abs(previousIndex - currentIndex);
            previousIndex = currentIndex;
        }
        return totalDistance;
    }

    public static void main(String[] args) {
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
        boolean beastTitanExists = false;

        System.out.print("Initial number of Titans: ");
        int noOfTitans = scanner.nextInt();

        System.out.println();
        System.out.printf("Generating %d Titans ...\n", noOfTitans);
        for (int i = 1; i <= noOfTitans; i++) {
            Titan titan = Titan.generateTitan();
            titan.setIndex(i);
            titanKillQueue.enqueue(titan);
            System.out.printf("Titan %d: %-70s\tRisk: %d\n", titan.getIndex(), titan, titan.getRisk());
        }

        while(!titanKillQueue.isEmpty()){
            System.out.println();
            System.out.println("Titans to be killed");
            for(int i = 0; i < titanKillQueue.getSize(); i++){
                System.out.printf("Titan %d: %-70s\tRisk: %d\n",
                        titanKillQueue.getElement(i).getIndex(),
                        titanKillQueue.getElement(i),
                        titanKillQueue.getElement(i).getRisk());
            }

            System.out.println();

            for(int i = 0; i < titanKillQueue.getSize(); i++){
                if(titanKillQueue.getElement(i).getRisk() < 19) break;
                if(titanKillQueue.getElement(i).equals(new NineTitan("Nine Titan (Beast Titan)"))){
                    beastTitanExists = true;
                    break;
                }
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
            titanKillQueue.dequeue();
            System.out.printf("Killed Titan %d", currentIndex);
            totalDistance += Math.abs(previousIndex - currentIndex);
            previousIndex = currentIndex;
            System.out.println();
            System.out.printf("Current distance travelled: %d", totalDistance);
            System.out.println();

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
                    System.out.printf("Titan %d: %-70s\tRisk: %d\n", titan.getIndex(), titan, titan.getRisk());
                }
                noOfTitans += extraTitans;
            }

        }

        System.out.println("Total distance travelled: " + totalDistance);
        System.out.println("Leaving Paradis...");

    }
}


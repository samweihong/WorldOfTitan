package logic;

import collections.PriorityQueue;
import data_objects.GameCharacter;
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
        int time = GameCharacterList.getGameCharacter(name)
        int ability = agility + strength;
        int time

        List<Titan> titanList = new ArrayList<>();
        List<Integer> dangerRiskList = new ArrayList<>();
        PriorityQueue<Titan> titanKillQueue = new PriorityQueue<>();

        boolean moreTitans = false;
        do{

            System.out.print("Number of Titans: ");
            int noOfTitans = scanner.nextInt();

            System.out.println();
            System.out.printf("Generating %d Titans ...\n", noOfTitans);
            for (int i = 1; i <= noOfTitans; i++) {
                Titan titan = Titan.generateTitan();
                titan.setIndex(i);
                titanList.add(titan);
                System.out.printf("Titan %d: %s\n", i, titan);
            }

            dangerRiskList = evaluateDangerRisk(titanList);
            System.out.println();
            System.out.println("Their respective danger risks: ");
            for (int i = 0; i < noOfTitans; i++) System.out.printf("Titan %d Risk: %d\n", (i+1), dangerRiskList.get(i));

            titanKillQueue = createKillQueue(titanList);
            int maxDangerRisk = titanKillQueue.getElement(0).getRisk();
            System.out.println();
            System.out.printf("%s's agility: %d\n%s's strength: %d\n%s's ability to kill titan: %d\n",
                    name, agility, name, strength, name, ability);
            System.out.println();

            if(maxDangerRisk > ability){
                System.out.println("ABORT MISSION! RUN FOR YOUR LIFE!!!");
                break;
            }
            else{
                System.out.println("Proceed with mission...");
                System.out.print("Sequence to be killed: ");
                System.out.println(killPriority(titanKillQueue));
                System.out.print("Total distance moved: ");
                System.out.println(calculateDistance(titanKillQueue));
            }

            System.out.print("Detected ");

        }while(moreTitans || );


    }
}


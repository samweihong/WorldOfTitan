package logic;

import collections.PriorityQueue;

import java.util.*;

public class TitanEvaluationAndKillingPriority{

    public static List<Integer> evaluateDangerRisk(List<Titan> titans){

        List<Integer> dangerRisks = new ArrayList<>();
        for(int i = 0; i < titans.size(); i++){

            Titan titan = titans.get(i);
            int risk = titan.evaluateRisk(titan.toString());
            dangerRisks.add(risk);

        }

        return dangerRisks;

    }

    public static collections.PriorityQueue<Titan> createKillQueue(List<Titan> titans){

        collections.PriorityQueue<Titan> killQueue = new collections.PriorityQueue<>();
        for(int i = 0; i < titans.size(); i++){
            killQueue.enqueue(titans.get(i));
        }
        return killQueue;

    }

    public static String killPriority(collections.PriorityQueue<Titan> killQueue){

        StringBuilder killSequence = new StringBuilder();
        for(int i = 0; i < killQueue.getSize(); i++){
            killSequence.append("logic.Titan ").append(killQueue.getElement(i).getIndex()).append(" --> ");
        }
        killSequence.delete(killSequence.length() - 4, killSequence.length());

        return killSequence.toString();

    }

    public static int calculateDistance(collections.PriorityQueue<Titan> killQueue){

        int totalDistance = 0;
        int currentNode = 0;
        for(int i = 0; i < killQueue.getSize(); i++){
            int distance = Math.abs(currentNode - killQueue.getElement(i).getIndex());
            totalDistance += distance;
            currentNode = killQueue.getElement(i).getIndex();
        }

        return totalDistance;

    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Number of Titans: ");
        int noOfTitans = scanner.nextInt();

        System.out.println();
        List<Integer> dangerRiskList = new ArrayList<>();
        List<Titan> titanList = new ArrayList<>();
        System.out.printf("Generating %d Titans ...\n", noOfTitans);
        for(int i = 1; i <= noOfTitans; i++){
            Titan titan = new Titan();
            titan.setIndex(i);
            titanList.add(titan);
            System.out.printf("logic.Titan %d: %s\n", i, titan);
            dangerRiskList.add(titan.evaluateRisk(titan.toString()));
        }

        System.out.println();
        System.out.println("Their respective danger risks: ");
        for(int i = 1; i <= noOfTitans; i++){
            System.out.printf("logic.Titan %d Risk: %d\n", i, dangerRiskList.get(i-1));
        }

        PriorityQueue<Titan> titanKillQueue = createKillQueue(titanList);
        System.out.println();
        System.out.print("Sequence to be killed: ");
        System.out.println(killPriority(titanKillQueue));

        System.out.print("Total distance moved: ");
        System.out.println(calculateDistance(titanKillQueue));

    }


}


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TitanEvaluationAndKillingPriority {

    public static List<Integer> evaluateDangerRisk(List<Titan> titans){

        List<Integer> dangerRisks = new ArrayList<>();
        for(int i = 0; i < titans.size(); i++){

            Titan titan = titans.get(i);
            int risk = titan.getRisk(titan.toString());
            dangerRisks.add(risk);

        }

        return dangerRisks;

    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Number of Titans: ");
        int noOfTitans = scanner.nextInt();

        System.out.println();
        List<Integer> dangerRiskList = new ArrayList<>();
        System.out.printf("Generating %d Titans ...\n", noOfTitans);
        for(int i = 1; i <= noOfTitans; i++){
            Titan titan = new Titan();
            System.out.printf("Titan %d: %s\n", i, titan);
            dangerRiskList.add(titan.getRisk(titan.toString()));
        }

        System.out.println();
        System.out.println("Their respective danger risks: ");
        for(int i = 1; i <= noOfTitans; i++){
            System.out.printf("Titan %d Risk: %d\n", i, dangerRiskList.get(i-1));
        }

        System.out.println();
        System.out.print("Sequence to be killed: ");
        System.out.print("Total distance moved: ");

    }

}
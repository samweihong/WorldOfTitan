import java.util.PriorityQueue;
import java.util.Scanner;
public class TitanEvaluationAndKillingPriorityTest {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Number of Titans: ");
        int noOfTitans = scanner.nextInt();

        while(noOfTitans-- > 0){
            String titan = TitanEvaluationAndKillingPriority.generateTitan();
            int titanDangerRisk = TitanEvaluationAndKillingPriority.evaluateDangerRisk(titan);
            System.out.println(titan);
            System.out.println(titanDangerRisk);
            System.out.println();
        }


    }

}

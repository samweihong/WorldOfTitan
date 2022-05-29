import java.util.ArrayList;
import java.util.List;

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

}
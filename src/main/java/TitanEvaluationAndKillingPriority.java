import java.util.Random;
public class TitanEvaluationAndKillingPriority {

    public static String generateTitan(){

        Random random = new Random();
        int titan = random.nextInt(1, 4);

        if(titan == 1){
            // Initializing string description for normal titan
            StringBuilder normalTitan = new StringBuilder("Normal Titan (");

            // Generate attributes of normal titan with Random class
            int height = random.nextInt(1, 31),
                legs = random.nextInt(5),
                speed = random.nextInt(1, 31);
            while(legs != 4 && legs != 2 && legs != 0) legs = random.nextInt(5);

            int walkingPattern = random.nextInt(1, 4);
            boolean canClimb = random.nextBoolean();

            // Completion of string description for normal titan
            normalTitan.append(height);
            normalTitan.append("m, ");

            normalTitan.append(legs);
            normalTitan.append(" legs, ");

            normalTitan.append(speed);
            normalTitan.append("ms, ");

            if(walkingPattern == 1) normalTitan.append("Not repeated");
            else if(walkingPattern == 2) normalTitan.append("Repeated");
            else if(walkingPattern == 3) normalTitan.append("Normal");
            normalTitan.append(" pattern, ");

            if(canClimb) normalTitan.append("Can");
            else normalTitan.append("Cannot");
            normalTitan.append(" climb)");

            return normalTitan.toString();

        }
        else if(titan == 2) return "Abnormal Titan";
        else return "Nine Titans";

    }

    public static int evaluateDangerRisk(String titan){

        String titanType = titan.split(" ")[0];
        if(titanType.equals("Normal")){
            // Normal Titan (25m, 4 legs, 15ms, Repeated pattern, Can climb)

            // Getting data from String input
            String[] attributes = titan.split(" ");
            int height = Integer.parseInt(attributes[2].substring(1, attributes[2].length() - 2));
            int legs = Integer.parseInt(attributes[3]);
            int speed = Integer.parseInt(attributes[5].substring(0, attributes[5].length() - 3));

            String walkingPattern;
            if(attributes.length == 11) walkingPattern = "Not repeated";
            else if(attributes[6].equalsIgnoreCase("Repeated")) walkingPattern = "Repeated";
            else walkingPattern = "Normal";

            boolean canClimb = true;
            if(attributes[attributes.length - 2].equals("Cannot")) canClimb = false;


            // Calculating danger risk based on collected data
            int risk = 0;

            if(height > 20) risk += 3;
            else if(height > 10) risk += 2;
            else risk++;

            if(legs == 4) risk += 3;
            else if(legs == 2) risk += 2;
            else if(legs == 0) risk++;

            if(speed > 20) risk += 3;
            else if(speed > 10) risk += 2;
            else risk++;

            if(walkingPattern.equals("Not repeated")) risk += 3;
            else if(walkingPattern.equals("Repeated")) risk += 2;
            else risk++;

            if(canClimb) risk += 3;
            else risk++;

            return risk;

        }
        else if(titanType.equals("Abnormal")) return 15;
        else return 19;

    }

}

package logic;

import java.util.Random;

public class Titan implements Comparable<Titan> {

    private String titan;
    private int risk;
    private int index;

    public Titan(String titan){ this.titan = titan; }
    // generate logic.Titan
    public Titan(){
        Random random = new Random();
        int titanType = random.nextInt(1, 4);

        if(titanType == 1){
            // Initializing string description for normal titan
            StringBuilder normalTitan = new StringBuilder("Normal logic.Titan (");

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

            titan = normalTitan.toString();

        }
        else if(titanType == 2) titan = "Abnormal logic.Titan";
        else {
            int nineTitans = random.nextInt(1, 10);
            if(nineTitans == 1) titan = "Founding logic.Titan";
            else if(nineTitans == 2) titan = "Armored logic.Titan";
            else if(nineTitans == 3) titan = "Attack logic.Titan";
            else if(nineTitans == 4) titan = "Beast logic.Titan";
            else if(nineTitans == 5) titan = "Cart logic.Titan";
            else if(nineTitans == 6) titan = "Colossus logic.Titan";
            else if(nineTitans == 7) titan = "Female logic.Titan";
            else if(nineTitans == 8) titan = "Jaw logic.Titan";
            else if(nineTitans == 9) titan = "War Hammer logic.Titan";
        }
    }

    public int evaluateRisk(String titan){
        String titanType = titan.split(" ")[0];
        if(titanType.equals("Abnormal")) risk = 15;
        else if(
            titanType.equals("Founding") ||
            titanType.equals("Armored") ||
            titanType.equals("Attack") ||
            titanType.equals("Beast") ||
            titanType.equals("Cart") ||
            titanType.equals("Colossus") ||
            titanType.equals("Female") ||
            titanType.equals("Jaw") ||
            titanType.equals("War")
        ) risk = 19;
        else if(titanType.equals("Normal")){
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
        return risk;
    }

    public int getRisk() { return risk; }
    public int getIndex() { return index; }
    public void setIndex(int index) { this.index = index; }

    @Override
    public String toString(){ return titan; }

    public static Titan toTitan(String titan){
        return new Titan(titan);
    }

    @Override
    public int compareTo(Titan o) {
        if(this.getRisk() > o.getRisk()) return -1;
        else if(this.getRisk() == o.getRisk()) return 0;
        else return 1;
    }
}

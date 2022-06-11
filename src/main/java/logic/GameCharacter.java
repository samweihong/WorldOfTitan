package logic;

public record GameCharacter(String name, int height, int weight, int strength, int agility, int intelligence, int coordination, int leadership) {
    @Override
    public String toString() {
        return "Name: " + name +
               "\nHeight: " + height + "cm" +
               "\nWeight: " + weight + "kg" +
               "\nStrength: " + strength +
               "\nAgility: " + agility +
               "\nIntelligence: " + intelligence +
               "\nCoordination: " + coordination +
               "\nLeadership: " + leadership +
               "\n";
    }
}

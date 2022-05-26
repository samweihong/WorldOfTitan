import java.util.Comparator;
import java.util.Scanner;
import java.util.Arrays;
import java.util.LinkedList;

public class Character {
    private String name;
    private int height;
    private int weight;
    private int strength;
    private int agility;
    private int intelligence;
    private int coordination;
    private int leadership;

    public Character(String name, int height, int weight, int strength, int agility, int intelligence, int coordination, int leadership) {
        this.height = height;
        this.weight = weight;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
        this.coordination = coordination;
        this.leadership = leadership;
        this.name=name;
    }

    public String getName() {return name;}

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getCoordination() {
        return coordination;
    }

    public int getLeadership() {
        return leadership;
    }

    @Override
    public String toString() {
        return "\nName: " + name + "\nHeight: " + height + "cm" + "\nWeight: " + weight + "kg" +
                "\nStrength: " + strength + "\nAgility: " + agility + "\nIntelligence: " + intelligence +
                "\nCoordination: " + coordination + "\nLeadership: " + leadership + "\n";
    }
}

class HeightComparator implements Comparator<Character> {
    public int compare(Character c1, Character c2) {
        return c2.getHeight() - c1.getHeight();
    }
}

class WeightComparator implements Comparator<Character> {
    public int compare(Character c1, Character c2) {
        return c2.getWeight() - c1.getWeight();
    }
}

class StrengthComparator implements Comparator<Character> {
    public int compare(Character c1, Character c2) {
        return c2.getStrength() - c1.getStrength();
    }
}

class AgilityComparator implements Comparator<Character> {
    public int compare(Character c1, Character c2) {
        return c2.getAgility() - c1.getAgility();
    }
}

class IntelligenceComparator implements Comparator<Character> {
    public int compare(Character c1, Character c2) {
        return c2.getIntelligence() - c1.getIntelligence();
    }
}

class CoordinationComparator implements Comparator<Character> {
    public int compare(Character c1, Character c2) {
        return c2.getCoordination() - c1.getCoordination();
    }
}

class LeadershipComparator implements Comparator<Character> {
    public int compare(Character c1, Character c2) {
        return c2.getLeadership() - c1.getLeadership();
    }
}



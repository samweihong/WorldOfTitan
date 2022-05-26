import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static FileHandling fileHandling = new FileHandling();

    public static void main(String args[]) {
//        System.out.println(storeCharacterInformation());
//        storeInLinkedList();
//        System.out.println(loadCharacterInformation());
//        System.out.println(sortingAttribute());
    }

    public static Character loadCharacterInformation() {
        Character[] characters = fileHandling.readFromFile("myjson.json");
        System.out.print("Enter name: " );
        String name = scanner.nextLine();
        for(Character character : characters)
            if (character.getName().equalsIgnoreCase(name)) return character;
        return null;
    }

    public static Character storeCharacterInformation() {
        Character[] characters = fileHandling.readFromFile("myjson.json");
        System.out.print("Enter name: " );
        String name = scanner.nextLine();
        System.out.print("Enter characteristics: ");
        String characteristics = scanner.nextLine();
        String[] strArray = characteristics.split(" ");
        int[] intArray = new int[strArray.length];
        for(int i = 0; i < intArray.length; i++) intArray[i] = Integer.parseInt(strArray[i]);
        Character character = new Character(name, intArray[0], intArray[1], intArray[2], intArray[3], intArray[4], intArray[5], intArray[6]);
        int l = characters.length;
        boolean found = false;
        for(int i = 0; i < l; i++) {
            if (characters[i].getName().equalsIgnoreCase(character.getName())) {
                found = true;
                break;
            }
        }
        if (found == false) {
            Character[] characters1 = new Character[l + 1];
            for(int i = 0; i < l; i++) characters1[i] = characters[i];
            characters1[l] = character;
            fileHandling.writeInFile(characters1, "myjson.json");
            return character;
        }
        return character;
    }

    public static void storeInLinkedList() {
        Character[] characters = fileHandling.readFromFile("myjson.json");
        LinkedList<Character> list = new LinkedList<>();
        Collections.addAll(list, characters);
    }

    public static String sortingAttribute() {
        Character[] characters = fileHandling.readFromFile("myjson.json");
        System.out.print("Sorting attribute: ");
        String input = scanner.nextLine();
        String str = "";
        System.out.println();
        switch (input) {
            case ("Height"):
                Arrays.sort(characters, new HeightComparator());
                for(Character character : characters) str += character.getName() + " " + character.getHeight() + "\n" ;
                break;
            case ("Weight"):
                Arrays.sort(characters, new WeightComparator());
                for(Character character : characters) str += character.getName() + " " + character.getWeight() + "\n";
                break;
            case ("Strength"):
                Arrays.sort(characters, new StrengthComparator());
                for(Character character : characters) str += character.getName() + " " + character.getStrength() + "\n";
                break;
            case ("Agility"):
                Arrays.sort(characters, new AgilityComparator());
                for(Character character : characters) str += character.getName() + " " + character.getAgility() + "\n";
                break;
            case ("Intelligence"):
                Arrays.sort(characters, new IntelligenceComparator());
                for(Character character : characters) str += character.getName() + " " + character.getIntelligence() + "\n";
                break;
            case ("Coordination"):
                Arrays.sort(characters, new CoordinationComparator());
                for(Character character : characters) str += character.getName() + " " + character.getCoordination() + "\n";
                break;
            case ("Leadership"):
                Arrays.sort(characters, new LeadershipComparator());
                for(Character character : characters) str += character.getName() + " " + character.getLeadership() + "\n";
                break;
            default:
                str += "Invalid Attribute";
        }
        return str;
    }
}

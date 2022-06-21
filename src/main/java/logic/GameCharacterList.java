package logic;

import collections.LinkedList;
import com.fasterxml.jackson.databind.ObjectMapper;
import data_objects.GameCharacter;
import data_objects.MapOfParadis;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameCharacterList {
    private static final LinkedList<GameCharacter> gameCharacterList = new LinkedList<>();
    private static final String DEFAULT_PATH = "src/main/resources/data/game_characters.json";

    static {
        readFromFile("src/main/resources/data/game_characters.json");
    }

    private GameCharacterList() {}

    public static LinkedList<GameCharacter> getGameCharacterList() {
        return gameCharacterList;
    }

    public static void clear() {
        gameCharacterList.clear();
    }

    public static void writeToFile() {
        writeToFile(DEFAULT_PATH);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void writeToFile(String path) {
        try {
            File file = new File(path);
            file.createNewFile();
            GameCharacter[] charactersArray = gameCharacterList.toArray(new GameCharacter[0]);
            new ObjectMapper().writeValue(new File(path), charactersArray);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readFromFile() {
        readFromFile(DEFAULT_PATH);
    }

    public static void readFromFile(String path) {
        try {
            GameCharacter[] characters = new ObjectMapper().readValue(new File(path), GameCharacter[].class);
            gameCharacterList.clear();
            for (GameCharacter character : characters)
                gameCharacterList.add(character);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static GameCharacter addGameCharacter(String name, int[] characteristics) {
        GameCharacter character = new GameCharacter(name, characteristics[0], characteristics[1], characteristics[2], characteristics[3], characteristics[4], characteristics[5], characteristics[6]);
        for (int i = 0; i < gameCharacterList.getSize(); i++) {
            if (gameCharacterList.get(i).name().equalsIgnoreCase(name)) {
                gameCharacterList.set(i, character);
                return character;
            }
        }
        gameCharacterList.add(character);
        return character;
    }

    public static GameCharacter getGameCharacter(String name) {
        for (GameCharacter gameCharacter : gameCharacterList)
            if (gameCharacter.name().equalsIgnoreCase(name))
                return gameCharacter;
        return null;
    }

    public static String getAttributeList(String attribute) {
        StringBuilder str = new StringBuilder();
        try {
            Method attributeGetter = GameCharacter.class.getMethod(attribute.toLowerCase());
            for (GameCharacter gameCharacter : gameCharacterList) {
                str.append(gameCharacter.name())
                   .append(" ")
                   .append(attributeGetter.invoke(gameCharacter)).append("\n");
            }
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return str.toString();
    }

    public static void sort(String attribute) {
        gameCharacterList.sort(
            Comparator.comparing((o) -> {
                try {
                    Method attributeGetter = GameCharacter.class.getMethod(attribute.toLowerCase());
                    return (Integer) attributeGetter.invoke(o);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }, Comparator.reverseOrder())
            .thenComparing((o) -> ((GameCharacter) o).name())
        );
    }

    public static LinkedList<GameCharacter> search(String attribute, int value) {
        sort(attribute);
        return gameCharacterList.binarySearchList(attribute, value);
    }

    public static String getSearchList(String attribute, int value) {
        return formatSearchList(search(attribute, value));
    }

    private static String formatSearchList(LinkedList<GameCharacter> list) {
        if (list.isEmpty()) return "None";
        if (list.getSize() == 1) return list.get(0).name();

        StringBuilder res = new StringBuilder();
        res.append(list.get(0).name());
        for (int i = 1; i < list.getSize()-1; i++)
            res.append(", ").append(list.get(i).name());
        res.append(" and ").append(list.get(list.getSize() - 1).name());
        return res.toString();
    }

    public static void main(String[] args) {
        gameCharacterList.clear();
        gameCharacterIO();
        sort();
        search();
    }

    private static void gameCharacterIO() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.println("\nHeight Weight Strength Agility Intelligence Coordination Leadership");
        System.out.print("Enter characteristics as in above format: ");
        int[] characteristics = Arrays.stream(scanner.nextLine().split(" "))
                                    .mapToInt(Integer::parseInt)
                                    .toArray();
        addGameCharacter(name, characteristics);
        System.out.println();
        System.out.println(gameCharacterList.get(0));
        System.out.println("This character is added!\n");
    }

    public static void startGameCharacterIO() {
        String menu = """
                0. Quit
                1. Add new character
                2. View all character names
                3. Get character information
                4. Save characters
                5. Load characters
                6. Clear all characters
                """;
        Scanner scanner = new Scanner(System.in);

        for (int choice = -1; choice != 0; ) {
            System.out.println(menu);

            while (true) {
                System.out.print("Enter your choice: ");
                try {
                    choice = scanner.nextInt();
                    if (choice < 0 || choice > 6) throw new IllegalArgumentException();
                    break;
                } catch (InputMismatchException | IllegalArgumentException e) {
                    System.out.println("Invalid input! Please enter again.\n");
                    scanner = new Scanner(System.in);
                }
            }
            System.out.println();

            scanner = new Scanner(System.in);
            switch (choice) {
                case 1 -> gameCharacterIO();
                case 2 -> getGameCharacterNames();
                case 3 -> {
                    System.out.print("Enter the character's name : ");
                    String name = scanner.nextLine();
                    GameCharacter gameCharacter = getGameCharacter(name);
                    System.out.println();
                    System.out.println(gameCharacter == null ? "This character doesn't exist!" : gameCharacter);
                }
                case 4 -> {
                    System.out.print("Enter the filename to save : ");
                    String filename = scanner.nextLine();
                    String path = System.getProperty("user.home") + "/" + filename + ".json";
                    writeToFile(path);
                    System.out.println("The characters are saved in " + path + "!");
                }
                case 5 -> {
                    System.out.print("Enter the filename to load : ");
                    String filename = scanner.nextLine();
                    String path = System.getProperty("user.home") + "/" + filename + ".json";
                    readFromFile(path);
                    System.out.println("The characters are loaded from " + path + "!");
                }
                case 6 -> {
                    clear();
                    System.out.println("All characters have been cleared.");
                }
            }
            System.out.println();
        }
    }

    private static void getGameCharacterNames() {
        if (gameCharacterList.isEmpty()) {
            System.out.println("The character list is empty!");
            return;
        }
        int i = 1;
        for (GameCharacter character : gameCharacterList)
            System.out.printf("%2d. %s\n", i++, character.name());
    }

    public static void startSortAndSearch() {
        System.out.println("""
                1. Soldiers Arrangement
                2. Soldiers Grouping
                """);
        Scanner scanner = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.print("Enter your choice: ");
            try {
                choice = scanner.nextInt();
                if (choice < 1 || choice > 2) throw new IllegalArgumentException();
                break;
            } catch (InputMismatchException | IllegalArgumentException e) {
                System.out.println("Invalid input! Please enter again.\n");
                scanner = new Scanner(System.in);
            }
        }
        System.out.println();
        if (choice == 1) sort();
        if (choice == 2) search();
    }

    private static void sort() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Sorting attribute: ");
        String attribute = scanner.nextLine();
        sort(attribute);
        System.out.println();
        System.out.println(getAttributeList(attribute));
    }

    private static void search() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Finding ability : ");
        String attribute = scanner.nextLine();
        System.out.print("With value of : ");
        int value = scanner.nextInt();
        System.out.println();
        System.out.println("Soldier : " + getSearchList(attribute, value));
        System.out.println();
    }
}

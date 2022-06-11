package logic;

import collections.LinkedList;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
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
        System.out.print("Enter characteristics: ");
        int[] characteristics = Arrays.stream(scanner.nextLine().split(" "))
                                    .mapToInt(Integer::parseInt)
                                    .toArray();
        addGameCharacter(name, characteristics);
        System.out.println();
        System.out.println(gameCharacterList.get(0));
    }

    private static void sort() {
        readFromFile();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Sorting attribute: ");
        String attribute = scanner.nextLine();
        sort(attribute);
        System.out.println();
        System.out.println(getAttributeList(attribute));
    }

    private static void search() {
        readFromFile();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Finding ability : ");
        String attribute = scanner.nextLine();
        System.out.println();
        System.out.print("value: ");
        int value = scanner.nextInt();
        System.out.println();
        System.out.println("Soldier : " + getSearchList(attribute, value));
    }
}

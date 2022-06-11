package logic;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GameCharacterList {
    private static final LinkedList<GameCharacter> gameCharacterList = new LinkedList<>();

    static {
        readFromFile("src/main/resources/data/game_characters.json");
    }

    private GameCharacterList() {}

    public static LinkedList<GameCharacter> getGameCharacterList() {
        return gameCharacterList;
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

    public static GameCharacter getGameCharacter(String name) {
        for (GameCharacter gameCharacter : gameCharacterList)
            if (gameCharacter.name().equalsIgnoreCase(name))
                return gameCharacter;
        return null;
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

    public static void search(String attribute, int value) {
        sort(attribute);
//        gameCharacterList.binarySearchList(attribute, value);
    }
}

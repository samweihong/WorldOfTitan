package logic;

import java.io.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CharacterList {
    private static final LinkedList<GameCharacter> characterList = new LinkedList<>();

    private CharacterList() {}

    public static LinkedList<GameCharacter> getCharacterList() {
        return characterList;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void writeToFile(String path) {
        try {
            File file = new File(path);
            file.createNewFile();
            GameCharacter[] charactersArray = characterList.toArray(new GameCharacter[0]);
            new ObjectMapper().writeValue(new File(path), charactersArray);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readFromFile(String path) {
        try {
            GameCharacter[] characters = new ObjectMapper().readValue(new File(path), GameCharacter[].class);
            characterList.clear();
            for (GameCharacter character : characters)
                characterList.add(character);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package logic;

import logic.GameCharacter;
import logic.GameCharacterList;

import collections.LinkedList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class CharacterListTest {
    private static final String PATH = "src/test/resources/test-data/test-character-list.json";

    @Test
    void testWriteToFile() {
        GameCharacter gameCharacter1 = new GameCharacter("Test1", 185, 95, 9, 7, 7, 10, 8);
        GameCharacter gameCharacter2 = new GameCharacter("Test2", 100, 10, 1, 2, 3, 4, 5);
        GameCharacterList.clear();
        GameCharacterList.getGameCharacterList().add(gameCharacter1);
        GameCharacterList.getGameCharacterList().add(gameCharacter2);
        GameCharacterList.writeToFile(PATH);
    }

    @Test
    void readFromFile() {
        GameCharacterList.readFromFile(PATH);
        LinkedList<GameCharacter> characterList = GameCharacterList.getGameCharacterList();
        assertEquals("Test1", characterList.get(0).name());
        assertEquals("Test2", characterList.get(1).name());
        assertEquals(95, characterList.get(0).weight());
        assertEquals(2, characterList.get(1).agility());
    }

    @AfterAll
    static void deleteFile() {
        File file = new File(PATH);
        assertTrue(file.delete());
    }
}

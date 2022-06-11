package logic;

import collections.LinkedList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class GameCharacterListTest {
    private static final String TEST_PATH = "src/test/resources/test-data/test-character-list.json";
    private static final String DATA_PATH = "src/test/resources/test-data/game_characters.json";

    @BeforeEach
    void clear() {
        GameCharacterList.clear();
    }

    @Test
    void testWriteAndRead() {
        GameCharacterList.addGameCharacter("Test1", new int[]{185, 95, 9, 7, 7, 10, 8});
        GameCharacterList.addGameCharacter("Test2", new int[]{100, 10, 1, 2, 3, 4, 5});
        GameCharacterList.addGameCharacter("Test2", new int[]{100, 100, 10, 10, 10, 10, 10});
        GameCharacterList.writeToFile(TEST_PATH);
        GameCharacterList.clear();

        GameCharacterList.readFromFile(TEST_PATH);
        LinkedList<GameCharacter> characterList = GameCharacterList.getGameCharacterList();
        assertEquals("Test1", characterList.get(0).name());
        assertEquals("Test2", characterList.get(1).name());
        assertEquals(185, characterList.get(0).height());
        assertEquals(95, characterList.get(0).weight());
        assertEquals(10, characterList.get(1).agility());
        assertEquals(10, characterList.get(1).intelligence());
    }

    @Test
    void testSort() {
        GameCharacterList.readFromFile(DATA_PATH);
        GameCharacterList.sort("Strength");
        assertEquals("""
                     Levi Ackerman 12
                     Annie Leonhart 10
                     Mikasa Ackerman 10
                     Bertholdt Hoover 9
                     Eren Yeager 9
                     Hange Zoe 9
                     Jean Kristein 9
                     Reiner Braun 9
                     Erwin Smith 8
                     Connie Springer 6
                     Sasha Blouse 6
                     Historia Reiss 4
                     Armin Arlert 2
                     """, GameCharacterList.getAttributeList("Strength"));
    }

    @Test
    void testGetSearchList() {
        GameCharacterList.readFromFile(DATA_PATH);
        assertEquals("Annie Leonhart and Mikasa Ackerman", GameCharacterList.getSearchList("Strength", 10));
        assertEquals("Annie Leonhart, Connie Springer and Reiner Braun", GameCharacterList.getSearchList("Agility", 7));
        assertEquals("Annie Leonhart, Historia Reiss, Jean Kristein, Levi Ackerman and Reiner Braun", GameCharacterList.getSearchList("Intelligence", 7));
    }

    @AfterAll
    static void deleteFile() {
        File file = new File(TEST_PATH);
        assertTrue(file.delete());
    }
}
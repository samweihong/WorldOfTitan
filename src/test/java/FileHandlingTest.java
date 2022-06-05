import logic.GameCharacter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import logic.FileHandling;
import logic.LinkedList;

class FileHandlingTest {

    FileHandling fileHandling = new FileHandling();

    @Test
    void writeInFile() {
        GameCharacter cha = new GameCharacter("Test", 185, 95, 9, 7, 7, 10, 8);
        LinkedList<GameCharacter> test = new LinkedList<>();
        test.add(cha);
        fileHandling.writeInFile(test,"testFile.json");
    }

    @Test
    void readFromFile() {
        assertEquals("Test",fileHandling.readFromFile("testFile.json"));
    }
}
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlingTest {

    FileHandling fileHandling = new FileHandling();

    @Test
    void writeInFile() {
        Character cha = new Character("Test", 185, 95, 9, 7, 7, 10, 8);
        Character test[] = new Character[1];
        test[0] = cha;
        fileHandling.writeInFile(test,"testFile.json");
    }

    @Test
    void readFromFile() {
        assertEquals("Test",fileHandling.readFromFile("testFile.json"));
    }
}
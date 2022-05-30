import com.google.gson.Gson;
import java.io.*;

public class FileHandling {

    static LinkedList<GameCharacter> characters = new LinkedList<>();

    public static LinkedList<GameCharacter> getCharacterList() {
        return readFromFile("myjson.json");
    }

    public static void writeInFile(LinkedList<GameCharacter> characters, String path) {
        File file = new File(path);
        try ( final FileWriter fileWriter = new FileWriter(file) ) {
            new Gson().toJson(characters, fileWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static LinkedList<GameCharacter> readFromFile(String path) {
        final File file = new File(path);
        try ( final FileReader fileReader = new FileReader(file) ) {
            final GameCharacter[] character = new Gson().fromJson(fileReader, GameCharacter[].class);
            for(GameCharacter cha : character) characters.add(cha);
            return characters;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

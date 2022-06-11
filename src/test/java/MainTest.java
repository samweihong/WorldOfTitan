import com.fasterxml.jackson.databind.ObjectMapper;
import logic.CharacterList;
import logic.GameCharacter;
import logic.LinkedList;
import logic.Main;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testHelloWorld() {
        assertEquals("Hello World!", Main.helloWorld());
    }

    @Test
    void name() {
//        java.util.LinkedList<String> list = new java.util.LinkedList<>();
        LinkedList<String> list = new LinkedList<>();
        list.add("abc");
        list.add("1");
        String[] arr = list.toArray(new String[list.getSize()]);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    void test() {
        ObjectMapper mapper = new ObjectMapper();
//        GameCharacter character = new GameCharacter("Hange Zo\u00EB", 160, 65, 12, 12, 7, 8, 8);
//        System.out.println(character.name());
//        System.out.println(character.weight());
//        ArrayList<GameCharacter> list = new ArrayList<>();
//        System.out.println(character);
        try {
//            list.add(character);
//            list.add(character);
//            String serial = mapper.writeValueAsString(character);
//            System.out.println(serial);
//            mapper.writeValue(new File("user.json"), list);
            GameCharacter[] gameCharacter = mapper.readValue(new File("myjson.json"), GameCharacter[].class);
            System.out.println(Arrays.toString(gameCharacter));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}

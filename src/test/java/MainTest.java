import logic.Main;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    GameCharacter character = new GameCharacter("Levi Ackerman", 160, 65, 12, 12, 7, 8, 8);

    @Test
    void testStoreCharacterInformation() {
        assertEquals("Name: " + character.getName() +
                "\nHeight: " + character.getHeight() + "cm" +
                "\nWeight: " + character.getWeight() + "kg" +
                "\nStrength: " + character.getStrength() +
                "\nAgility: " + character.getAgility() +
                "\nIntelligence: " + character.getIntelligence() +
                "\nCoordination: " + character.getCoordination() +
                "\nLeadership: " + character.getLeadership(), Main.storeGameCharacterInformation());
    }
//
//    @Test
//    void testLoadCharacterInformation() {
//        assertEquals("Name: " + character.getName() +
//                "\nHeight: " + character.getHeight() + "cm" +
//                "\nWeight: " + character.getWeight() + "kg" +
//                "\nStrength: " + character.getStrength() +
//                "\nAgility: " + character.getAgility() +
//                "\nIntelligence: " + character.getIntelligence() +
//                "\nCoordination: " + character.getCoordination() +
//                "\nLeadership: " + character.getLeadership(), Main.LoadGameCharacterInformation());
//    }

    @Test
    void testReadInput() {
        assertEquals("Levi Ackerman", Main.readInput());
    }

    @Test
    void testSortingAttribute() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        assertEquals("""
                     Levi Ackerman 12
                     Annie Leonhart 10
                     Mikasa Ackerman 10
                     Reiner Braun 9
                     Bertholdt Hoover 9
                     Jean Kristein 9
                     Eren Yeager 9
                     Hange Zoë 9
                     Erwin Smith 8
                     Sasha Blouse 6
                     Connie Springer 6
                     Historia Reiss 4
                     Armin Arlert 2
                     """, Main.sortingAttribute());
    }
}

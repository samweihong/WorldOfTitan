import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    Character character = new Character("Levi Ackerman", 160, 65, 12, 12, 7, 8, 8);

    @Test
    void testStoreCharacterInformation() {
        assertEquals("Name: " + character.getName() +
                "\nHeight: " + character.getHeight() + "cm" +
                "\nWeight: " + character.getWeight() + "kg" +
                "\nStrength: " + character.getStrength() +
                "\nAgility: " + character.getAgility() +
                "\nIntelligence: " + character.getIntelligence() +
                "\nCoordination: " + character.getCoordination() +
                "\nLeadership: " + character.getLeadership(), Main.storeCharacterInformation());
    }

    @Test
    void testLoadingCharacterInformation() {
        assertEquals("Name: " + character.getName() +
                "\nHeight: " + character.getHeight() + "cm" +
                "\nWeight: " + character.getWeight() + "kg" +
                "\nStrength: " + character.getStrength() +
                "\nAgility: " + character.getAgility() +
                "\nIntelligence: " + character.getIntelligence() +
                "\nCoordination: " + character.getCoordination() +
                "\nLeadership: " + character.getLeadership(), Main.loadCharacterInformation());
    }

    @Test
    void testSortingAttribute() {
        assertEquals("Levi Ackerman 12" +
                "\nAnnie Leonhart 10" +
                "\nMikasa Ackerman 10" +
                "\nReiner Braun 9" +
                "\nBertholdt Hoover 9" +
                "\nJean Kristein 9" +
                "\nEren Yeager 9" +
                "\nHange Zoë 9" +
                "\nErwin Smith 8" +
                "\nSasha Blouse 6" +
                "\nConnie Springer 6" +
                "\nHistoria Reiss 4" +
                "\nArmin Arlert 2", Main.sortingAttribute());
    }
}

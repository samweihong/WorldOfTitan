import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {

    Character character;

    public CharacterTest() {
        this.character = new Character("abc",1,2,3,4,5,6,7);
    }

    @Test
    void getHeight() {
        assertEquals(1,character.getHeight());
    }

    @Test
    void getWeight() {
        assertEquals(2,character.getWeight());
    }

    @Test
    void getStrength() {
        assertEquals(3,character.getStrength());
    }

    @Test
    void getAgility() {
        assertEquals(4,character.getAgility());
    }

    @Test
    void getIntelligence() {
        assertEquals(5,character.getIntelligence());
    }

    @Test
    void getCoordination() {
        assertEquals(6,character.getCoordination());
    }

    @Test
    void getLeadership() {
        assertEquals(7,character.getLeadership());
    }
}
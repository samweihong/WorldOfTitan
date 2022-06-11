package logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameCharacterListTest {

    @Test
    void testSort() {
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
        assertEquals("Annie Leonhart and Mikasa Ackerman", GameCharacterList.getSearchList("Strength", 10));
        assertEquals("Annie Leonhart, Connie Springer and Reiner Braun", GameCharacterList.getSearchList("Agility", 7));
        assertEquals("Annie Leonhart, Historia Reiss, Jean Kristein, Levi Ackerman and Reiner Braun", GameCharacterList.getSearchList("Intelligence", 7));
    }
}
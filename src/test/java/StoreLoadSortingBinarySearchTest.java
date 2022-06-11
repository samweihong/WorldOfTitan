import logic.GameCharacter;
import logic.StoreLoadSortingBinarySearch;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import static org.junit.jupiter.api.Assertions.*;

public class StoreLoadSortingBinarySearchTest {

    GameCharacter character = new GameCharacter("Levi Ackerman", 160, 65, 12, 12, 7, 8, 8);

//    @Test
//    void testStoreCharacterInformation() {
//        assertEquals("Name: " + character.name() +
//                "\nHeight: " + character.height() + "cm" +
//                "\nWeight: " + character.weight() + "kg" +
//                "\nStrength: " + character.strength() +
//                "\nAgility: " + character.agility() +
//                "\nIntelligence: " + character.intelligence() +
//                "\nCoordination: " + character.coordination() +
//                "\nLeadership: " + character.leadership(), logic.StoreLoadSortingBinarySearch.storeGameCharacterInformation(StoreLoadSortingBinarySearch.readInput(), StoreLoadSortingBinarySearch.readCharacteristicsInput()));
//    }
//
//    @Test
//    void testLoadCharacterInformation() {
//        assertEquals("Name: " + character.name() +
//                "\nHeight: " + character.height() + "cm" +
//                "\nWeight: " + character.weight() + "kg" +
//                "\nStrength: " + character.strength() +
//                "\nAgility: " + character.agility() +
//                "\nIntelligence: " + character.intelligence() +
//                "\nCoordination: " + character.coordination() +
//                "\nLeadership: " + character.leadership(), logic.StoreLoadSortingBinarySearch.loadGameCharacterInformation(StoreLoadSortingBinarySearch.readInput()));
//    }
//
//    @Test
//    void testReadInput() {
//        assertEquals("Levi Ackerman", logic.StoreLoadSortingBinarySearch.readInput());
//    }
//
//    @Test
//    void testBinarySearch() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        assertEquals("Levi Ackerman, Historia Reiss, Jean Kristein, Annie Leonhart, Reiner Bruan", logic.StoreLoadSortingBinarySearch.binarySearch(StoreLoadSortingBinarySearch.readInput(), Integer.parseInt(StoreLoadSortingBinarySearch.readInput())));
//    }
//
//    @Test
//    void testSortingAttribute() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        assertEquals("""
//                     Levi Ackerman 12
//                     Annie Leonhart 10
//                     Mikasa Ackerman 10
//                     Reiner Braun 9
//                     Bertholdt Hoover 9
//                     Jean Kristein 9
//                     Eren Yeager 9
//                     Hange Zoë 9
//                     Erwin Smith 8
//                     Sasha Blouse 6
//                     Connie Springer 6
//                     Historia Reiss 4
//                     Armin Arlert 2
//                     """, logic.StoreLoadSortingBinarySearch.sortingAttribute(StoreLoadSortingBinarySearch.readInput()));
//    }
}
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {

    @Test
    void get() {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1, 20);
        map.put(5, 50);
        map.put(9, 40);
        System.out.println(map);
        assertEquals(20, map.get(1));
        assertEquals(50, map.get(5));
        assertEquals(40, map.get(9));
    }

    @Test
    void getOrDefault() {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(5, null);
        assertNull(map.getOrDefault(5, 0));
    }
} 

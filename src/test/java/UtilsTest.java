import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {
    @Test
    void testFormatList() {
        assertEquals("A", Utils.formatList(List.of('A')));
        assertEquals("A and B", Utils.formatList(Arrays.asList('A', 'B')));
        assertEquals("A, B and C", Utils.formatList(Arrays.asList('A', 'B', 'C')));
        assertEquals("A, B, C and D", Utils.formatList(Arrays.asList('A', 'B', 'C', 'D')));
    }
}

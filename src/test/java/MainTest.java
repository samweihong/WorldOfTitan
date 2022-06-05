import logic.Main;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testHelloWorld() {
        assertEquals("Hello World!", Main.helloWorld());
    }
}

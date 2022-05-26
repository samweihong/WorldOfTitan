import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WordConverterTest {
    @Test
    void testConvertWord() {
        assertEquals("find attack titan", WordConverter.convertWord("oh(ldchc$ebdccd$rl)"));
        assertEquals("destroy Wall of Maria, Rose and Sheena", WordConverter.convertWord("rsgc(qqd^i$tkz)$ko$^udzhd,(rld$sgk^z$)$^gpssld"));
        assertEquals("most dangerous soldier is levi ackerman", WordConverter.convertWord("ukgc$rd(vsq$gh$zshrqkg$gwkzsml)h$dbeszudl"));
    }
}

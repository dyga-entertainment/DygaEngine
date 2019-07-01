import com.dyga.Engine.Source.Main.Game;
import com.dyga.Engine.Source.Utils.Images;
import com.dyga.Engine.Source.Utils.Tuple;
import org.junit.Test;

import java.awt.image.BufferedImage;

import static junit.framework.Assert.*;

public class TestGame {

    @Test
    public void testTuple() {
        Tuple tuple = new Tuple(5, 5);

        assertNotNull(tuple);
        assertEquals(tuple.first, 5);
        assertEquals(tuple.second, 5);
    }

}

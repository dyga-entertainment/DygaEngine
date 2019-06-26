import com.dyga.Engine.Source.Main.Game;
import org.junit.Test;

import static junit.framework.Assert.*;

public class TestGame {

    @Test
    public void testGameStatesInit() {
        Game game = new Game("testGame", 60, true);
        assertFalse(game.running);
        assertFalse(game.gameOver);
    }

}

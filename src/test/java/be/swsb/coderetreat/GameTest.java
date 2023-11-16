package be.swsb.coderetreat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GameTest {
    @Test
    void canMakeGameWithTwoPlayers() throws Exception {
        Game game = new Game(new Player("Mauro"), new Player("GeenKans"));

        String expectedOceanPlayer = """
                🌊🌊🌊🌊🌊🌊🌊🛳️🛳️🛳️
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🛳️🛳️🛳️🛳️🛳️🌊🌊🌊🛳️🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🛳️🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🛳️🌊
                🌊🌊🌊🌊🌊🛳️🛳️🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🛳️🛳️🛳️🛳️🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                """;

        String expectedOceanOpponent = """
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🛳️🛳️🛳️🛳️🛳️🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🛳️🛳️🛳️🌊🌊🛳️🌊
                🌊🌊🌊🌊🌊🛳️🛳️🌊🛳️🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🛳️🛳️🛳️🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                """;

        game.getPlayer().placeShip(new Position(7,0), Direction.HORIZONTAL, 3)
                .placeShip(new Position(0,2), Direction.HORIZONTAL, 5)
                .placeShip(new Position(8,2), Direction.VERTICAL, 3)
                .placeShip(new Position(5,5), Direction.HORIZONTAL, 2)
                .placeShip(new Position(4,7), Direction.HORIZONTAL, 4);

        game.getOpponent().placeShip(new Position(3,4), Direction.HORIZONTAL, 3)
                .placeShip(new Position(2,1), Direction.HORIZONTAL, 5)
                .placeShip(new Position(8,4), Direction.VERTICAL, 2)
                .placeShip(new Position(5,5), Direction.HORIZONTAL, 2)
                .placeShip(new Position(4,8), Direction.HORIZONTAL, 3);

        assertEquals(game.getPlayer().renderBoard(), expectedOceanPlayer);
        assertEquals(game.getOpponent().renderBoard(), expectedOceanOpponent);
    }

    @Test
    void canPlayWholeGame() throws Exception {
        Game game = new Game(new Player("Mauro"), new Player("GeenKans"));

        // Place Ships
        game.getPlayer().placeShip(new Position(7,0), Direction.HORIZONTAL, 3)
                .placeShip(new Position(0,2), Direction.HORIZONTAL, 5)
                .placeShip(new Position(8,2), Direction.VERTICAL, 3)
                .placeShip(new Position(5,5), Direction.HORIZONTAL, 2)
                .placeShip(new Position(4,7), Direction.HORIZONTAL, 4);

        game.getOpponent().placeShip(new Position(3,4), Direction.HORIZONTAL, 3)
                .placeShip(new Position(2,1), Direction.HORIZONTAL, 5)
                .placeShip(new Position(8,4), Direction.VERTICAL, 2)
                .placeShip(new Position(5,5), Direction.HORIZONTAL, 2)
                .placeShip(new Position(4,8), Direction.HORIZONTAL, 3);

        // Fire shots
        game.fireShot(new Position(2,1)).fireShot(new Position(3,1))
                .fireShot(new Position(3,1)).fireShot(new Position(4,1))
                .fireShot(new Position(4,1)).fireShot(new Position(6,1))
                .fireShot(new Position(5,1)).fireShot(new Position(2,3))
                .fireShot(new Position(6,1)).fireShot(new Position(2,1))

                .fireShot(new Position(3,4)).fireShot(new Position(1,1))
                .fireShot(new Position(4,4)).fireShot(new Position(2,5))
                .fireShot(new Position(5,4)).fireShot(new Position(5,3));

        assertEquals("No player won yet", game.getWinner());

        game.fireShot(new Position(5,5)).fireShot(new Position(1,7))
                .fireShot(new Position(6,5)).fireShot(new Position(1,8))

                .fireShot(new Position(8,4)).fireShot(new Position(5,1))
                .fireShot(new Position(8,5)).fireShot(new Position(5,2))

                .fireShot(new Position(4,8)).fireShot(new Position(9,9))
                .fireShot(new Position(5,8)).fireShot(new Position(8,9))
                .fireShot(new Position(6,8)).fireShot(new Position(7,9));

        // Check Winner
        assertEquals("Mauro", game.getWinner());
        assertNotEquals("GeenKans", game.getWinner());


    }
}

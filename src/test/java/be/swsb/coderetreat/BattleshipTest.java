package be.swsb.coderetreat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BattleshipTest {

    @Test
    void gameInitiallyRendersEmptyOcean() {
        String expectedOcean = """
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                """;
        Game game = new Game();
        assertEquals(expectedOcean, game.render());
    }

    @Test
    void boatOf1CellIsPlacedOnPosition() throws Exception {
        String expectedOcean = """
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🛳️🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                """;
        Game game = new Game();
        assertEquals(expectedOcean, game.placeShip(new Position(2,1), Direction.HORIZONTAL, 1).render());
    }

    @Test
    void boatOf1CellIsPlacedOnPositionOutsideOcean() {
        Game game = new Game();
        assertThrows(Exception.class, () -> game.placeShip(new Position(10,10), Direction.HORIZONTAL, 1)); // Edge case
        assertThrows(Exception.class, () -> game.placeShip(new Position(14,20), Direction.HORIZONTAL, 1));
    }

    @Test
    void boatOf3CellsIsPlacedHorizontally() throws Exception {
        String expectedOcean = """
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🛳️🛳️🛳️🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                """;
        Game game = new Game();
        assertEquals(expectedOcean, game.placeShip(new Position(1,2), Direction.HORIZONTAL, 3).render());
    }

    @Test
    void boatOf3CellsIsPlacedHorizontallyInvalidLocation() throws Exception {
        Game game = new Game();
        assertThrows(Exception.class, () -> game.placeShip(new Position(8,0), Direction.HORIZONTAL, 3));
    }

    @Test
    void boatOf3CellsIsPlacedVertically() throws Exception {
        String expectedOcean = """
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🛳️🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🛳️🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🛳️🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                """;
        Game game = new Game();
        assertEquals(expectedOcean, game.placeShip(new Position(1,2), Direction.VERTICAL, 3).render()
        );
    }

    @Test
    void boatOf3CellsIsPlacedVerticallyInvalidLocation() {
        Game game = new Game();
        assertThrows(Exception.class, () -> game.placeShip(new Position(0,8), Direction.VERTICAL, 3));
    }

    @Test
    void firingAndMissing() throws Exception {
        String expectedOcean = """
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊💧🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🛳️🛳️🛳️🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                """;

        Game game = new Game();
        assertEquals(expectedOcean, game
                .placeShip(new Position(0,8), Direction.HORIZONTAL, 3)
                .fireShot(new Position(3,4))
                .render());
    }

    @Test
    void firingAndHitting() throws Exception {
        String expectedOcean = """
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                💥🛳️🛳️🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                """;

        Game game = new Game();
        assertEquals(expectedOcean, game
                .placeShip(new Position(0,8), Direction.HORIZONTAL, 3)
                .fireShot(new Position(0,8))
                .render());
    }

    @Test
    void firingAndSinking() throws Exception {
        String expectedOcean = """
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🏊🏊🏊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                """;

        Game game = new Game();
        assertEquals(expectedOcean, game
                .placeShip(new Position(0,8), Direction.HORIZONTAL, 3)
                .fireShot(new Position(0,8))
                .fireShot(new Position(1,8))
                .fireShot(new Position(2,8))
                .render());
    }

    @Test
    void placingAllShips() throws Exception {
        String expectedOcean = """
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

        Game game = new Game();
        assertEquals(expectedOcean, game
                .placeShip(new Position(7,0), Direction.HORIZONTAL, 3)
                .placeShip(new Position(0,2), Direction.HORIZONTAL, 5)
                .placeShip(new Position(8,2), Direction.VERTICAL, 3)
                .placeShip(new Position(5,5), Direction.HORIZONTAL, 2)
                .placeShip(new Position(4,7), Direction.HORIZONTAL, 4)
                .render());
    }
}

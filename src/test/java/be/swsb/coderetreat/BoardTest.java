package be.swsb.coderetreat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    void boardInitiallyRendersEmptyOcean() {
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
        Board board = new Board();
        assertEquals(expectedOcean, board.render());
    }

    @Test
    void shipOf1CellIsPlacedOnPosition() throws Exception {
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
        Board board = new Board();
        assertEquals(expectedOcean, board.placeShip(new Position(2,1), Direction.HORIZONTAL, 1).render());
    }

    @Test
    void shipOf1CellIsPlacedOnPositionOutsideOcean() {
        Board board = new Board();
        assertThrows(Exception.class, () -> board.placeShip(new Position(10,10), Direction.HORIZONTAL, 1)); // Edge case
        assertThrows(Exception.class, () -> board.placeShip(new Position(14,20), Direction.HORIZONTAL, 1));
    }

    @Test
    void shipOf3CellsIsPlacedHorizontally() throws Exception {
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
        Board board = new Board();
        assertEquals(expectedOcean, board.placeShip(new Position(1,2), Direction.HORIZONTAL, 3).render());
    }

    @Test
    void shipOf3CellsIsPlacedHorizontallyInvalidLocation() throws Exception {
        Board board = new Board();
        assertThrows(Exception.class, () -> board.placeShip(new Position(8,0), Direction.HORIZONTAL, 3));
    }

    @Test
    void shipOf3CellsIsPlacedVertically() throws Exception {
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
        Board board = new Board();
        assertEquals(expectedOcean, board.placeShip(new Position(1,2), Direction.VERTICAL, 3).render()
        );
    }

    @Test
    void shipOf3CellsIsPlacedVerticallyInvalidLocation() {
        Board board = new Board();
        assertThrows(Exception.class, () -> board.placeShip(new Position(0,8), Direction.VERTICAL, 3));
    }

    @Test
    void shipCanNotBePlacedOnCellOccupiedByOtherShip() {
        Board board = new Board();
        assertThrows(Exception.class, () -> board
                .placeShip(new Position(3,4), Direction.VERTICAL, 3)
                .placeShip(new Position(3,4), Direction.VERTICAL, 3));
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

        Board board = new Board();
        assertEquals(expectedOcean, board
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

        Board board = new Board();
        assertEquals(expectedOcean, board
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

        Board board = new Board();
        assertEquals(expectedOcean, board
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

        Board board = new Board();
        assertEquals(expectedOcean, board
                .placeShip(new Position(7,0), Direction.HORIZONTAL, 3)
                .placeShip(new Position(0,2), Direction.HORIZONTAL, 5)
                .placeShip(new Position(8,2), Direction.VERTICAL, 3)
                .placeShip(new Position(5,5), Direction.HORIZONTAL, 2)
                .placeShip(new Position(4,7), Direction.HORIZONTAL, 4)
                .render());
    }
}

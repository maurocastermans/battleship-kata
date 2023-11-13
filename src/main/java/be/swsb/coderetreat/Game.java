package be.swsb.coderetreat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Game {
    private static final int BOARD_SIZE = 10;
    private final List<Ship> ships = new ArrayList<>();
    private final List<Cell> wrongGuesses = new ArrayList<>();

    public String render() {
        return OceanPrinter.printOcean(BOARD_SIZE, ships, wrongGuesses);
    }

    public Game placeShip(Position startPosition, Direction direction, int length) throws Exception {
        Ship shipToPlace = new Ship(startPosition, direction, length);
        for (Cell cell: shipToPlace.getOccupiedCells()) {
            if (!validPosition(cell.getPosition())) {
                throw new Exception("The ship can not be placed on position" + cell.getPosition());
            }
        }

        for (Ship existingShip : ships) {
            for (Cell existingCell : existingShip.getOccupiedCells()) {
                for (Cell newCell : shipToPlace.getOccupiedCells()) {
                    if (existingCell.getPosition().equals(newCell.getPosition())) {
                        throw new Exception("The ship can not be placed on position " + newCell.getPosition() +
                                " as it is already occupied by another ship.");
                    }
                }
            }
        }

        ships.add(shipToPlace);
        return this;
    }

    public Game fireShot(Position position) throws Exception {
        if (!validPosition(position)) {
            throw new Exception("Please provide a valid position.");
        }
        Optional<Ship> shipToShoot = getShipContainingPosition(position);
        if (shipToShoot.isPresent()) {
            shipToShoot.get().shot(position);
            shipToShoot.get().checkIfSunken();
        } else {
            wrongGuesses.add(new Cell(position, CellType.MISSED));
        }
        return this;
    }

    public boolean validPosition(Position position) {
        return position.x() < BOARD_SIZE && position.y() < BOARD_SIZE && position.x() >= 0 && position.y() >= 0;
    }

    public Optional<Ship> getShipContainingPosition(Position position) {
        for (Ship ship : ships) {
            for (Cell cell : ship.getOccupiedCells()) {
                if (cell.getPosition().equals(position))
                    return Optional.of(ship);
            }
        }
        return Optional.empty();
    }
}
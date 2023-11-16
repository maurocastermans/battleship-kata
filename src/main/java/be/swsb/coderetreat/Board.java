package be.swsb.coderetreat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class Board {
    private static final int BOARD_SIZE = 10;
    private final List<Ship> ships = new ArrayList<>();
    private final List<Cell> wrongGuesses = new ArrayList<>();

    public String render() {
        return OceanPrinter.printOcean(BOARD_SIZE, ships, wrongGuesses);
    }

    public Board placeShip(Position startPosition, Direction direction, int length) throws Exception {
        Ship shipToPlace = new Ship(startPosition, direction, length);
        verifyCellsInBoard(shipToPlace);
        verifyCellsNotOccupied(shipToPlace);
        ships.add(shipToPlace);
        return this;
    }

    public Board fireShot(Position position) throws Exception {
        if (!validPosition(position)) {
            throw new Exception("OUT OF BOARD: Can not shoot on position " + position);
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

    private void verifyCellsInBoard(Ship shipToPlace) throws Exception {
        for (Cell cell: shipToPlace.getOccupiedCells()) {
            if (!validPosition(cell.getPosition())) {
                throw new Exception("OUT OF BOARD: Can not place ship on position " + cell.getPosition());
            }
        }
    }

    private void verifyCellsNotOccupied(Ship shipToPlace) throws Exception {
        Stream<Cell> existingCells = ships.stream()
                .flatMap(existingShip -> existingShip.getOccupiedCells().stream());

        Set<Cell> newCells = shipToPlace.getOccupiedCells();

        boolean cellsOverlap = existingCells.anyMatch(newCells::contains);
        if (cellsOverlap) {
            throw new Exception("CELL OCCUPIED: Cannot place ship with overlapping cells");
        }
    }

    public boolean validPosition(Position position) {
        return position.x() >= 0 && position.x() < BOARD_SIZE && position.y() >= 0 && position.y() < BOARD_SIZE;
    }

    public Optional<Ship> getShipContainingPosition(Position position) {
        return ships.stream()
                .filter(ship -> ship.getOccupiedCells().stream()
                        .anyMatch(cell -> cell.getPosition().equals(position)))
                .findFirst();
    }

    public int getNumberOfAfloatShips() {
        int shipCount = 0;
        for (Ship ship : ships) {
            if (ship.getStatus() == ShipStatus.AFLOAT) {
                shipCount++;
            }
        }
        return shipCount;
    }
}
package be.swsb.coderetreat;

import java.util.HashSet;
import java.util.Set;

public class Ship {
    private final Set<Cell> occupiedCells;

    public Ship(Position startPosition, Direction direction, int length) {
        occupiedCells = setOccupiedCells(startPosition, direction, length);
    }

    public Set<Cell> setOccupiedCells(Position startPosition, Direction direction, int length) {
        int startXPosition = startPosition.x();
        int startYPosition = startPosition.y();

        Set<Cell> occupiedCells = new HashSet<>();
        switch (direction) {
            case HORIZONTAL -> {
                for (int i = startXPosition; i < startXPosition + length; i++) {
                    occupiedCells.add(new Cell(new Position(i, startYPosition), CellType.SHIP));
                }
            }
            case VERTICAL -> {
                for (int i = startYPosition; i < startYPosition + length; i++) {
                    occupiedCells.add(new Cell(new Position(startXPosition, i), CellType.SHIP));
                }
            }
        }
        return occupiedCells;
    }

    public Set<Cell> getOccupiedCells() {
        return occupiedCells;
    }

    public void shot(Position position) {
        for (Cell cell : occupiedCells) {
            if (cell.getPosition().equals(position)) {
                cell.setCellType(CellType.DAMAGED);
            }
        }
    }

    public void checkIfSunken() {
        for (Cell cell : occupiedCells) {
            if (cell.getCellType().equals(CellType.SHIP)) {
                return;
            }
            sunken();
        }
    }

    private void sunken() {
        for (Cell cell : occupiedCells) {
            cell.setCellType(CellType.SUNKEN);
        }
    }
}
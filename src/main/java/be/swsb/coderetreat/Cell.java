package be.swsb.coderetreat;

import java.util.Objects;

public class Cell {
    private final Position position;
    private CellType cellType;

    public Cell(Position position, CellType cellType) {
        this.position = position;
        this.cellType = cellType;
    }

    public CellType getCellType() {
        return cellType;
    }

    public Position getPosition() {
        return position;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "position=" + position +
                ", cellType=" + cellType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return Objects.equals(position, cell.position) && cellType == cell.cellType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, cellType);
    }
}

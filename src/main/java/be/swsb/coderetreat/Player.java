package be.swsb.coderetreat;

public class Player {

    private final String name;
    private final Board board = new Board();
    private boolean turn = false;

    public Player(String name) {
        this.name = name;
    }

    public Player placeShip(Position startPosition, Direction direction, int length) throws Exception {
        board.placeShip(startPosition, direction, length);
        return this;
    }

    public void fireShot(Position position) throws Exception {
        board.fireShot(position);
    }

    public boolean isLost() {
        return board.getNumberOfAfloatShips() == 0;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public String getName() {
        return name;
    }

    public String renderBoard() {
        return board.render();
    }
}

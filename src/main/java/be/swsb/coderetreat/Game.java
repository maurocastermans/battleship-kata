package be.swsb.coderetreat;

public class Game {
    private Player player;
    private Player opponent;

    public Game(Player player, Player opponent) {
        this.player = player;
        player.setTurn(true);
        this.opponent = opponent;
    }

    public Game fireShot(Position position) throws Exception {
        if (player.isTurn()) {
            opponent.fireShot(position);
        } else if (opponent.isTurn()) {
            player.fireShot(position);
        } else {
            throw new Exception("Game is over");
        }
        changeTurns();
        return this;
    }

    public String getWinner() {
        if (player.isLost()) {
            gameEnded();
            return opponent.getName();
        } else if (opponent.isLost()) {
            gameEnded();
            return player.getName();
        } else {
            return "No player won yet";
        }
    }

    public void changeTurns() {
        player.setTurn(!player.isTurn());
        opponent.setTurn(!opponent.isTurn());
    }

    public void gameEnded() {
        player.setTurn(false);
        opponent.setTurn(false);
    }

    public Player getPlayer() {
        return player;
    }

    public Player getOpponent() {
        return opponent;
    }
}

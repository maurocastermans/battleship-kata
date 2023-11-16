package be.swsb.coderetreat;

import java.util.List;

public class OceanPrinter {

    public static String printOcean(int BOARD_SIZE, List<Ship> ships, List<Cell> wrongGuesses) {
        String[][] ocean = new String[BOARD_SIZE][BOARD_SIZE];
        initializeOcean(ocean, BOARD_SIZE);
        fillOceanWithShips(ocean, ships);
        fillOceanWithWrongGuesses(ocean, wrongGuesses);
        return buildRenderedOcean(ocean, BOARD_SIZE);
    }

    private static void initializeOcean(String[][] ocean, int BOARD_SIZE) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                ocean[i][j] = CellType.WATER.getSymbol();
            }
        }
    }

    private static void fillOceanWithShips(String[][] ocean, List<Ship> ships) {
        ships.forEach(ship ->
                ship.getOccupiedCells().forEach(cell ->
                        ocean[cell.getPosition().y()][cell.getPosition().x()] = cell.getCellType().getSymbol()));
    }

    private static void fillOceanWithWrongGuesses(String[][] ocean, List<Cell> wrongGuesses) {
        wrongGuesses.forEach(wrongGuess ->
                ocean[wrongGuess.getPosition().y()][wrongGuess.getPosition().x()] = wrongGuess.getCellType().getSymbol());
    }

    private static String buildRenderedOcean(String[][] ocean, int BOARD_SIZE) {
        StringBuilder renderedOcean = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                renderedOcean.append(ocean[i][j]);
            }
            renderedOcean.append("\n");
        }
        return renderedOcean.toString();
    }
}
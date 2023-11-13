package be.swsb.coderetreat;

import java.util.List;

public class OceanPrinter {

    public static String printOcean(int BOARD_SIZE, List<Ship> ships, List<Cell> wrongGuesses) {
        String[][] ocean = new String[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                ocean[i][j] = "🌊";
            }
        }
        for (Ship ship : ships) {
            for (Cell cell : ship.getOccupiedCells()) {
                ocean[cell.getPosition().y()][cell.getPosition().x()] = cell.getCellType().getSymbol();
            }
        }
        for (Cell wrongGuess : wrongGuesses) {
            ocean[wrongGuess.getPosition().y()][wrongGuess.getPosition().x()] = wrongGuess.getCellType().getSymbol();
        }
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

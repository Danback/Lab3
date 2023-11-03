package com.example.lab3;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class TicTacToeView {
    private static final int BOARD_SIZE = 3;
    private static final int CELL_SIZE = 100;

    private GridPane grid;

    public TicTacToeView() {
        grid = new GridPane();
        initializeBoard();
    }

    public GridPane getGrid() {
        return grid;
    }

    private void initializeBoard() {
        // Skapa knappar för spelbrädet och lägg till dem i grid
    }

    public void updateBoard(char[][] board) {
        // Uppdatera knapparnas utseende baserat på brädets tillstånd
    }
}

package com.example.lab3;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class TicTacToeView {
    private final GridPane gridPane = new GridPane();

    public TicTacToeView() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button button = new Button();
                button.setMinSize(100, 100);
                gridPane.add(button, col, row);
            }
        }
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public void updateBoard(char[][] board) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button button = (Button) getGridPane().getChildren().get(row * 3 + col);
                char symbol = board[row][col];
                String text = (symbol == '\u0000') ? "" : String.valueOf(symbol);
                button.setText(text);
            }
        }
    }
}

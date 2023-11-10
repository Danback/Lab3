package com.example.lab3;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

// Vyn
public class TicTacToeView {
    private final GridPane gridPane = new GridPane(); // GridPane som organiserar knapparna i ett rutnät för spelet.

    // Konstruktorn skapar UI-komponenterna och layouten.
    public TicTacToeView() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button button = new Button();
                button.setMinSize(100, 100); // Sätter minsta storlek på knapparna för ett trevligt utseende.
                gridPane.add(button, col, row); // Lägger till knappen i GridPane.
            }
        }
    }

    // Getter som returnerar gridPane, som innehåller alla knappar.
    public GridPane getGridPane() {
        return gridPane;
    }

    // Uppdaterar knapparnas text för att visa spelbrädets tillstånd.
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

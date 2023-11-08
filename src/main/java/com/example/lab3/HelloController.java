package com.example.lab3;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;

public class HelloController {
    private final TicTacToeModel model;
    private final TicTacToeView view;

    public HelloController() {
        model = new TicTacToeModel();
        view = new TicTacToeView();
        addEventHandlers();
    }

    private void addEventHandlers() {
        for (int i = 0; i < 9; i++) {
            Button button = (Button) view.getGridPane().getChildren().get(i);
            int row = i / 3;
            int col = i % 3;
            button.setOnAction(event -> {
                if (model.isGameInProgress() && model.isXTurn()) {
                    // Player 'X' makes a move
                    model.makeMove(row, col, 'X');
                    view.updateBoard(model.getBoard());

                    // Check for win or draw after player 'X' move
                    if (model.checkForWin('X')) {
                        gameEnd("Player X wins!");
                    } else if (model.checkForDraw()) {
                        gameEnd("It's a draw!");
                    } else {
                        // Now it is the computer's turn
                        makeComputerMove();
                    }
                }
            });
        }
    }

    private void makeComputerMove() {
        model.makeComputerMove();
        view.updateBoard(model.getBoard());

        // Check for win or draw after computer 'O' move
        if (model.checkForWin('O')) {
            gameEnd("Computer wins!");
        } else if (model.checkForDraw()) {
            gameEnd("It's a draw!");
        }
    }



    private void gameEnd(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText(message + "\n" + getScoreMessage());
        alert.showAndWait();

        // Reset the game
        model.resetGame();
        view.updateBoard(model.getBoard());
    }

    private String getScoreMessage() {
        return "Scores:\nPlayer X - " + model.getPlayerXScore() + "\nPlayer O - " + model.getPlayerOScore();
    }

    public TicTacToeView getView() {
        return view;
    }
}

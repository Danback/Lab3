package com.example.lab3;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.Random;
import javafx.scene.Node;

public class HelloApplication extends Application {
    private char[][] board = new char[3][3];
    private boolean xTurn = true;
    private boolean userHasMoved = false;
    private boolean gameInProgress = true;
    private GridPane gridPane; // Added gridPane as an instance variable
    private int playerXScore = 0;
    private int playerOScore = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tic Tac Toe");

        gridPane = new GridPane(); // Initialize gridPane
        gridPane.setAlignment(Pos.CENTER);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button button = createButton(row, col);
                gridPane.add(button, col, row);
            }
        }

        Scene scene = new Scene(gridPane, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createButton(int row, int col) {
        Button button = new Button();
        button.setMinSize(100, 100);
        button.setOnAction(e -> handleButtonClick(row, col, button));
        return button;
    }

    private void handleButtonClick(int row, int col, Button button) {
        if (gameInProgress && board[row][col] == 0 && xTurn) {
            board[row][col] = 'X';
            button.setText("X");
            xTurn = !xTurn;
            userHasMoved = true;

            if (checkForWin('X') || checkForDraw()) {
                endGame();
                return;
            }

            // Let the computer make a move after the user's first move
            if (userHasMoved) {
                makeComputerMove();
            }
        }
        // Add an else statement to allow the user to make another move when it's their turn
        else if (gameInProgress && board[row][col] == 0 && !xTurn) {
            board[row][col] = 'O';
            button.setText("O");
            xTurn = !xTurn;

            if (checkForWin('O') || checkForDraw()) {
                endGame();
                return;
            }

            // Let the computer make a move after the user's move
            if (userHasMoved) {
                makeComputerMove();
            }
        }
    }


    private void makeComputerMove() {
        if (gameInProgress) {
            Random random = new Random();
            int row, col;
            do {
                row = random.nextInt(3);
                col = random.nextInt(3);
            } while (board[row][col] != 0);

            board[row][col] = 'O'; // Always set the computer's move to 'O'

            // Find the button that corresponds to the computer's move and update it in the UI
            for (Node node : gridPane.getChildren()) {
                if (GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) == row
                        && GridPane.getColumnIndex(node) != null && GridPane.getColumnIndex(node) == col) {
                    if (node instanceof Button button) {
                        button.setText("O");
                    }
                }
            }

            if (checkForWin('O') || checkForDraw()) {
                endGame();
            }

            xTurn = true; // Set it to the user's turn
        }
    }


    private boolean checkForWin(char player) {
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
                return true;
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }

        return board[0][2] == player && board[1][1] == player && board[2][0] == player;
    }


    private boolean checkForDraw() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == 0) {
                    return false; // There is an empty cell, so the game is not a draw yet
                }
            }
        }
        // All cells are filled, and there is no winner, so it's a draw
        return true;
    }


    private void endGame() {
        gameInProgress = false;

        // Check for a winner or tie
        String resultMessage = "";
        if (checkForWin('X')) {
            resultMessage = "Player X wins!";
            playerXScore++;
        } else if (checkForWin('O')) {
            resultMessage = "Player O wins!";
            playerOScore++;
        } else if (checkForDraw()) {
            resultMessage = "It's a tie!";
        }

        resultMessage += "\nScores: Player X - " + playerXScore + ", Player O - " + playerOScore;

        // Display the result and scores in a dialog
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Round Result");
        alert.setHeaderText(null);
        alert.setContentText(resultMessage);
        alert.showAndWait();

        // Start a new game
        resetGame();
    }


    private void resetGame() {
        board = new char[3][3];
        xTurn = true;
        userHasMoved = false; // Reset user's move status
        gameInProgress = true;
        // Reset the UI to clear the board
        gridPane.getChildren().forEach(node -> {
            if (node instanceof Button button) {
                button.setText("");
            }
        });
    }
    public boolean isValidMove(int row, int col) {
        return gameInProgress && board[row][col] == 0 && ((xTurn && board[row][col] == 0) || (!xTurn && board[row][col] == 0));
    }

    public void makeMove(int row, int col) {
        if (isValidMove(row, col)) {
            if (xTurn) {
                board[row][col] = 'X';
            } else {
                board[row][col] = 'O';
            }
            xTurn = !xTurn;
            userHasMoved = true;
        }
    }

}

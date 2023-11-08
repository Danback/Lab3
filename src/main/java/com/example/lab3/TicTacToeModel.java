package com.example.lab3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TicTacToeModel {
    private final char[][] board = new char[3][3];
    private boolean xTurn = true;
    private boolean gameInProgress = true;
    private int playerXScore = 0;
    private int playerOScore = 0;
    private final Random random = new Random();

    public TicTacToeModel() {
        resetGame();
    }

    public boolean isValidMove(int row, int col) {
        return board[row][col] == '\u0000' && gameInProgress;
    }

    public void makeMove(int row, int col, char player) {
        if (gameInProgress && board[row][col] == '\u0000') {
            board[row][col] = player;
            // Toggle turns only if it's a player's move, not if the computer is making the move
            if (player == 'X') {
                xTurn = false;
            } else if (player == 'O') {
                xTurn = true;
            }
            checkGameStatus();
        }
    }


    public void makeComputerMove() {
        if (!gameInProgress || xTurn) { // Only make a computer move if it's not X's turn
            return;
        }

        List<int[]> emptyCells = new ArrayList<>();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == '\u0000') {
                    emptyCells.add(new int[]{row, col});
                }
            }
        }

        if (!emptyCells.isEmpty()) {
            int[] move = emptyCells.get(random.nextInt(emptyCells.size()));
            board[move[0]][move[1]] = 'O';
            xTurn = !xTurn; // Toggle the turn back to X
            checkGameStatus();
        }
    }

    private void checkGameStatus() {
        char currentPlayer = xTurn ? 'O' : 'X';
        if (checkForWin(currentPlayer)) {
            gameInProgress = false;
            if (currentPlayer == 'X') {
                playerXScore++;
            } else {
                playerOScore++;
            }
        } else if (checkForDraw()) {
            gameInProgress = false;
        }
    }

    public boolean checkForWin(char player) {
        // Horizontal, vertical, and diagonal checks
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        return board[0][2] == player && board[1][1] == player && board[2][0] == player;
    }

    public boolean checkForDraw() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == '\u0000') {
                    return false;
                }
            }
        }
        return true;
    }

    public void resetGame() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = '\u0000';
            }
        }
        gameInProgress = true;
        xTurn = true; // X always starts
    }

    // Getters and Setters
    public char[][] getBoard() {
        return board;
    }

    public boolean isXTurn() {
        return xTurn;
    }

    public boolean isGameInProgress() {
        return gameInProgress;
    }

    public int getPlayerXScore() {
        return playerXScore;
    }

    public int getPlayerOScore() {
        return playerOScore;
    }
}

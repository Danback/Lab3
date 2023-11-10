// TicTacToeModel.java
package com.example.lab3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Här hanterar jag spellogik
public class TicTacToeModel {
    private final char[][] board = new char[3][3];
    // Boolean som håller reda på vems tur det är, true för X och false för O.
    private boolean xTurn = true;
    // Boolean som håller reda på om spelet är igång.
    private boolean gameInProgress = true;
    // Spelares poäng.
    private int playerXScore = 0;
    private int playerOScore = 0;
    // Random-objekt för att generera slumpmässiga drag för datorn.
    private final Random random = new Random();

    // Konstruktor som initierar ett nytt spel.
    public TicTacToeModel() {
        resetGame();
    }

    // Metod för att kontrollera om ett drag är giltigt.
    public boolean isValidMove(int row, int col) {
        return board[row][col] == '\u0000' && gameInProgress;
    }

    // Metod för att göra ett drag på spelbrädet.
    public void makeMove(int row, int col, char player) {
        if (gameInProgress && board[row][col] == '\u0000') {
            board[row][col] = player;
            // Byter tur om det är en spelares drag.
            if (player == 'X') {
                xTurn = false;
            } else if (player == 'O') {
                xTurn = true;
            }
            // Kontrollerar spelstatus efter varje drag.
            checkGameStatus();
        }
    }

    // Metod för att låta datorn göra ett drag.
    public void makeComputerMove() {
        if (!gameInProgress || xTurn) { // Endast om det är O:s tur.
            return;
        }

        List<int[]> emptyCells = new ArrayList<>();
        // Hittar alla tomma celler.
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == '\u0000') {
                    emptyCells.add(new int[]{row, col});
                }
            }
        }

        // Väljer en slumpmässig tom cell och gör ett drag för O.
        if (!emptyCells.isEmpty()) {
            int[] move = emptyCells.get(random.nextInt(emptyCells.size()));
            board[move[0]][move[1]] = 'O';
            xTurn = !xTurn; // Byter tillbaka turen till X.
            checkGameStatus();
        }
    }

    // Privat metod för att kontrollera status på spelet efter varje drag.
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

    // Metod för att kontrollera om en spelare har vunnit.
    public boolean checkForWin(char player) {
        // Kontrollerar rader, kolumner och diagonaler.
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

    // Metod som kollar/kontrollerar om spelet är oavgjort.
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

    // Metod för att återställa spelet till startstatus.
    public void resetGame() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = '\u0000';
            }
        }
        gameInProgress = true;
        xTurn = true; // X börjar alltid.
    }

    // Getters och setters för att få tillgång till privata fält.
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

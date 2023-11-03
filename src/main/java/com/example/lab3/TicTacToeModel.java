package com.example.lab3;

public class TicTacToeModel {
    private char[][] board;
    private boolean xTurn;

    public TicTacToeModel() {
        board = new char[3][3]; // 3x3 bräde
        xTurn = true; // Börja med X-spelare
    }

    public char[][] getBoard() {
        return board;
    }

    public boolean isXTurn() {
        return xTurn;
    }

    public void makeMove(int row, int col) {
        // Implementera logik för att göra drag
        // Uppdatera brädet och byt tur
    }

    public boolean isGameOver() {
        // Implementera logik för att avgöra om spelet är över (vinst eller oavgjort)
        return false;
    }
}

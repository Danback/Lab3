package com.example.lab3;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TicTacToeModelTest {
    private TicTacToeModel game;

    @Before
    public void setUp() {
        game = new TicTacToeModel();
    }

    @Test
    public void testValidMove() {
        // Initially, any move should be valid as long as the cell is empty
        game.resetGame(); // Make sure the game is reset before testing
        assertTrue(game.isValidMove(0, 0));
        assertTrue(game.isValidMove(1, 1));
    }

    @Test
    public void testInvalidMove() {
        game.resetGame(); // Reset the game to clear the board
        game.makeMove(0, 0, 'X'); // Make a move for player 'X'
        assertFalse(game.isValidMove(0, 0)); // This cell is no longer valid for a move

        game.makeMove(1, 1, 'O'); // Make a move for player 'O'
        assertFalse(game.isValidMove(1, 1)); // This cell is no longer valid for a move
    }

    @Test
    public void testRoundCompletion() {
        game.resetGame(); // Reset the game to start fresh

        // Simulate a winning condition for 'X'
        game.makeMove(0, 0, 'X');
        game.makeMove(0, 1, 'X');
        game.makeMove(0, 2, 'X');
        assertTrue("Game should be won by X", game.checkForWin('X'));

        // Simulate a draw
        game.resetGame(); // Reset the game to start fresh
        game.makeMove(0, 0, 'X');
        game.makeMove(0, 1, 'O');
        game.makeMove(0, 2, 'X');
        game.makeMove(1, 0, 'X');
        game.makeMove(1, 1, 'X');
        game.makeMove(1, 2, 'O');
        game.makeMove(2, 0, 'O');
        game.makeMove(2, 1, 'X');
        game.makeMove(2, 2, 'O');
        assertTrue("Game should be a draw", game.checkForDraw());
    }
}

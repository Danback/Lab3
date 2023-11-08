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
        game.resetGame();
        assertTrue(game.isValidMove(0, 0));
        game.makeMove(0, 0, 'X'); // Assume this is a player move
        assertFalse(game.isValidMove(0, 0)); // Now it should be invalid
    }

    @Test
    public void testInvalidMove() {
        game.resetGame();
        game.makeMove(0, 0, 'X');
        assertFalse(game.isValidMove(0, 0)); // Cell is already taken
    }

    @Test
    public void testWinCondition() {
        game.resetGame();
        game.makeMove(0, 0, 'X');
        game.makeMove(0, 1, 'X');
        game.makeMove(0, 2, 'X');
        assertTrue(game.checkForWin('X'));
    }

    @Test
    public void testDrawCondition() {
        game.resetGame();
        game.makeMove(0, 0, 'X');
        game.makeMove(0, 1, 'O');
        game.makeMove(0, 2, 'X');
        game.makeMove(1, 0, 'X');
        game.makeMove(1, 1, 'O');
        game.makeMove(1, 2, 'X');
        game.makeMove(2, 0, 'O');
        game.makeMove(2, 1, 'X');
        game.makeMove(2, 2, 'O');
        assertTrue(game.checkForDraw());
    }

}

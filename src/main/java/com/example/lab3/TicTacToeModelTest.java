package com.example.lab3;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TicTacToeModelTest {
    private TicTacToeModel game; // Instans av spelmodellen som ska testas.

    @Before
    public void setUp() {
        game = new TicTacToeModel(); // Initialiserar en ny spelinstans före varje test.
    }

    @Test
    public void testValidMove() {
        game.resetGame(); // Återställer spelet till sitt ursprungliga tillstånd.
        assertTrue(game.isValidMove(0, 0)); // Kontrollerar om draget är giltigt.
        game.makeMove(0, 0, 'X'); // Gör ett drag för spelare 'X'.
        assertFalse(game.isValidMove(0, 0)); // Nu bör draget vara ogiltigt eftersom cellen är tagen.
    }

    @Test
    public void testInvalidMove() {
        game.resetGame(); // Återställer spelet till sitt ursprungliga tillstånd.
        game.makeMove(0, 0, 'X'); // Gör ett drag för spelare 'X'.
        assertFalse(game.isValidMove(0, 0)); // Kontrollerar om draget är ogiltigt eftersom cellen redan är tagen.
    }

    @Test
    public void testWinCondition() {
        game.resetGame(); // Återställer spelet till sitt ursprungliga tillstånd.
        // Simulerar en vinnande kombination för 'X'.
        game.makeMove(0, 0, 'X');
        game.makeMove(0, 1, 'X');
        game.makeMove(0, 2, 'X');
        assertTrue(game.checkForWin('X')); // Kontrollerar om 'X' har vunnit.
    }

    @Test
    public void testDrawCondition() {
        game.resetGame(); // Återställer spelet till sitt ursprungliga tillstånd.
        // Simulerar en oavgjord situation.
        game.makeMove(0, 0, 'X');
        game.makeMove(0, 1, 'O');
        game.makeMove(0, 2, 'X');
        game.makeMove(1, 0, 'X');
        game.makeMove(1, 1, 'O');
        game.makeMove(1, 2, 'X');
        game.makeMove(2, 0, 'O');
        game.makeMove(2, 1, 'X');
        game.makeMove(2, 2, 'O');
        assertTrue(game.checkForDraw()); // Kontrollerar om spelet är oavgjort.
    }

    @Test
    public void testWinConditionForO() {
        game.resetGame(); // Återställer spelet till sitt ursprungliga tillstånd.

        // Simulerar en vinnande kombination för 'O'.
        game.makeMove(0, 0, 'O');
        game.makeMove(1, 0, 'O');
        game.makeMove(2, 0, 'O');

        assertTrue("Game should be won by O", game.checkForWin('O')); // Kontrollerar om 'O' har vunnit.
    }

    @Test
    public void testGameEndsAfterWinForO() {
        game.resetGame(); // Återställer spelet till sitt ursprungliga tillstånd.

        // Simulerar en vinnande kombination för 'O'.
        game.makeMove(0, 0, 'O');
        game.makeMove(1, 0, 'O');
        game.makeMove(2, 0, 'O');

        assertFalse("Game should be over after a win", game.isGameInProgress()); // Kontrollerar om spelet är slut efter en vinst.
    }
}

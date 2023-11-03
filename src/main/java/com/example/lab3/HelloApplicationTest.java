package com.example.lab3;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class HelloApplicationTest {
    private HelloApplication game;

    @Before
    public void setUp() {
        game = new HelloApplication();
    }

    @Test
    public void testValidMove() {
        assertTrue(game.isValidMove(0, 0));
        assertTrue(game.isValidMove(1, 1));
    }

    @Test
    public void testInvalidMove() {
        game.makeMove(0, 0);
        assertFalse(game.isValidMove(0, 0));
        game.makeMove(1, 1);
        assertFalse(game.isValidMove(1, 1));
    }

    @Test
    public void testRoundCompletion() {
        // Test whether a round is completed when the game conditions are met
        // You can simulate game conditions to check if the round is completed
        // For example, simulate a win or draw and check if the round completion is detected.
    }
}

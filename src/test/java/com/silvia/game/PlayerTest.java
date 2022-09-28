package com.silvia.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Player player = new Player("Sara", new int[0][0], 20, 50);

    @Test
    void getName() {
        assertEquals("Sara", player.getName());
    }

    @Test
    void getHighScore() {
        assertEquals(50, player.getHighScore());
    }

    @Test
    void setHighScore() {
        player.setHighScore(30);

        assertEquals(30, player.getHighScore());
    }

    @Test
    void getWinScore() {
        assertEquals(20, player.getWinScore());
    }

    @Test
    void setWinScore() {
        player.setWinScore(10);

        assertEquals(10, player.getWinScore());
    }

    @Test
    void testToString() {

        assertEquals("Player: Sara\n Total wins: 20 High score: 50\n Dice throws: []", player.toString());
    }
}
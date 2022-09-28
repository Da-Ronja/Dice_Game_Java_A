package com.silvia.game;

public class Main {
    public static void main(String[] args) {

        Game game = new Game();
        game.initializePlayers();
        game.initializeGame();
        game.gameRound();
        game.gameFinal();

    }
}

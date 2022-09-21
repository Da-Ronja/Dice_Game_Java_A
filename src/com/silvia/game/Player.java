package com.silvia.game;
import java.util.Arrays;

public class Player {
    static int playerMinAmount = 2;
    public static String howManyText = "How many players want to play? \n Enter a number between " + playerMinAmount + " and up";
    public static String wrongAmount = "That's not a number! \n Please enter a number between " + playerMinAmount + " and up";

    private String name;
    int[][] scoreList;
    private int winScore;
    private int highScore;

    public Player(String name, int[][] scoreList, int winScore, int highScore) {
        this.name = name;
        this.scoreList = scoreList;
        this.winScore = winScore;
        this.highScore = highScore;
    }

    public String getName() {
        return name;
    }

    public int getHighScore() {
        return highScore;
    }
    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
    public int getWinScore() {
        return winScore;
    }
    public void setWinScore(int winScore) {
        this.winScore = winScore;
    }

    public String toString() {

        return "Player: " + name + "\n Total wins: " + winScore + " High score: " + highScore + "\n Dice throws: " + Arrays.deepToString(scoreList);
    }

}

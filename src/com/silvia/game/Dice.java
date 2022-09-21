package com.silvia.game;
import java.util.Random;

public class Dice {
    public static int minDiceAmount = 1;
    public static String howManyText = "How many dice do you want to play with? \n Enter a number between " + minDiceAmount + "  and up";
    public static String wrongAmount = "That's not a number! \n Please enter a number between " + minDiceAmount + " and up";

    static int dieDotValue;

    public static int throwDie() {
        int diceMaxDots = 6;
        int diceMinDots = 1;

        Random randomValue = new Random();
        dieDotValue = randomValue.nextInt(diceMinDots, diceMaxDots + 1);

        return dieDotValue;
    }
}

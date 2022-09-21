package com.silvia.game;
import java.util.Scanner;

public class UserInput {

    static Scanner scan = new Scanner(System.in);

    static int amountOf;

    public static void scanInt(String howManyText, String wrongAmount, int minimumAmount) {
        do {
            System.out.println(howManyText);
            while (!scan.hasNextInt()) {
                System.out.println(wrongAmount);
                scan.next();
            }
            amountOf = scan.nextInt();
        } while (amountOf < minimumAmount);

    }

    static String scanPlayersName(int playerAmount) {
        System.out.println("Whats your name player " + playerAmount + "?");

        return scan.next();
    }

    static void pressToContinue()
    {
        System.out.println("Press Enter key twice to continue...");
        try
        {
            System.in.read();
            scan.nextLine();
        }
        catch(Exception e)
        {}
    }

}

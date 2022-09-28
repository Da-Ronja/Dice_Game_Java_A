package com.silvia.game;
import java.util.ArrayList;

public class Game {
    int diceAmount;
    int playerAmount;
    static ArrayList<Player> playersList = new ArrayList<>();

    boolean last = false;
    int amountOfRounds;
    int sumOfScore;
    int maxScore = 0;   // HOLDER FOR THE HIGHEST SCORE
    int totalScore;
    int playerIndex;
    ArrayList<Integer> listOfScores = new ArrayList<>();
    ArrayList<Integer> topPlayerList = new ArrayList<>();
    ArrayList<Integer> newTopPlayerList = new ArrayList<>();
    ArrayList<Integer> throwToWin;


    public void initializePlayers() {

        UserInput.scanInt(Player.howManyText, Player.wrongAmount, Player.playerMinAmount);
        playerAmount = UserInput.amountOf;
        System.out.println(playerAmount);

        for (int i = 0; i < playerAmount; i++) {
            int playerNum = i + 1;
            String playerName;
            int winScore = 0;
            int highScore = 0;

            playerName = UserInput.scanPlayersName(playerNum);
            playersList.add(new Player(playerName, new int[amountOfRounds][diceAmount], winScore, highScore));
        }

        System.out.println(" ");
    }

    public void initializeGame() {
        int minSetAmount = 1;
        String howManySetText = "How many sets do you want to play? \n Enter a number between " + minSetAmount + "  and up";
        String wrongSetAmount = "That's not a number! \n Please enter a number between " + minSetAmount + " and up";

        UserInput.scanInt(Dice.howManyText, Dice.wrongAmount, Dice.minDiceAmount);
        diceAmount = UserInput.amountOf;
        UserInput.scanInt(howManySetText, wrongSetAmount, minSetAmount);
        amountOfRounds = UserInput.amountOf;


        for (Player player : playersList) {
            player.scoreList = new int[amountOfRounds][diceAmount];
        }

        System.out.println("You chose to play with " + diceAmount + " dice and " + amountOfRounds + " rounds!\n");
    }

    public void gameRound() {
        last = false;

        for (int i = 0; i < amountOfRounds; i++) {
            System.out.println(" **** Game Round " + (i + 1) + " **** \n ");

            for (Player player : playersList) { // EACH PLAYER THROWS, CALCULATION OF SUM FOR THIS ROUND + ALL ROUNDS

                // FOR THE DICE THROW
                System.out.println(player.getName() + " throws ...");
                for (int k = 0; k < diceAmount; k++) {
                    int dieValue = Dice.throwDie();
                    player.scoreList[i][k] = dieValue;
                }

                sumOfScore = 0;     // FOR SCORE OF ROUND Clear sum for calculation the sum of next player
                for (int j = 0; j < player.scoreList[0].length; j++) {        // get total score sum of round from 2D array
                    sumOfScore = sumOfScore + player.scoreList[i][j];
                }
                listOfScores.add(sumOfScore);

                totalScore = 0; // FOR HIGH SCORE OF ALL ROUNDS
                for (int j = 0; j < player.scoreList.length; j++) {
                    for (int k = 0; k < player.scoreList[0].length; k++) {
                        totalScore += player.scoreList[j][k];
                    }
                }
                player.setHighScore(totalScore);

                System.out.print("Your dice landed on [");  // PRINTING THE LANDED DICE
                for (int c = 0; c < player.scoreList[i].length; c++) {      //for loop for column iteration.
                    System.out.print("[" + player.scoreList[i][c] + "]");
                }
                System.out.println("] \n Your total score for this round is: " + sumOfScore + "\n");

                UserInput.pressToContinue();
            }

            maxScore = maxScoreCalc(listOfScores);                               // CALCULATION THE HIGHEST SCORE OF THE ROUND
            System.out.println("The highest score is " + maxScore + " and.... \n");
            topPlayerList = winnersPlayList(listOfScores, maxScore);           // GETTING THE PLAYERS WITH THE HIGHEST SCORE OF THE ROUND

            if (topPlayerList.size() > 1) { // IF STATEMENT FOR PRESENTING A WINNER OR MULTI WINNERS OF THE ROUND
                System.out.println("It's a tie between:");

            } else {
                System.out.println("The winner is:");

            }

            forWin(topPlayerList, last);

            for (int inter : topPlayerList) {
                System.out.print("Player: " + playersList.get(inter).getName() + ". ");
            }
            System.out.println(" ");
            for (int inter : topPlayerList) {
                System.out.print(playersList.get(inter).getName() + "'s dices landed on [");  // PRINTING THE LANDED DICE
                for (int c = 0; c < playersList.get(inter).scoreList[i].length; c++) {      // for loop for column iteration.
                    System.out.print("[" + playersList.get(inter).scoreList[i][c] + "]");
                }
                System.out.println("] with the score of " + maxScore);
            }

            System.out.println("\n \n  **** ** Game round " + (i + 1) + " has ended ** **** ");

            UserInput.pressToContinue();
            listOfScores.clear();
            topPlayerList.clear();
        }
    }


    public void gameFinal() {
        last = true;
        System.out.println("---------- *** End of all Rounds *** ----------\n");

        for (Player player : playersList) {     // GETTING WINS
            listOfScores.add(player.getWinScore());
        }
        maxScore = maxScoreCalc(listOfScores);      // CALCULATION HIGHEST AMOUNT OF WINS
        topPlayerList = winnersPlayList(listOfScores, maxScore); // LIST OF PLAYERS WITH HIGHEST WINS


        if (topPlayerList.size() > 1) {
            UserInput.pressToContinue();
            System.out.println("It's a tie between:");
            //*******************************************************************
            // BUILDING IF MULTI PLAYERS WINS COMPARE HIGHEST SCORE OF ALL ROUNDS
            //*******************************************************************
            forWin(topPlayerList, last);

            System.out.println("\nCOMPARE THE HIGHEST SCORE FOR EACH PLAYERS......");
            listOfScores.clear(); // CLEARING LIST FOR NEW CALCULATION OF HIGHEST SCORE

            for (int inter : topPlayerList) {  // ADDING HIGHEST SCORE
                listOfScores.add(playersList.get(inter).getHighScore());
                System.out.print("...Player: " + playersList.get(inter).getName() + "..." + playersList.get(inter).getHighScore());
            }

            maxScore = maxScoreCalc(listOfScores);               // CALCULATION WITCH SCORE IS HIGHEST
            System.out.println("\n\n Highest score is " + maxScore);

            newTopPlayerList = new ArrayList<>();
            for (int inter : topPlayerList) {
                sumOfScore = 0;

                if (maxScore == playersList.get(inter).getHighScore()) {
                    playerIndex = inter;
                    newTopPlayerList.add(playerIndex);
                }
            }
            topPlayerList = newTopPlayerList;

            for (int inter : topPlayerList) {  //  HIGHEST SCORE
                playerIndex = inter;
                System.out.println("Player: " + playersList.get(playerIndex).getName() + " has a total high score of " + playersList.get(playerIndex).getHighScore());
            }
            System.out.println(" ");

            UserInput.pressToContinue();

            if (topPlayerList.size() > 1) {
                //*******************************************************************************
                // BUILDING DO WHILE MULTI PLAYERS WINS THROW THE HIGHEST SCORE ON ONE DIE TO WIN
                //*******************************************************************************
                do {
                    System.out.println("It is still a tie between:");
                    throwToWin = new ArrayList<>();
                    for (int inter : topPlayerList) {
                        playerIndex = inter;
                        System.out.print(" Player: " + playersList.get(playerIndex).getName() + ".");
                    }
                    System.out.println("\n");
                    System.out.println("THROW THE DIE AND GET THE HIGHEST SCORE TO WIN");

                    System.out.println("The die trow for every player");
                    for (int inter : topPlayerList) {
                        playerIndex = inter;
                        int dieValue = Dice.throwDie();
                        throwToWin.add(dieValue);
                        System.out.println("Player: " + playersList.get(playerIndex).getName() + " throws a " + dieValue);
                    }
                    maxScore = maxScoreCalc(throwToWin);
                    System.out.println("\n Highest die: " + maxScore);

                    newTopPlayerList = new ArrayList<>();   // GET THE NUMBER OF topPlayerList FOR  INDEX OF PLAYER IN playersList
                    for (int j = 0; j < throwToWin.size(); j++) {

                        if (maxScore == throwToWin.get(j)) {
                            playerIndex = topPlayerList.get(j);
                            newTopPlayerList.add(playerIndex);
                        }
                    }
                    topPlayerList = newTopPlayerList;

                    for (int inter : topPlayerList) {
                        playerIndex = inter;
                        System.out.println(playersList.get(playerIndex).getName() + " got the highest die score of " + maxScore  + "\n");
                    }
                    UserInput.pressToContinue();
                } while (topPlayerList.size() > 1);
            }
        }

        System.out.println("THE WINNER IS: ");
        forWin(topPlayerList, last);
        System.out.println(" ");

        UserInput.pressToContinue();

        System.out.println("Score list:");
        for (Player player : playersList) {
            System.out.println(player.toString());
        }
    }


    // CALCULATION THE HIGHEST SCORE
    public static int maxScoreCalc(ArrayList<Integer> scoreList) {
        int maxScore = 0;

        for (Integer score : scoreList) {
            if (score > maxScore) {
                maxScore = score;
            }
        }

        return maxScore;
    }

    // GETTING PLAYERS WITH HIGHEST SCORE
    public static ArrayList<Integer> winnersPlayList(ArrayList<Integer> listOfScores, int maxScore) {
        ArrayList<Integer> topPlayerList = new ArrayList<>();

        for (int j = 0; j < listOfScores.size(); j++) {
            if (maxScore == listOfScores.get(j)) {
                topPlayerList.add(j);
            }
        }

        return topPlayerList;
    }

    // ADDING TO WIN SCORE
    public static void forWin(ArrayList<Integer> topPlayerList, boolean last) {

        for (int inter : topPlayerList) {
            if (!last) {
                int wins = playersList.get(inter).getWinScore();
                wins += 1;
                playersList.get(inter).setWinScore(wins);
            } else {
                System.out.println(playersList.get(inter).getName() + " that has a total of " + playersList.get(inter).getWinScore() + " wins! \n and a total score of " + playersList.get(inter).getHighScore());
            }
        }
    }

}
package com.silvia.game;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void maxScoreCalc() {
        ArrayList<Integer> scoreList = new ArrayList<>(Arrays.asList(2,5,8));
        Game.maxScoreCalc(scoreList);

        int result = 8;

        assertEquals(8, result);

    }

    @Test
    void winnersPlayList() {
        ArrayList<Integer> listOfScores = new ArrayList<>(Arrays.asList(2,5,8,8));

        int maxScore = 8;

        Game.winnersPlayList(listOfScores, maxScore);
        ArrayList<Integer> result = new ArrayList<>(Arrays.asList(8,8));

        assertEquals(Arrays.asList(8,8), result);
    }
}
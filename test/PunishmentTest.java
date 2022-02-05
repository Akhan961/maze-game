package com.mycompany.app;

import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

public class PunishmentTest {

    private int runningPoints = 50;

    @Before
    public void resetPoints(){
        runningPoints = 50;
    }

    @Test
    public void deductPointsTest(){
        int newPoints = Punishment.deductPoints(runningPoints);
        assertEquals(25, newPoints);
    }

    @Test
    public void addPointsTest(){
        int newPoints = Punishment.addPoints(runningPoints);
        assertEquals(75, newPoints);
    }

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void negScoreFalse(){
        // No need to expect system.exit because the program wont close if the score is still >= 0
        Punishment.negScore(runningPoints);
        assertEquals(false, Maze.lost);
    }

    @Test
    public void negScoreTrue(){
        exit.expectSystemExitWithStatus(0);
        runningPoints = -1;
        Punishment.negScore(runningPoints);
        assertEquals(true, Maze.lost);
    }
}


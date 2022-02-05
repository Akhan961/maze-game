package com.mycompany.app;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import static org.junit.Assert.*;

public class MazeTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Before
    public void setUp() {
        Maze.createMap();
    }

    @Test
    public void createMap() {
        String real_maze =
                "******************" +
                "*b  k           k*" +
                "* * * *        * *" +
                "* *  * ********* *" +
                "* *         p  * *" +
                "*c  ** p *  ** * *" +
                "* * **  c   *  c *" +
                "* *     *b  * ** *" +
                "*   ** ***       *" +
                "* * *       * ** *" +
                "* * *  c c* *  * *" +
                "* * *   * * ** * *" +
                "sm      *p      b*" +
                "***********e******";
        assertEquals(real_maze, Maze.maze);
        int counter = 0;
        for (int i = 0; i < Maze.ROWS; i++) {
            for (int j = 0; j < Maze.COLUMNS; j++) {
                assertEquals(String.valueOf(real_maze.charAt(counter)), Maze.map[i][j].getElement());
                assertEquals(i, Maze.map[i][j].getX());
                assertEquals(j, Maze.map[i][j].getY());
                counter++;
            }
        }
    }

    @Test
    public void getPos() {
        Maze.getPos();
        assertEquals("m", Maze.player.getElement());
        assertEquals(12, Maze.player.getX());
        assertEquals(1, Maze.player.getY());
        assertEquals(13, Maze.END_DOOR_X);
        assertEquals(11, Maze.END_DOOR_Y);
    }

    @Test
    public void gameStatusLost() {
        exit.expectSystemExitWithStatus(0);
        Maze.lost = true;
        Maze.gameStatus();
    }

    @Test
    public void gameStatusWon() {
        exit.expectSystemExitWithStatus(0);
        Maze.won = true;
        Maze.gameStatus();
    }

    @Test
    public void gameStatusNeutral() {
        Maze.won = false;
        Maze.lost = false;
        Maze.gameStatus();
    }

}

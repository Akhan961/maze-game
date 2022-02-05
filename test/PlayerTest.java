package com.mycompany.app;

import org.junit.Rule;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    // Default setting and initializations required for the game to function
    // within the context of the Player class
    @Before
    public void setup() {
        // Initializes the map and enemies so there is a medium to test in
        Maze.createMap();
        Enemy.createEnemies();
        Maze.getPos();

        // Reset static Player fields after every test
        Player.playerScore = 50;
        Player.collectedKeys = 0;
    }

    // Check to see if the player was initialized properly
    @Test
    public void playerInitTest() {
        // Check if player fields were initialized correctly
        assertEquals(Player.collectedKeys, 0);
        assertEquals(Player.numKeys, 2);
        assertEquals(Player.playerScore, 50);

        // Check player starts in the right position
        assertEquals(Maze.player.getX(), 12);
        assertEquals(Maze.player.getY(), 1);
    }

    // Base test to see that any normal player movement
    // (that does not collide with a key) will not collect any keys.
    // Key 1 is located a (1, 4) so if the player were to be placed
    // at (1, 6) and then moves down 1, the player is expected to not
    // collect any keys.
    @Test
    public void testCollectNothing() {
        // Check that the player has not collected any keys
        assertEquals(Player.collectedKeys, 0);

        // Set player 2 spots above the key
        Maze.map[1][6].setElement("m");
        Maze.player = Maze.map[1][6];

        Player.collectKeys(Maze.player, 0, -1);
        // Check if the player has collected a key
        assertEquals(Player.collectedKeys, 0);
    }

    // Tests if the player will collect a key when it
    // moves onto it from any direction.
    // Works by placing player next to a key and then use collectKeys()
    // to check and collect (if any) keys in the player's moving direction.
    // Key 1 is located at (1, 4), the player will be placed at (1, 3)
    // to test up movement.
    @Test
    public void testCollectWithKeyW() {
        // Check that the player has not collected any keys
        assertEquals(Player.collectedKeys, 0);

        // Set player 1 below the key
        Maze.map[1][3].setElement("m");
        Maze.player = Maze.map[1][3];

        Player.collectKeys(Maze.player, 0, 1);

        // Check if the player has collected a key
        assertEquals(Player.collectedKeys, 1);
    }

    // Tests if the player will collect a key when it
    // moves onto it from any direction.
    // Works by placing player next to a key and then use collectKeys()
    // to check and collect (if any) keys in the player's moving direction.
    // Key 1 is located at (1, 4), the player will be placed at (2, 4)
    // to test left movement.
    @Test
    public void testCollectWithKeyA() {
        // Check that the player has not collected any keys
        assertEquals(Player.collectedKeys, 0);

        // Set player 1 to the right of the key
        Maze.map[2][4].setElement("m");
        Maze.player = Maze.map[2][4];

        Player.collectKeys(Maze.player, -1, 0);
        // Check if the player has collected a key
        assertEquals(Player.collectedKeys, 1);
    }

    // Tests if the player will collect a key when it
    // moves onto it from any direction.
    // Works by placing player next to a key and then use collectKeys()
    // to check and collect (if any) keys in the player's moving direction.
    // Key 1 is located at (1, 4), the player will be placed at (1, 5)
    // to test left movement.
    @Test
    public void testCollectWithKeyS() {
        // Check that the player has not collected any keys
        assertEquals(Player.collectedKeys, 0);

        // Set player 1 above the key
        Maze.map[1][5].setElement("m");
        Maze.player = Maze.map[1][5];

        Player.collectKeys(Maze.player, 0, -1);
        // Check if the player has collected a key
        assertEquals(Player.collectedKeys, 1);
    }

    // Tests if the player will collect a key when it
    // moves onto it from any direction.
    // Works by placing player next to a key and then use collectKeys()
    // to check and collect (if any) keys in the player's moving direction.
    // Key 1 is located at (1, 4), the player will be placed at (0, 4)
    // to test right movement.
    @Test
    public void testCollectWithKeyD() {
        // Check that the player has not collected any keys
        assertEquals(Player.collectedKeys, 0);

        // Set player 1 to the right of the key
        Maze.map[0][4].setElement("m");
        Maze.player = Maze.map[0][4];

        Player.collectKeys(Maze.player, 1, 0);
        // Check if the player has collected a key
        assertEquals(Player.collectedKeys, 1);
    }

    // Tests if a player can properly collect more than 1 key
    // Works by placing player next to a key and then use collectKeys()
    // to check and collect the first key.
    // Then the player is placed next to the second key where it will collect
    // Key 1 is located at (1, 4), the player will be placed at (0, 4)
    // Key 2 is located at (1, 16), the player will be placed at (0, 16)
    @Test
    public void testCollectBothKeys() {
        // Check that the player has not collected any keys
        assertEquals(Player.collectedKeys, 0);

        // Set player 1 to the right of the first key
        Maze.map[0][4].setElement("m");
        Maze.player = Maze.map[0][4];

        Player.collectKeys(Maze.player, 1, 0);
        // Check if the player has collected a key
        assertEquals(Player.collectedKeys, 1);

        // Set player 1 to the right of the second key
        Maze.map[0][16].setElement("m");
        Maze.player = Maze.map[0][16];

        Player.collectKeys(Maze.player, 1, 0);

        // Check if the player has collected 2 keys in total
        assertEquals(Player.collectedKeys, 2);
    }

    // Tests that the hasCollectedAllKeys() function opens
    // the end door when both keys are collected
    @Test
    public void testAllKeysOpenDoor() {
        // Check door is closed before collecting keys
        assertEquals(Maze.map[Maze.END_DOOR_X][Maze.END_DOOR_Y].getElement(), "e");

        // Emulate collecting 2 keys
        Player.collectedKeys = 2;
        // Call to update door status
        Player.hasCollectedAllKeys();

        // Recheck to confirm that the exit door has been opened
        // (should now be a blank character)
        assertEquals(Maze.map[Maze.END_DOOR_X][Maze.END_DOOR_Y].getElement(), " ");
    }


    //////--------------------------------------------------------------------------------------
//   Unable to test testDoorIsPassable() because the expected value of the maze
//   status is true which results in the program ending, thus there is no finished test.
//   Commenting out "Maze.gameStatus();" from line 46 in the Player class will allow
//   testDoorIsPassable() to run undisrupted by the exit in the program
//   (I have tested this function and it passed all the tests).
//////--------------------------------------------------------------------------------------
    // Tests that the player can go through the opened exit door
    // and that it will end the game
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();
    @Test
    public void testDoorIsPassable() {
        // Prevents program from exiting when testing
        exit.expectSystemExitWithStatus(0);

        // Check the status of the game before
        assertEquals(Maze.won, false);

        // Emulate collecting 2 keys
        Player.collectedKeys = 2;

        // Place player at the opened door and expect won status to become true
        Maze.map[Maze.END_DOOR_X][Maze.END_DOOR_Y].setElement("m");
        Maze.player = Maze.map[Maze.END_DOOR_X][Maze.END_DOOR_Y];

        // Call to update door status
        Player.hasCollectedAllKeys();

        // Expect the winning status to be true
        assertEquals(Maze.won, true);
    }
//////--------------------------------------------------------------------------------------

    // Tests to see if pressing the W key will move the player up
    @Test
    public void testWInput() {
        // Check player starts in the right position (also repeated in playerInitTest(),
        // but I added it here too for clarity and understanding)
        assertEquals(Maze.player.getX(), 12);
        assertEquals(Maze.player.getY(), 1);

        // Send W key to playerInput emulate the W key being pressed
        Player.playerInput("W");
        Maze.getPos();

        // Assert that the new position is correct
        assertEquals(Maze.player.getX(), 11);
        assertEquals(Maze.player.getY(), 1);
    }

    // Tests to see if pressing the A key will move the player left.
    // In this case the player will not move because the player's start position
    // has a wall to the left.
    @Test
    public void testAInput() {
        // Check player starts in the right position (also repeated in playerInitTest(),
        // but I added it here too for clarity and understanding)
        assertEquals(Maze.player.getX(), 12);
        assertEquals(Maze.player.getY(), 1);

        // Send A to playerInput emulate the A key being pressed
        Player.playerInput("A");
        Maze.getPos();

        // Assert that the new position is unchanged
        assertEquals(Maze.player.getX(), 12);
        assertEquals(Maze.player.getY(), 1);
    }

    // Tests to see if pressing the S key will move the player down.
    // In this case the player will not move because the player's start position
    // has a wall on the bottom.
    @Test
    public void testSInput() {
        // Check player starts in the right position (also repeated in playerInitTest(),
        // but I added it here too for clarity and understanding)
        assertEquals(Maze.player.getX(), 12);
        assertEquals(Maze.player.getY(), 1);

        // Send S to playerInput emulate the S key being pressed
        Player.playerInput("S");
        Maze.getPos();

        // Assert that the new position is unchanged
        assertEquals(Maze.player.getX(), 12);
        assertEquals(Maze.player.getY(), 1);
    }

    // Tests to see if pressing the D key will move the player right
    @Test
    public void testDInput() {
        // Check player starts in the right position (also repeated in playerInitTest(),
        // but I added it here too for clarity and understanding)
        assertEquals(Maze.player.getX(), 12);
        assertEquals(Maze.player.getY(), 1);

        // Send D to playerInput emulate the D key being pressed
        Player.playerInput("D");
        Maze.getPos();

        // Assert that the new position is correct
        assertEquals(Maze.player.getX(), 12);
        assertEquals(Maze.player.getY(), 2);
    }
}

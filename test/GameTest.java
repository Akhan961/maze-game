package com.mycompany.app;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @BeforeEach
    public void setup() {
        // Initializing the map and enemies so there is a medium to test in
        Maze.createMap();
        Enemy.createEnemies();
        Maze.getPos();
    }

    // Tests to determine whether changes made to the map are updated
    // in the image view
    @Test
    public void testRemoveTile() {
        // Call updateMap() to get a bass map
        Game.updateMap();

        // A position is picked, the element at this position is noted then
        // changed
        // Make sure that the expected wall is at this position
        assertEquals(Maze.map[3][6].getElement(), "*");
        // Now a gap should appear where the wall was
        Maze.map[3][6].setElement(" ");

        // Update the map before asserting
        Game.updateMap();
    }

    // Tests to determine whether adding an element will appear
    @Test
    public void testAddTile() {
        // Call updateMap() to get a bass map
        Game.updateMap();

        // A position is picked, the element at this position is noted then
        // changed
        // Make sure that the expected wall is at this position
        assertEquals(Maze.map[3][6].getElement(), "*");
        // Now a gap should appear where the wall was
        Maze.map[3][6].setElement(" ");

        // Update the map before asserting
        Game.updateMap();
//        assertEquals(Game.imgArray[3][6] , );
    }
}
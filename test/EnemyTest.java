package com.mycompany.app;

import java.util.ArrayList;
import org.junit.Rule; // for sys out issue
import org.junit.contrib.java.lang.system.ExpectedSystemExit; // for sys out issue
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class EnemyTest {


    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();


    @Before
    public  void mazeReset(){

        String mazeReset =
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

        createMap(mazeReset);
        Maze.getPos();
        Maze.allEnemies.clear();

    }

    public  void createMap(String maze ) {

        ArrayList<Block> blocks = new ArrayList<>();
        for (int i = 0; i < maze.length(); i++) {
            blocks.add(new Block(maze.charAt(i)));

        }
        int counter = 0;
        for (int i = 0; i < Maze.ROWS; i++) {
            for (int j = 0; j < Maze.COLUMNS; j++) {
                Maze.map[i][j] = blocks.get(counter);
                Maze.map[i][j].setX(i);
                Maze.map[i][j].setY(j);
                counter++;
            }
        }
    }


    @Test
    public void checkIfEnemiesAddedToArrayList(){
        // before creating enemies//
        assertEquals(0, Maze.allEnemies.size());

        //creating enemies//
        Maze.createMap();
        Enemy.createEnemies();

        //after creating enemies//
        assertEquals(3, Maze.allEnemies.size());
    }

    @Test
    public void checkIfGameLost(){
        Enemy.moveEnemies();
        assertFalse(Maze.lost);
    }


    public void createCollisionMap_B1(){

        String mapForTestingCollision_B1 =
                "******************" +
                "*bm  k           k*" +
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
                "s      *p      b*" +
                "***********e******";

        ArrayList<Block> blocks = new ArrayList<>();
        for (int i = 0; i < mapForTestingCollision_B1.length(); i++) {
            blocks.add(new Block(mapForTestingCollision_B1.charAt(i)));
        }

        int counter = 0;
        for (int i = 0; i < Maze.ROWS; i++) {
            for (int j = 0; j < Maze.COLUMNS; j++) {
                Maze.map[i][j] = blocks.get(counter);
                Maze.map[i][j].setX(i);
                Maze.map[i][j].setY(j);
                counter++;
            }
        }

    }


    @Test
    public void checkIfCollisionWithB1(){
        // set up//
        createCollisionMap_B1();
        Maze.getPos();
        Enemy.createEnemies();
        exit.expectSystemExitWithStatus(0); // added so now able to recognize its a test

        //creating barrel to check with actual pos//
        Block collisionBarrel1 = new Block("b");
        collisionBarrel1.setX(1);
        collisionBarrel1.setY(2);

        // move the enemy//
        Enemy.moveEnemies();
        Enemy.goRight(collisionBarrel1);

        // check if enemy is over top of player .. it is! //
        assertEquals(collisionBarrel1.getX(), Maze.player.getX());
        assertEquals(collisionBarrel1.getY(), Maze.player.getY());


        //check if lost updated to true//
        Enemy.checkIfCollision(collisionBarrel1);
        assertTrue(Maze.lost);


    }


    @Test
    public void checkingIfBarrelIsWhereItShouldBe_AfterCreationOfEnemies(){
        // set up //
        Maze.createMap();
        Enemy.createEnemies();

        // creating instances of Enemy1//
        Block barrelTester1 = new Block("b");
        barrelTester1.setX(1);
        barrelTester1.setY(1);
        // creating instances of Enemy2//
        Block barrelTester2 = new Block("b");
        barrelTester2.setX(7);
        barrelTester2.setY(9);
        // creating instances of Enemy3//
        Block barrelTester3 = new Block("b");
        barrelTester3.setX(12);
        barrelTester3.setY(16);

        // checking barrel positions//
        int barrel1Row = barrelTester1.getX();
        int barrel1Col = barrelTester1.getY();
        int barrel2Row = barrelTester2.getX();
        int barrel2Col = barrelTester2.getY();
        int barrel3Row = barrelTester3.getX();
        int barrel3Col = barrelTester3.getY();

        assertEquals(barrel1Row, Maze.allEnemies.get(0).getX()); // passes
        assertEquals(barrel1Col, Maze.allEnemies.get(0).getY()); // passes
        assertEquals(barrel2Row, Maze.allEnemies.get(1).getX()); // passes
        assertEquals(barrel2Col, Maze.allEnemies.get(1).getY()); // passes
        assertEquals(barrel3Row, Maze.allEnemies.get(2).getX()); // passes
        assertEquals(barrel3Col, Maze.allEnemies.get(2).getY()); // passes

    }

    @Test
    public void checkingBarrelElemIsCorrect(){
        //set up//
        Maze.createMap();
        Enemy.createEnemies();

        // checking barrel elements//
        String barrelElem = "b";

        assertEquals(barrelElem, Maze.allEnemies.get(0).getElement());
        assertEquals(barrelElem, Maze.allEnemies.get(1).getElement());
        assertEquals(barrelElem, Maze.allEnemies.get(2).getElement());
    }


    @Test
    public  void checkElemAfterMoveEnemy(){

        Enemy.createEnemies();
        Enemy.moveEnemies();

        String actualBarrelElemOnMap = Maze.map[Maze.allEnemies.get(0).getX()][Maze.allEnemies.get(0).getY()].getElement();
        assertEquals("b",actualBarrelElemOnMap);

    }


    //    ------------start of----------- testing if barrel1 moves Down (REG MAP) --> IT passes!!! --------------------------------- //

    @Test
    public void checkIfBarrel_1_MovesInRegMap_Down(){

        // set up//
        Enemy.createEnemies();

        //setting up barrelTester1//
        Block barrelTester1 = new Block("b");
        barrelTester1.setX(1);
        barrelTester1.setY(1);

        // now move the barrels and check if they moved to where they were suppose to//
        Enemy.moveEnemies();
        Enemy.goDown(barrelTester1);
        int expectedBarrel_1_XWhenGoes_Down = 2; // x position should increase by 1, so 1+1 = 2
        int actualBarrel_1_XWhenGoes_Down = Maze.map[Maze.allEnemies.get(0).getX()][Maze.allEnemies.get(0).getY()].getX();
        assertEquals(expectedBarrel_1_XWhenGoes_Down, actualBarrel_1_XWhenGoes_Down);

    }

//     ------------end of----------- testing if barrel1 moves Down (REG MAP) --> IT passes!!! --------------------------------- //


//     ------------start of----------- testing if barrel1 moves right --> IT WORKSS!!! --------------------------------- //

    public void createNewMapForTesting_Barrel1_Right(){

        // new map if player is next to barrel, it moves as suppose to
        String mazeTester1R =
                "******************" +
                "*b mk           k*" +
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
                "s       *p      b*" +
                "***********e******" ;

        ArrayList<Block> mazeTesterBlocks = new ArrayList<>();
        for (int i = 0; i < mazeTester1R.length(); i++) {
            mazeTesterBlocks.add(new Block(mazeTester1R.charAt(i)));

        }
        int counter = 0;
        for (int i = 0; i < Maze.ROWS; i++) {
            for (int j = 0; j < Maze.COLUMNS; j++) {
                Maze.map[i][j] = mazeTesterBlocks.get(counter);
                Maze.map[i][j].setX(i);
                Maze.map[i][j].setY(j);
                counter++;
            }
        }

    }

    @Test
    public void checkEnemyCanMove_UsingMapToTestEnemy_1_Right() {

        //set up//
        createNewMapForTesting_Barrel1_Right();
        Enemy.createEnemies();
        Maze.getPos();

        //setting up barrelTester1//
        Block barrelTester1 = new Block("b");
        barrelTester1.setX(1);
        barrelTester1.setY(1);

        //checking if able to locate new player position//
        assertEquals(1, Maze.player.getX());
        assertEquals(3, Maze.player.getY());

        // now move the enemy and check if it does//
        Enemy.moveEnemies();
        Enemy.goRight(barrelTester1);
        int expectedBarrel_1Col_WhenGoes_Right = 2;  // increase y by 1 so 1 + 1 = 2
        int actualBarrel_1Col_WhenGoes_Right = Maze.map[Maze.allEnemies.get(0).getX()][Maze.allEnemies.get(0).getY()].getY();
        assertEquals(expectedBarrel_1Col_WhenGoes_Right, actualBarrel_1Col_WhenGoes_Right);

    }

// ------------end of----------- testing if barrel1 moves Right --> it does!! --------------------------------- //

// ------------start of----------- testing if barrel1 moves Left --> it PASSESSS!!! --------------------------------- //


    public void createNewMapForTesting_Barrel1_Left(){

        // new map if player is next to barrel, it moves as suppose to
        String mazeTester1L =
                "******************" +
                "*m  bk           k*" +
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
                "s       *p      b*" +
                "***********e******" ;

        ArrayList<Block> mazeTesterBlocks = new ArrayList<>();
        for (int i = 0; i < mazeTester1L.length(); i++) {
            mazeTesterBlocks.add(new Block(mazeTester1L.charAt(i)));

        }
        int counter = 0;
        for (int i = 0; i < Maze.ROWS; i++) {
            for (int j = 0; j < Maze.COLUMNS; j++) {
                Maze.map[i][j] = mazeTesterBlocks.get(counter);
                Maze.map[i][j].setX(i);
                Maze.map[i][j].setY(j);
                counter++;
            }
        }

    }

    @Test
    public void checkEnemyCanMove_UsingMapToTestEnemy_1_Left(){
        //set up//
        createNewMapForTesting_Barrel1_Left(); // creates the new map for testing
        Enemy.createEnemies();
        Maze.getPos();

        assertEquals(1, Maze.map[Maze.allEnemies.get(0).getX()][Maze.allEnemies.get(0).getY()].getX());
        assertEquals(4, Maze.map[Maze.allEnemies.get(0).getX()][Maze.allEnemies.get(0).getY()].getY());

        Block barrelMapTester1 = new Block("b");
        barrelMapTester1.setX(1);
        barrelMapTester1.setY(4);

        // now call goLeft and check enemy position//
        Enemy.moveEnemies();
        Enemy.goLeft(barrelMapTester1);
        int expectedBarrel_1Col_WhenGoes_Left = 3; // decrease y by 1 so 4 - 1 = 3
        int actualBarrel_1Col_WhenGoes_Left = Maze.map[Maze.allEnemies.get(0).getX()][Maze.allEnemies.get(0).getY()].getY();
        assertEquals(expectedBarrel_1Col_WhenGoes_Left, actualBarrel_1Col_WhenGoes_Left); // passes

    }


//    ------------end of-------- testing if barrel1 moves Left --> it PASSESSS!!!--------------------------------- //


//     ------------start of-------- testing if barrel1 moves Up -->  IT WORKSSSSSS!!! --------------------------------- //


    public void createNewMapForTesting_Barrel1_Up(){

        // new map if player is next to barrel, it moves as suppose to
        String mazeTester1U =
                "******************" +
                "*   k           k*" +
                "* * * *        * *" +
                "* *  * ********* *" +
                "* *         p  * *" +
                "*c  ** p *  ** * *" +
                "* * **  c   *  c *" +
                "*m *     *b  * ** *" +
                "*   ** ***       *" +
                "*b* *       * ** *" +
                "* * *  c c* *  * *" +
                "* * *   * * ** * *" +
                "s       *p      b*" +
                "***********e******" ;

        ArrayList<Block> mazeTesterBlocks = new ArrayList<>();
        for (int i = 0; i < mazeTester1U.length(); i++) {
            mazeTesterBlocks.add(new Block(mazeTester1U.charAt(i)));

        }
        int counter = 0;
        for (int i = 0; i < Maze.ROWS; i++) {
            for (int j = 0; j < Maze.COLUMNS; j++) {
                Maze.map[i][j] = mazeTesterBlocks.get(counter);
                Maze.map[i][j].setX(i);
                Maze.map[i][j].setY(j);
                counter++;
            }
        }

    }


    @Test
    public void checkEnemyCanMove_UsingMapToTestEnemy_1_Up() {
        //set up//
        createNewMapForTesting_Barrel1_Up(); // creates the new map for testing
        Enemy.createEnemies();
        Maze.getPos();
        Enemy.moveEnemies();

        //setting up barrelTester1//
        Block barrelTester1 = new Block("b");
        barrelTester1.setX(8);
        barrelTester1.setY(2);

        // check to see if player pos is updated with new map//
        assertEquals(7, Maze.player.getX());
        assertEquals(1, Maze.player.getY());

        Enemy.goUp(barrelTester1);
        int expectedBarrel_1Row_WhenGoes_Up = 7 ;// decrease x by 1 so 9 -1 = 8
        int actualBarrel_1Row_WhenGoes_Up = Maze.map[Maze.allEnemies.get(0).getX()][Maze.allEnemies.get(0).getY()].getX();
        assertEquals(expectedBarrel_1Row_WhenGoes_Up, actualBarrel_1Row_WhenGoes_Up);


    }

    //     ------------end of-------- testing if barrel1 moves Up -->  IT WORKSSSSSS!!!--------------------------------- //

    //     -  Unit test for [barrel 2] begins here -    //


// ------------start of----------- testing if barrel2 moves Right --> it does! --------------------------------- //

    public void createNewMapForTesting_Barrel2_Right(){

        // new map if player is somewhere random
        String mazeTester1 =
                "******************" +
                        "*b  k           k*" +
                        "* * * *        * *" +
                        "* *  * ********* *" +
                        "* *         p  * *" +
                        "*c  ** p *  ** * *" +
                        "* * **  c   *  c *" +
                        "* *     *b m* ** *" + // moved it so the m player is here//
                        "*   ** ***       *" +
                        "* * *       * ** *" +
                        "* * *  c c* *  * *" +
                        "* * *   * * ** * *" +
                        "s       *p      b*" + // main player was here, right of s, before//
                        "***********e******" ;


        ArrayList<Block> mazeTesterBlocks = new ArrayList<>();
        for (int i = 0; i < mazeTester1.length(); i++) {
            mazeTesterBlocks.add(new Block(mazeTester1.charAt(i)));

        }
        int counter = 0;
        for (int i = 0; i < Maze.ROWS; i++) {
            for (int j = 0; j < Maze.COLUMNS; j++) {
                Maze.map[i][j] = mazeTesterBlocks.get(counter);
                Maze.map[i][j].setX(i);
                Maze.map[i][j].setY(j);
                counter++;
            }
        }


    }

    @Test
    public void checkEnemyCanMove_UsingMapToTestEnemy_2_Right(){

        //set up//
        createNewMapForTesting_Barrel2_Right();
        Enemy.createEnemies();
        Maze.getPos();

        // creating barrel
        Block barrelTester2 = new Block("b");
        barrelTester2.setX(7);
        barrelTester2.setY(9);

        // able to locate the player's new position with updated map//
        assertEquals(7, Maze.player.getX());
        assertEquals(11, Maze.player.getY());

        // move and check if position is correct//
        Enemy.moveEnemies();
        Enemy.goRight(barrelTester2);
        int expectedBarrel_2Col_WhenGoes_Left = 10; // increase y by 1 so 9 + 1 = 10
        int actualBarrel_2Col_WhenGoes_Left = Maze.map[Maze.allEnemies.get(1).getX()][Maze.allEnemies.get(1).getY()].getY();
        assertEquals(expectedBarrel_2Col_WhenGoes_Left, actualBarrel_2Col_WhenGoes_Left); // passes

    }

//     ------------end of----------- testing if barrel2 moves Right --> it does! --------------------------------- //

    //    ------------start of----------- testing if barrel2 moves Left --> it works!! --------------------------------- //


    public void createNewMapForTesting_Barrel2_Left(){

        // new map if player is somewhere random
        String mazeTester2L =
                "******************" +
                        "*b  k           k*" +
                        "* * * *        * *" +
                        "* *  * ********* *" +
                        "* *         p  * *" +
                        "*c  ** p *  ** * *" +
                        "* * **  c   *  c *" +
                        "* *     *m b* ** *" +
                        "*   ** ***       *" +
                        "* * *       * ** *" +
                        "* * *  c c* *  * *" +
                        "* * *   * * ** * *" +
                        "s       *p      b*" +
                        "***********e******" ;


        ArrayList<Block> mazeTesterBlocks = new ArrayList<>();
        for (int i = 0; i < mazeTester2L.length(); i++) {
            mazeTesterBlocks.add(new Block(mazeTester2L.charAt(i)));

        }
        int counter = 0;
        for (int i = 0; i < Maze.ROWS; i++) {
            for (int j = 0; j < Maze.COLUMNS; j++) {
                Maze.map[i][j] = mazeTesterBlocks.get(counter);
                Maze.map[i][j].setX(i);
                Maze.map[i][j].setY(j);
                counter++;
            }
        }


    }

    @Test
    public void checkEnemyCanMove_UsingMapToTestEnemy_2_Left() {

        //set up//
        createNewMapForTesting_Barrel2_Left();
        Enemy.createEnemies();
        Maze.getPos();

        // created these to find barrel pos on map since moved in new map//
        assertEquals(7, Maze.map[Maze.allEnemies.get(1).getX()][Maze.allEnemies.get(1).getY()].getX());
        assertEquals(11, Maze.map[Maze.allEnemies.get(1).getX()][Maze.allEnemies.get(1).getY()].getY() );

        // created barrel with new map coordinates //
        Block barrelMapTester2 = new Block("b");
        barrelMapTester2.setX(7);
        barrelMapTester2.setY(11);

        // move and check if moved correctly//
        Enemy.moveEnemies();
        Enemy.goLeft(barrelMapTester2);
        int expectedBarrel_2Col_WhenGoes_Left = 10 ;// decrease y by 1 so 11-1 = 10
        int actualBarrel_2Col_WhenGoes_Left = Maze.map[Maze.allEnemies.get(1).getX()][Maze.allEnemies.get(1).getY()].getY();
        assertEquals(expectedBarrel_2Col_WhenGoes_Left, actualBarrel_2Col_WhenGoes_Left);

    }

//     ------------end of----------- testing if barrel2 moves Left --> it works!!  --------------------------------- //


//        ------------start of----------- testing if barrel2 moves Up --> it works! --------------------------------- //


    public void createMapForTesting_Barrel2_Up(){

        String mapForTesting_B2U =
                "******************" +
                        "*b  k           k*" +
                        "* * * *        * *" +
                        "* *  * ********* *" +
                        "* *         p  * *" +
                        "*c  ** p *m ** * *" +
                        "* * **  c   *  c *" +
                        "* *     * b * ** *" +
                        "*   ** ***       *" +
                        "* * *       * ** *" +
                        "* * *  c c* *  * *" +
                        "* * *   * * ** * *" +
                        "s       *p      b*" +
                        "***********e******";

        ArrayList<Block> blocks = new ArrayList<>();
        for (int i = 0; i < mapForTesting_B2U.length(); i++) {
            blocks.add(new Block(mapForTesting_B2U.charAt(i)));
        }


        int counter = 0;
        for (int i = 0; i < Maze.ROWS; i++) {
            for (int j = 0; j < Maze.COLUMNS; j++) {
                Maze.map[i][j] = blocks.get(counter);
                Maze.map[i][j].setX(i);
                Maze.map[i][j].setY(j);
                counter++;
            }
        }

    }

    @Test
    public void checkEnemyMove_UsingTestingMap_Barrel2_Up(){
        //set up //
        createMapForTesting_Barrel2_Up();
        Enemy.createEnemies();
        Maze.getPos();

        // created these to find barrel pos on map since moved in new map//
        assertEquals(7, Maze.map[Maze.allEnemies.get(1).getX()][Maze.allEnemies.get(1).getY()].getX());
        assertEquals(10, Maze.map[Maze.allEnemies.get(1).getX()][Maze.allEnemies.get(1).getY()].getY());

        // creating barrel from new map coordinates//
        Block barrel2MapTester = new Block("b");
        barrel2MapTester.setX(6);
        barrel2MapTester.setY(10);

        // move and check its location //
        Enemy.moveEnemies();
        Enemy.goUp(barrel2MapTester);
        int expectedBarrel_2Row_WhenGoes_Up = 6 ; // decrease x by 1 so 7 -1 = 6
        int actualBarrel_2Row_WhenGoes_Up = Maze.map[Maze.allEnemies.get(1).getX()][Maze.allEnemies.get(1).getY()].getX();
        assertEquals(expectedBarrel_2Row_WhenGoes_Up, actualBarrel_2Row_WhenGoes_Up);

    }

//    ------------end of----------- testing if barrel2 moves Up --> it works! --------------------------------- //

//    ------------start of----------- testing if barrel2 moves Down --> it WORKS! --------------------------------- //

    public void createMapForTesting_Barrel2_Down(){
        String mapForTesting_B2D =
                "******************" +
                        "*b  k           k*" +
                        "* * * *        * *" +
                        "* *  * ********* *" +
                        "* *         p  * *" +
                        "*c  ** p *b ** * *" +
                        "* * **  c   *  c *" +
                        "* *     * m * ** *" +
                        "*   ** ***       *" +
                        "* * *       * ** *" +
                        "* * *  c c* *  * *" +
                        "* * *   * * ** * *" +
                        "s       *p      b*" +
                        "***********e******";

        ArrayList<Block> blocks = new ArrayList<>();
        for (int i = 0; i < mapForTesting_B2D.length(); i++) {
            blocks.add(new Block(mapForTesting_B2D.charAt(i)));
        }


        int counter = 0;
        for (int i = 0; i < Maze.ROWS; i++) {
            for (int j = 0; j < Maze.COLUMNS; j++) {
                Maze.map[i][j] = blocks.get(counter);
                Maze.map[i][j].setX(i);
                Maze.map[i][j].setY(j);
                counter++;
            }
        }

    }


    @Test
    public void checkEnemyMove_UsingTestingMap_Barrel2_Down(){
        //set up//
        createMapForTesting_Barrel2_Down();
        Enemy.createEnemies();
        Maze.getPos();

        // created these to find barrel pos on map since moved in new map//
        assertEquals(5, Maze.map[Maze.allEnemies.get(1).getX()][Maze.allEnemies.get(1).getY()].getX());
        assertEquals(10,Maze.map[Maze.allEnemies.get(1).getX()][Maze.allEnemies.get(1).getY()].getY() );

        // creating barrel with new map pos//
        Block barrel2MapTester = new Block("b");
        barrel2MapTester.setX(5);
        barrel2MapTester.setY(10);

        // move and check positions//
        Enemy.moveEnemies();
        Enemy.goDown(barrel2MapTester);
        int expectedBarrel_2Row_WhenGoes_Down = 6; //increase x pos by 1 so 5 + 1 = 6
        int actualBarrel_2Row_WhenGoes_Down = Maze.map[Maze.allEnemies.get(1).getX()][Maze.allEnemies.get(1).getY()].getX();
        assertEquals(expectedBarrel_2Row_WhenGoes_Down, actualBarrel_2Row_WhenGoes_Down);

    }


//    ------------end of----------- testing if barrel2 moves Down --> It WORKS! --------------------------------- //


    //    - Unit tests for  [ barrel 3 ]  starts here - //



//    ------------start of----------- testing if barrel3 moves Left (RegMAP) --> IT passes!!! --------------------------------- //


    @Test
    public void checkIfBarrel_3_MovesInRegMap_Left(){

        // set up//
        Maze.createMap();
        Maze.getPos();
        Enemy.createEnemies();

        // creating an instance for barrel //
        Block barrelTester3 = new Block("b");
        barrelTester3.setX(12);
        barrelTester3.setY(16);

        // checking conditional if statements in MoveEnemy//
        assertFalse(Maze.player.getX() < barrelTester3.getX());
        assertEquals(Maze.player.getX(),barrelTester3.getX() );


        Enemy.moveEnemies();
        Enemy.goLeft(barrelTester3);
        int expectedBarrel_3Col_WhenGoes_Left = 15; // decrease y by 1 so 16 - 1 = 15
        int actualBarrel_3Col_WhenGoes_Left = Maze.map[Maze.allEnemies.get(2).getX()][Maze.allEnemies.get(2).getY()].getY();
        assertEquals(expectedBarrel_3Col_WhenGoes_Left, actualBarrel_3Col_WhenGoes_Left);



    }


//     ------------end of----------- testing if barrel3 moves Left --> IT passes!!! --------------------------------- //



//     ------------start of----------- testing if barrel3 moves Right --> IT WORKS! --------------------------------- //

    public void createMapForTesting_Barrel3_Right(){

        String mapForTesting_B3R =
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
                "s       *p   b  m*" +
                "***********e******";

        ArrayList<Block> blocks = new ArrayList<>();
        for (int i = 0; i < mapForTesting_B3R.length(); i++) {
            blocks.add(new Block(mapForTesting_B3R.charAt(i)));
        }

        int counter = 0;
        for (int i = 0; i < Maze.ROWS; i++) {
            for (int j = 0; j < Maze.COLUMNS; j++) {
                Maze.map[i][j] = blocks.get(counter);
                Maze.map[i][j].setX(i);
                Maze.map[i][j].setY(j);
                counter++;
            }
        }


    }

    @Test
    public void checkEnemyMove_UsingTestingMap_Barrel3_Right(){
        //set up//
        createMapForTesting_Barrel3_Right();
        Enemy.createEnemies();
        Maze.getPos();

        // created these to find barrel pos on map since moved in new map//
        assertEquals(12, Maze.map[Maze.allEnemies.get(2).getX()][Maze.allEnemies.get(2).getY()].getX());
        assertEquals(13, Maze.map[Maze.allEnemies.get(2).getX()][Maze.allEnemies.get(2).getY()].getY());

        // created barrel based off new map pos//
        Block barrelMapTester3 = new Block("b");
        barrelMapTester3.setX(12);
        barrelMapTester3.setY(13);

        // move and check its location//
        Enemy.moveEnemies();
        Enemy.goRight(barrelMapTester3);
        int expectedBarrel_3Col_WhenGoes_Right = 14; // increase col by 1 so 13 + 1 = 14
        int actualBarrel_3Col_WhenGoes_Right = Maze.map[Maze.allEnemies.get(2).getX()][Maze.allEnemies.get(2).getY()].getY();
        assertEquals(expectedBarrel_3Col_WhenGoes_Right, actualBarrel_3Col_WhenGoes_Right);

    }


//     ------------end of----------- testing if barrel3 moves Right --> IT WORKS! --------------------------------- //


//    ------------start of----------- testing if barrel3 moves Up -->  it works! --------------------------------- //

    public void createMapForTesting_Barrel3_Up(){

        String mapForTesting_B3U =
                "******************" +
                "*b  k           k*" +
                "* * * *        * *" +
                "* *  * ********* *" +
                "* *         p  * *" +
                "*c  ** p *  ** * *" +
                "* * **  c   *  c *" +
                "* *     *b  * ** *" +
                "*   ** ***       *" +
                "* * *       * **m*" +
                "* * *  c c* *  * *" +
                "* * *   * * ** * *" +
                "s       *p      b*" +
                "***********e******";

        ArrayList<Block> blocks = new ArrayList<>();
        for (int i = 0; i < mapForTesting_B3U.length(); i++) {
            blocks.add(new Block(mapForTesting_B3U.charAt(i)));
        }

        int counter = 0;
        for (int i = 0; i < Maze.ROWS; i++) {
            for (int j = 0; j < Maze.COLUMNS; j++) {
                Maze.map[i][j] = blocks.get(counter);
                Maze.map[i][j].setX(i);
                Maze.map[i][j].setY(j);
                counter++;
            }
        }

    }

    @Test
    public void checkEnemyMove_UsingTestingMap_Barrel3_Up(){
        // set up //
        createMapForTesting_Barrel3_Up();
        Enemy.createEnemies();
        Maze.getPos();

        // created these to find barrel pos on map since moved in new map//
        assertEquals(12, Maze.map[Maze.allEnemies.get(2).getX()][Maze.allEnemies.get(2).getY()].getX());
        assertEquals(16, Maze.map[Maze.allEnemies.get(2).getX()][Maze.allEnemies.get(2).getY()].getY());

        // creating barrel off new map pos//
        Block barrelMapTester3U = new Block("b");
        barrelMapTester3U.setX(12);
        barrelMapTester3U.setY(16);

        // move and check if moved position is correct//
        Enemy.moveEnemies();
        Enemy.goUp(barrelMapTester3U);
        int expectedBarrel_3Row_WhenGoes_Up = 11; // decrease x by 1 sp 12 - 1 = 11
        int actualBarrel_3Row_WhenGoes_Up = Maze.map[Maze.allEnemies.get(2).getX()][Maze.allEnemies.get(2).getY()].getX();
        assertEquals(expectedBarrel_3Row_WhenGoes_Up,actualBarrel_3Row_WhenGoes_Up);

    }

//    ------------end of----------- testing if barrel3 moves Up --> it works!! --------------------------------- //

//    ------------start of----------- testing if barrel3 moves Down --> it works! --------------------------------- //


    public void createMapForTesting_Barrel3_Down(){

        String mapForTesting_B3D =
                "******************" +
                "*b  k           k*" +
                "* * * *        * *" +
                "* *  * ********* *" +
                "* *         p  * *" +
                "*c  ** p *  ** * *" +
                "* * **  c   *  c *" +
                "* *     *b  * ** *" +
                "*   ** ***       *" +
                "* * *       * **b*" +
                "* * *  c c* *  * *" +
                "* * *   * * ** * *" +
                "s       *p      m*" +
                "***********e******";

        ArrayList<Block> blocks = new ArrayList<>();
        for (int i = 0; i < mapForTesting_B3D.length(); i++) {
            blocks.add(new Block(mapForTesting_B3D.charAt(i)));
        }

        int counter = 0;
        for (int i = 0; i < Maze.ROWS; i++) {
            for (int j = 0; j < Maze.COLUMNS; j++) {
                Maze.map[i][j] = blocks.get(counter);
                Maze.map[i][j].setX(i);
                Maze.map[i][j].setY(j);
                counter++;
            }
        }

    }

    @Test
    public void checkEnemyMove_UsingTestingMap_Barrel3_Down(){
        // set up //
        createMapForTesting_Barrel3_Down();
        Enemy.createEnemies();
        Maze.getPos();

        // created these to find barrel pos on map since moved in new map//
        assertEquals(9, Maze.map[Maze.allEnemies.get(2).getX()][Maze.allEnemies.get(2).getY()].getX());
        assertEquals(16,Maze.map[Maze.allEnemies.get(2).getX()][Maze.allEnemies.get(2).getY()].getY());

        // created barrel based off new maps pos//
        Block barrelMapTester3D = new Block("b");
        barrelMapTester3D.setX(9);
        barrelMapTester3D.setY(16);

        // move and check if the moved pos is the same//
        Enemy.moveEnemies();
        Enemy.goDown(barrelMapTester3D);
        int expectedBarrel_3Row_WhenGoes_Down = 10; // increase x by 1 so 9 + 1 = 10
        int actualBarrel_3Row_WhenGoes_Down = Maze.map[Maze.allEnemies.get(2).getX()][Maze.allEnemies.get(2).getY()].getX();
        assertEquals(expectedBarrel_3Row_WhenGoes_Down,actualBarrel_3Row_WhenGoes_Down);

    }

//    ------------end of----------- testing if barrel3 moves Down -->  it works!! --------------------------------- //

}



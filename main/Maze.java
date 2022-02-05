package com.mycompany.app;

import java.util.*;

public class Maze {


    public static int ROWS = 14;
    public static int COLUMNS = 18;
    public static Block player;

    public static int END_DOOR_X; // exit door row position
    public static int END_DOOR_Y; // exit door column position

    public static ArrayList<Block> allEnemies = new ArrayList<>();
    public static Block[][] map = new Block[ROWS][COLUMNS];

    public static boolean lost = false;
    public static boolean won = false;

    /*
     * This method creates the map.
     *      Legend:
     *          * = walls
     *          b = barrels
     *          k = keys
     *          p = punishment
     *          c = coins
     *          s = starting door
     *          e = ending door
     *          m = mario

     * @see     the map before JavaFx
     */

    public static String maze =
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

    // turns each string on the map to a Block class


    /*
     * This method creates the map.
     *      1.) It creates a new arrayList, named "blocks", and adds a new block to the arrayList
     *          by retrieving the character at index position
     *
     * @param   nothing    just creates the map when called
     * @return  nothing    the map created
     * @see
     */
    public static void createMap() {

        ArrayList<Block> blocks = new ArrayList<>();
        for (int i = 0; i < maze.length(); i++) {
            blocks.add(new Block(maze.charAt(i)));

        }
        int counter = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                map[i][j] = blocks.get(counter);
                map[i][j].setX(i);
                map[i][j].setY(j);
                counter++;
            }
        }
    }


    /*
     * This is primary a Debug function
     *         Its meant to print a version of the map in the console
     *
     * @param   element     just prints the map when called
     * @return  map         sets the element position to the entered element
     * @see     map         the map will become visible through terminal
     */
    public static void printMap(){
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print(map[i][j].getElement());
            }
            System.out.println();
        }
    }



    /*
     * This method's purpose is to finds the player and ending door on the map and saves it
     *      searches through the 2d array or matrix
     *      locates the symbol "m" for Mario, the main character.
     *          and stores it in "player" for future use
     *      locates the symbol "e" for exit door
     *          and stores the row of the exit door in END_DOOR_X
     *          and stores the column of the exit door in END_DOOR_Y
     *
     * @param   nothing      does not take parameters
     * @return  nothing      locates m and e and assignment them to variables for future use
     * @see     position     the position m and c
     */
    public static void getPos() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (map[i][j].getElement().equals("m")) {
                    player = map[i][j];
                }
                if (map[i][j].getElement().equals("e")){
                    END_DOOR_X = i;
                    END_DOOR_Y = j;
                }
            }
        }
    }


    /*
     * This method's purpose is to: checks if the win or lose condition has been met and display a message accordingly
     *      created boolean "last" and "won" above, and sets it to true
     *      so it acts on quitting or winning the game
     *
     * @param   nothing      does not take parameters
     * @return  nothing      quits teh game if player lost
     * @see     gameStatus   will display wheter you have lost or won the game
     */
    public static void gameStatus(){
        if (lost){
//            System.out.println("You Lose");
            System.exit(0);
        }
        if (won){
//            System.out.println("Congrats, You Win!");
            System.exit(0);
        }
    }
}
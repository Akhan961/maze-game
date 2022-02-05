package com.mycompany.app;

public class Player {
    /*
     * These are attributes for this class
     *      public and static so they are easily accessable
     *
     * playerScore = current player score
     * numKeys = total number of keys
     * collectedKeys = number of keys collected by the player
     */
    public static int playerScore = 50;
    public static final int numKeys = 2;
    public static int collectedKeys = 0;

    /*
     * collectKeys is responsible for detecting if the next move
     * will contain a coin and update the collected key count if so
     *
     * @param   player      needs Block player to use the current player position
     * @param   directionX  X displacement for the player
     * @param   directionY  Y displacement for the player
     * @return  nothing     increments the collected keys if a key is found
     *                      in the next position
     */
    public static void collectKeys(Block player, int directionX, int directionY) {
        if (Maze.map[(player.getX() + directionX)][player.getY() + directionY].getElement().equals("k")) {
            collectedKeys++;
        }
    }

    /*
     * hasCollectedAllKeys checks if all the keys have been collected and sets the exit
     *      door element to an empty tile. Also checks if the player is at the end of the game
     *
     * @param   none      The map and player are already accessible
     * @return  nothing   the game is either ended, exit door disappears, or nothing happens.
     *                          No return value needed
     *
     */
    public static void hasCollectedAllKeys() {
        if(numKeys == collectedKeys) {
            if(Maze.map[Maze.END_DOOR_X][Maze.END_DOOR_Y].getElement().equals("e")) {
                Maze.map[Maze.END_DOOR_X][Maze.END_DOOR_Y].setElement(" ");
            }
            if(Maze.player.getX() == Maze.END_DOOR_X && Maze.player.getY() == Maze.END_DOOR_Y){
                Maze.won = true;
                Maze.gameStatus();
            }
        }
    }

    /*
     * PlayerInput is responsible for retrieving the player's key pressed and checking
     *      whether or not it is WASD. If is is a WASD key then the players position its moving to
     *      is checked against barrels, coins, punishment (fire), and keys; acting accordingly
     *
     * @param   input      holds the player's input
     * @return  nothing    moves the player, subtracts points, or ends game depending on
     *                     where the player will land
     */
    public static void playerInput(String input){
        if(input.equals("W") || input.equals("w")){
            collectKeys(Maze.player, -1, 0);

            //moves character up if there are no walls or doors
            if (!Maze.map[Maze.player.getX()-1][Maze.player.getY()].getElement().equals("*") &&
                    !Maze.map[Maze.player.getX()-1][Maze.player.getY()].getElement().equals("s") &&
                    !Maze.map[Maze.player.getX()-1][Maze.player.getY()].getElement().equals("e")) {
                if (Maze.map[Maze.player.getX()-1][Maze.player.getY()].getElement().equals("c")) {
                    playerScore = Punishment.addPoints(playerScore);
                }
                // updated score if punishment//
                if(Maze.map[Maze.player.getX()-1][Maze.player.getY()].getElement().equals("p")){
                    playerScore = Punishment.deductPoints(playerScore);
                }
                Maze.map[Maze.player.getX()-1][Maze.player.getY()].setElement("m");
                if (!Maze.map[Maze.player.getX()][Maze.player.getY()].getElement().equals("b")) {
                    Maze.map[Maze.player.getX()][Maze.player.getY()].setElement(" ");
                }
            }
        }
        if(input.equals("A") || input.equals("a")) {
            collectKeys(Maze.player, 0, -1);

            //moves character left if there are no walls or doors
            if (!Maze.map[Maze.player.getX()][Maze.player.getY()-1].getElement().equals("*") &&
                    !Maze.map[Maze.player.getX()][Maze.player.getY()-1].getElement().equals("s") &&
                    !Maze.map[Maze.player.getX()][Maze.player.getY()-1].getElement().equals("e")) {
                if (Maze.map[Maze.player.getX()][Maze.player.getY()-1].getElement().equals("c")) {
                    playerScore = Punishment.addPoints(playerScore);
                }
                // updated score if punishment//
                if(Maze.map[Maze.player.getX()][Maze.player.getY()-1].getElement().equals("p")){
                    playerScore = Punishment.deductPoints(playerScore);
                }
                Maze.map[Maze.player.getX()][Maze.player.getY() - 1].setElement("m");
                if (!Maze.map[Maze.player.getX()][Maze.player.getY()].getElement().equals("b")) {
                    Maze.map[Maze.player.getX()][Maze.player.getY()].setElement(" ");
                }
            }
        }
        if(input.equals("S") || input.equals("s")){
            collectKeys(Maze.player, 1, 0);

            //moves character down if there are no walls or doors
            if (!Maze.map[Maze.player.getX()+1][Maze.player.getY()].getElement().equals("*") &&
                    !Maze.map[Maze.player.getX()+1][Maze.player.getY()].getElement().equals("s") &&
                    !Maze.map[Maze.player.getX()+1][Maze.player.getY()].getElement().equals("e")) {
                if (Maze.map[Maze.player.getX()+1][Maze.player.getY()].getElement().equals("c")) {
                    playerScore = Punishment.addPoints(playerScore);
                }
                // updated score if punishment//
                if(Maze.map[Maze.player.getX()+1][Maze.player.getY()].getElement().equals("p")){
                    playerScore = Punishment.deductPoints(playerScore);
                }
                Maze.map[Maze.player.getX() + 1][Maze.player.getY()].setElement("m");
                if (!Maze.map[Maze.player.getX()][Maze.player.getY()].getElement().equals("b")) {
                    Maze.map[Maze.player.getX()][Maze.player.getY()].setElement(" ");
                }
            }
        }
        if(input.equals("D") || input.equals("d")){
            collectKeys(Maze.player, 0, 1);

            //moves character right if there are no walls or doors
            if (!Maze.map[Maze.player.getX()][Maze.player.getY()+1].getElement().equals("*") &&
                    !Maze.map[Maze.player.getX()][Maze.player.getY()+1].getElement().equals("s") &&
                    !Maze.map[Maze.player.getX()][Maze.player.getY()+1].getElement().equals("e")) {
                if (Maze.map[Maze.player.getX()][Maze.player.getY()+1].getElement().equals("c")) {
                    playerScore = Punishment.addPoints(playerScore);
                }
                // updated score if punishment//
                if(Maze.map[Maze.player.getX()][Maze.player.getY()+1].getElement().equals("p")){
                    playerScore = Punishment.deductPoints(playerScore);
                }
                Maze.map[Maze.player.getX()][Maze.player.getY() + 1].setElement("m");
                if (!Maze.map[Maze.player.getX()][Maze.player.getY()].getElement().equals("b")) {
                    Maze.map[Maze.player.getX()][Maze.player.getY()].setElement(" ");
                }
            }
        }
        Punishment.negScore(playerScore);
    }
}



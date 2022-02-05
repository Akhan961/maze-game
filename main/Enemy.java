package com.mycompany.app;

public class Enemy {

    public static boolean move = false;


    /*
     * This method's purpose is to: find the barrels on the map and save them to an arraylist
     *      searches through the 2d array or matrix
     *      locates the symbol "b" for barrel, the moving enemy.
     *          and creates a new block with the element b as its parameter
     *          and stores the row and column of the barrel in an arrayList named "allEnemies" player" for future use
     *
     * @param   nothing      does not take parameters
     * @return  nothing      will have added the enemies to the arraylist, need for future use
     * @see     allEnemies     the barrels will be created
     */
    public static void createEnemies(){
        for(int i = 0; i < Maze.ROWS; i++){
            for(int j = 0; j < Maze.COLUMNS; j++){
                if(Maze.map[i][j].getElement().equals("b")){
                    Block barrel = new Block('b');
                    barrel.setY(j);
                    barrel.setX(i);
                    Maze.allEnemies.add(barrel);
                }
            }
        }
    }



    /*
     * This method's purpose is to: moves the barrels closer to the player each tick
     *          1.) First check is to see if the barrel position x and y match the players position x and y
     *              a.) if matched, means player hits the moving enemy thus lost
     *              b.) sets the lost value to true which gets used later to check the status
     *          2.) Second seconds are to see if the player x is greater than or less than barrel x
     *              a.) if player row position greater than barrel row position --> barrel moves down, map updated position
     *              b.) if player row position less than barrel row position --> barrel moves up, map update position
     *          3.) if player row position equal to barrel row position --> check the columns
     *              a.) if player column position is greater than barrel column position --> barrel moves right, map position
     *              b.) if player column position is less than barrel column position --> barrel moves left, map updates position
     *
     *
     * @param   nothing      when called the barrels will move
     * @return  nothing      the barrel will move
     * @see     barrels     moving barrels
     */
    public static void moveEnemies() {
        for (Block barrel : Maze.allEnemies) {
            // checks for collision over same cell//
            checkIfCollision(barrel);
            // go directions//
            move = false;

            goDown(barrel);
            if (!move){
                goUp(barrel);
            }
            if (!move){
                goRight(barrel);
            }
            if (!move) {
                goLeft(barrel);
            }

        }
    }


    /*
     * This method's purpose is to: move the barrel down if there are no walls
     *              if there is another element such as a reward, barrel is able to go through that
     *              uses temp from Block to temporary replace it the reward and bring back after moved
     *
     *
     * @param   barrel      when called the barrels will move Down
     * @return  nothing      the barrel will move
     * @see     barrels     moving barrels
     */
    public static void goDown(Block barrel){

        if ( goDownConditionTrue(barrel) ){
            move = true;

            if (Maze.map[barrel.getX() + 1][barrel.getY()].getElement().equals(" ")) {
                if(!Maze.map[barrel.getX()][barrel.getY()].getTemp().equals(" ") && !Maze.map[barrel.getX()][barrel.getY()].getTemp().equals("m")){
                    Maze.map[barrel.getX()][barrel.getY()].removeTemp();
                }
                else{
                    Maze.map[barrel.getX()][barrel.getY()].setElement(" ");
                }
                Maze.map[barrel.getX() + 1][barrel.getY()].setElement("b");
            }
            else {
                Maze.map[barrel.getX()][barrel.getY()].setElement(" ");
                Maze.map[barrel.getX()+1][barrel.getY()].setTemp("b");
            }
            barrel.setX(barrel.getX() + 1);
        }
    } // end of go down



    /*
     * This method's purpose is to: check is the barrel is allowed to move Down
     *                      if its in good relation to the player and no wall or other barrel meets
     *
     *
     * @param   barrel      when called the barrels will move Down
     * @return  nothing      the barrel will move
     * @see     barrels     moving barrels
     */
    public static boolean goDownConditionTrue(Block barrel){
        if( (Maze.player.getX() > barrel.getX()
                    && (!Maze.map[barrel.getX() + 1][barrel.getY()].getElement().equals("*"))
                    && (!Maze.map[barrel.getX() + 1][barrel.getY()].getElement().equals("b")))) {
            return true;
        }
        return false;
    }



    /*
     * This method's purpose is to: move the barrel UP if there are no walls
     *              if there is another element such as a reward, barrel is able to go through that
     *              uses temp from Block to temporary replace it the reward and bring back after moved
     *
     *
     * @param   barrel      when called the barrels will move Up
     * @return  nothing      the barrel will move
     * @see     barrels     moving barrels
     */
    public static void goUp(Block barrel){

        if ( goUpConditionTrue(barrel) ) {
            move = true;
            if (Maze.map[barrel.getX() - 1][barrel.getY()].getElement().equals(" ")) {
                if(!Maze.map[barrel.getX()][barrel.getY()].getTemp().equals(" ") && !Maze.map[barrel.getX()][barrel.getY()].getTemp().equals("m")){
                    Maze.map[barrel.getX()][barrel.getY()].removeTemp();
                }
                else{
                    Maze.map[barrel.getX()][barrel.getY()].setElement(" ");
                }
                Maze.map[barrel.getX() - 1][barrel.getY()].setElement("b");
            }
            else {
                Maze.map[barrel.getX()][barrel.getY()].setElement(" ");
                Maze.map[barrel.getX()-1][barrel.getY()].setTemp("b");
            }
            barrel.setX(barrel.getX() - 1);

        }

    } // end of goUP


    /*
     * This method's purpose is to: check is the barrel is allowed to move Up
     *                      if its in good relation (following algorithm) to the player and no wall or other barrel meets
     *
     *
     * @param   barrel      when called the barrels will move Up
     * @return  nothing      the barrel will move
     * @see     barrels     moving barrels
     */
    public static boolean goUpConditionTrue(Block barrel){
        if ( Maze.player.getX() < barrel.getX()
                && (!Maze.map[barrel.getX()-1][barrel.getY()].getElement().equals("*"))
                && (!Maze.map[barrel.getX() - 1][barrel.getY()].getElement().equals("b")) ){
            return true;
        }
        return false;
    }


    /*
     * This method's purpose is to: move the barrel Right if there are no walls
     *              if there is another element such as a reward, barrel is able to go through that
     *              uses temp from Block to temporary replace it the reward and bring back after moved
     *
     *
     * @param   barrel      when called the barrels will move Right
     * @return  nothing      the barrel will move
     * @see     barrels     moving barrels
     */
    public static void goRight(Block barrel){

        if ( goRightConditionTrue(barrel) ) {
            move = true;

            if (Maze.map[barrel.getX()][barrel.getY() + 1].getElement().equals(" ")) {
                if(!Maze.map[barrel.getX()][barrel.getY()].getTemp().equals(" ") && !Maze.map[barrel.getX()][barrel.getY()].getTemp().equals("m")){
                    Maze.map[barrel.getX()][barrel.getY()].removeTemp();
                }
                else{
                    Maze.map[barrel.getX()][barrel.getY()].setElement(" ");
                }
                Maze.map[barrel.getX()][barrel.getY()+1].setElement("b");
            }
            else {
                Maze.map[barrel.getX()][barrel.getY()].setElement(" ");
                Maze.map[barrel.getX()][barrel.getY()+1].setTemp("b");
            }
            barrel.setY(barrel.getY() + 1);
        }

    } // end of go right



    /*
     * This method's purpose is to: check is the barrel is allowed to move Right
     *                      if its in good relation to the player and no wall or other barrel meets
     *
     *
     * @param   barrel      when called the barrels will move Right
     * @return  nothing      the barrel will move
     * @see     barrels     moving barrels
     */
    public static boolean goRightConditionTrue(Block barrel){
        if ( Maze.player.getY() > barrel.getY()
                && (!Maze.map[barrel.getX() ][barrel.getY() + 1].getElement().equals("*"))
                && (!Maze.map[barrel.getX()][barrel.getY()+1].getElement().equals("b")) ){
            return true;
        }
        return false;
    }



    /*
     * This method's purpose is to: move the barrel Left if there are no walls
     *              if there is another element such as a reward, barrel is able to go through that
     *              uses temp from Block to temporary replace it the reward and bring back after moved
     *
     *
     * @param   barrel      when called the barrels will move Left
     * @return  nothing      the barrel will move
     * @see     barrels     moving barrels
     */
    public static void goLeft(Block barrel){

        if ( goLeftConditionTrue(barrel) ) {
            move = true;
            if (Maze.map[barrel.getX()][barrel.getY()-1].getElement().equals(" ")) {
                if(!Maze.map[barrel.getX()][barrel.getY()].getTemp().equals(" ") && !Maze.map[barrel.getX()][barrel.getY()].getTemp().equals("m")){
                    Maze.map[barrel.getX()][barrel.getY()].removeTemp();
                }
                else{
                    Maze.map[barrel.getX()][barrel.getY()].setElement(" ");
                }
                Maze.map[barrel.getX()][barrel.getY()-1].setElement("b");
            }
            else {
                Maze.map[barrel.getX()][barrel.getY()].setElement(" ");
                Maze.map[barrel.getX()][barrel.getY()-1].setTemp("b");
            }
            barrel.setY(barrel.getY() -1);
        }

    } // end of goLeft



    /*
     * This method's purpose is to: check is the barrel is allowed to move Left
     *                      if its in good relation (following algorithm) to the player and no wall or other barrel meets
     *
     *
     * @param   barrel      when called the barrels will move Left
     * @return  nothing     the barrel will move
     * @see     barrels     moving barrels
     */
    public static boolean goLeftConditionTrue(Block barrel){
        if ( Maze.player.getY() < barrel.getY()
                && (!Maze.map[barrel.getX()][barrel.getY()-1].getElement().equals("*"))
                && (!Maze.map[barrel.getX()][barrel.getY()-1].getElement().equals("b")) ){
            return true;
        }
        return false;
    }



    /*
     * This method's purpose is to: check is the barrel is and player row and column match
     *              if so, update the Maze.lost to true and update gameStatus
     *
     *
     * @param   barrel      when will check positions
     * @return  nothing     nothing
     * @see     barrels     the game end if collision
     */
    public static void checkIfCollision(Block barrel) {
        if (Maze.player.getX() == barrel.getX() && Maze.player.getY() == barrel.getY()) {
            Maze.lost = true;
            Maze.gameStatus();
        }
    }



} // end of Enemy class

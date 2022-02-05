package com.mycompany.app;

public class Punishment {


    /* attributes
    * pointDifference is used to add 25 points if it hits a reward
    * the same amount is used to remove 25 points if its hits a punishment
    */
    private static int pointDifference = 25;


     /*
     * This method is responsible for removing 25 points from the running score
     * which is tracked in Maze.java
     * It will take in the score from Maze known as the runningScore and
     * minus 25 points or point difference from it
     *
     * @param   runningScore            takes an integer value of the score from Maze
     * @return  updated runningScore    returns the score so it can be made visiable on the Map
     * @see     updated score
     */
    public static int deductPoints(int runningScore) {
        runningScore -= pointDifference;
        return runningScore;
    }

    /*
     * This method is responsible for adding 25 points to the running score
     * which is tracked in Maze.java
     * It will take in the score from Maze known as the runningScore and
     * adds 25 points (the point difference) to it
     *
     * @param   runningScore            takes an integer value of the score from Maze
     * @return  updated runningScore    returns the score so it can be made visiable on the Map
     * @see     updated score
     */
    public static int addPoints(int runningScore) {
        runningScore += pointDifference;
        return runningScore;
    }

    /*
     * This method is responsible for determining whether the score is below zero or not
     *      If below zero then the game will end.
     *      Zero is the safe spot as the player will not lose yet.
     *
     * @param   runningScore    takes an integer value of the score from Maze
     * @return  nothing         updates the "lost" to status "true" so Maze can end the game
     * @see     ends the game
     */
    public static void negScore(int runningScore) {
        if (runningScore < 0) {
            Maze.lost = true;
            Maze.gameStatus();
        }
    }

}

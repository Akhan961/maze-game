package com.mycompany.app;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game extends Application {

    public static TilePane blocks = new TilePane();
    public static ImageView[][] imgArray = new ImageView[Maze.ROWS][Maze.COLUMNS];

    public static int time = 1;
    public static Label timer = new Label("Timer: 0");
    public static Label score = new Label("Score: " + Player.playerScore);

    public int BLOCK_DIMENSIONS = 40;

    @Override

    /*
     * The purpose of this method is for:
     *      adding the image
     *      setting the screen layout
     *      displaying the map
     *      displaying the timer and score
     *      and initializing the player and barrels positions
     *

     * @param   primaryStage
     * @return  nothing
     * @see     the map with images
     */
    public void start(Stage primaryStage){

        VBox vbox = new VBox();

        Maze.createMap();
        Enemy.createEnemies();
        primaryStage.setTitle("21st Maze");

        blocks.setPrefTileHeight(BLOCK_DIMENSIONS);
        blocks.setPrefTileWidth(BLOCK_DIMENSIONS);

        for (int i = 0; i < Maze.ROWS; i++) {
            for (int j = 0; j < Maze.COLUMNS; j++) {
                switch (Maze.map[i][j].getElement()) {
                    case "*": {
                        Image image = new Image("/images/wall.jpg");
                        ImageView imageview = new ImageView(image);
                        imageview.setFitHeight(BLOCK_DIMENSIONS);
                        imageview.setFitWidth(BLOCK_DIMENSIONS);
                        imgArray[i][j] = imageview;
                        blocks.getChildren().add(imageview);
                        break;
                    }
                    case "b": {
                        Image image = new Image("/images/barrel.png");
                        ImageView imageview = new ImageView(image);
                        imageview.setFitHeight(BLOCK_DIMENSIONS);
                        imageview.setFitWidth(BLOCK_DIMENSIONS);
                        imgArray[i][j] = imageview;
                        blocks.getChildren().add(imageview);
                        break;
                    }
                    case "k": {
                        Image image = new Image("/images/key.png");
                        ImageView imageview = new ImageView(image);
                        imageview.setFitHeight(BLOCK_DIMENSIONS);
                        imageview.setFitWidth(BLOCK_DIMENSIONS);
                        imgArray[i][j] = imageview;
                        blocks.getChildren().add(imageview);
                        break;
                    }
                    case "p": {
                        Image image = new Image("/images/fire.png");
                        ImageView imageview = new ImageView(image);
                        imageview.setFitHeight(BLOCK_DIMENSIONS);
                        imageview.setFitWidth(BLOCK_DIMENSIONS);
                        imgArray[i][j] = imageview;
                        blocks.getChildren().add(imageview);
                        break;
                    }
                    case "c": {
                        Image image = new Image("/images/coin.png");
                        ImageView imageview = new ImageView(image);
                        imageview.setFitHeight(BLOCK_DIMENSIONS);
                        imageview.setFitWidth(BLOCK_DIMENSIONS);
                        imgArray[i][j] = imageview;
                        blocks.getChildren().add(imageview);
                        break;
                    }
                    case "m": {
                        Image image = new Image("/images/mario.png");
                        ImageView imageview = new ImageView(image);
                        imageview.setFitHeight(BLOCK_DIMENSIONS);
                        imageview.setFitWidth(BLOCK_DIMENSIONS);
                        imgArray[i][j] = imageview;
                        blocks.getChildren().add(imageview);
                        break;
                    }
                    case "s": {
                        Image image = new Image("/images/sdoor.png");
                        ImageView imageview = new ImageView(image);
                        imageview.setFitHeight(BLOCK_DIMENSIONS);
                        imageview.setFitWidth(BLOCK_DIMENSIONS);
                        imgArray[i][j] = imageview;
                        blocks.getChildren().add(imageview);
                        break;
                    }
                    case "e": {
                        Image image = new Image("/images/edoor.png");
                        ImageView imageview = new ImageView(image);
                        imageview.setFitHeight(BLOCK_DIMENSIONS);
                        imageview.setFitWidth(BLOCK_DIMENSIONS);
                        imgArray[i][j] = imageview;
                        blocks.getChildren().add(imageview);
                        break;
                    }
                    case " ": {
                        Image image = new Image("/images/blank.jpg");
                        ImageView imageview = new ImageView(image);
                        imageview.setFitHeight(BLOCK_DIMENSIONS);
                        imageview.setFitWidth(BLOCK_DIMENSIONS);
                        imgArray[i][j] = imageview;
                        blocks.getChildren().add(imageview);
                        break;
                    }
                }
            }
        }

        vbox.getChildren().add(blocks);

        vbox.getChildren().add(timer);
        vbox.getChildren().add(score);

        Scene scene = new Scene(vbox, BLOCK_DIMENSIONS*18.5, BLOCK_DIMENSIONS*16);

        //when any key on the keyboard is pressed, send the input to the playerInput method
        scene.setOnKeyPressed(event -> Player.playerInput(event.getText()));

        //updates the timer, score, map, and enemies every second
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
            timer.setText("Timer: " + time);
            time++;
            score.setText("Score: " + Player.playerScore);
            if (Player.playerScore <= 0) {
                Maze.lost = true;
                Maze.gameStatus();
            }
            Maze.getPos();
            Player.hasCollectedAllKeys();
            updateMap();
            // Uncomment this to see the map in console
//            Maze.printMap();
            Enemy.moveEnemies();
         }));
        //to make it go continuously
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        primaryStage.setScene(scene);
        primaryStage.show();
    }



    /*
     * The purpose of this method is for:
     *         when method is called, all the images of the blocks on the screen are updated
     *
     * @param   nothing        take no parameters
     * @return  nothing         the updated map
     * @see     map             the map with images
     */
    public static void updateMap(){

        for (int i = 0; i < Maze.ROWS; i++) {
            for (int j = 0; j < Maze.COLUMNS; j++) {
                ImageView imageview = imgArray[i][j];
                switch (Maze.map[i][j].getElement()) {
                    case "*": {
                        Image image = new Image("/images/wall.jpg");
                        imageview.setImage(image);
                        break;
                    }
                    case "b": {
                        Image image = new Image("/images/barrel.png");
                        imageview.setImage(image);
                        break;
                    }
                    case "k": {
                        Image image = new Image("/images/key.png");
                        imageview.setImage(image);
                        break;
                    }
                    case "p": {
                        Image image = new Image("/images/fire.png");
                        imageview.setImage(image);
                        break;
                    }
                    case "c": {
                        Image image = new Image("/images/coin.png");
                        imageview.setImage(image);
                        break;
                    }
                    case "m": {
                        Image image = new Image("/images/mario.png");
                        imageview.setImage(image);
                        break;
                    }
                    case "s": {
                        Image image = new Image("/images/sdoor.png");
                        imageview.setImage(image);
                        break;
                    }
                    case "e": {
                        Image image = new Image("/images/edoor.png");
                        imageview.setImage(image);
                        break;
                    }
                    case " ": {
                        Image image = new Image("/images/blank.jpg");
                        imageview.setImage(image);
                        break;
                    }
                }

            }
        }


    }


    public static void main(String[] args) {
        launch(args);
    }
}

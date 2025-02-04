package com.chriseberle.utils;

import javafx.geometry.Dimension2D;
import javafx.stage.Stage;

public class StageManager {

    // reference to the stage
    private static Stage primaryStage;


    /**
     * Set the default application settings for the JavaFX window.
     * @param stage the primary stage
     */
    public static void setLockedWindow(Stage stage) {
        stage.setResizable(false);
    }

    /*
     * locks the minimum width and height of window and sets resizable to false.
    */
    public static void setResizableWindow(Stage stage) {
        stage.setResizable(true);
        //stage.setMinHeight(440);
        //stage.setMinHeight(600);
    }

    /**
     * Get the dimensions of the current scene.
     * @return the dimensions of the current scene
     */
    public static Dimension2D getSceneDimensions(Stage stage) {
        return new Dimension2D(stage.getScene().getWidth(), stage.getScene().getHeight());
    }
    /**
     * Get the main Stage
     * @return Stage
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }
    /**
     * Set the main stage.
     * @return the dimensions of the current scene
     */
    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }
}

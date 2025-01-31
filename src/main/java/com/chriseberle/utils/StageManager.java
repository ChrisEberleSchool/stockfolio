package com.chriseberle.utils;

import java.util.Vector;
import javafx.stage.Stage;

public class StageManager {
    
    /**
     * Set the default application settings for the JavaFX window.
     * @param stage the primary stage
     */
    public static void setLockedWindowSettings(Stage stage) {
        stage.setResizable(false);
    }

    public static void setResizableWindowConstrained(Stage stage) {
        stage.setResizable(false);
        stage.setMinHeight(440);
        stage.setMinHeight(600);
    }


    /**
     * Get the dimensions of the current scene.
     * @return the dimensions of the current scene
     */
    public static Vector<Double> getSceneDimensions(Stage stage) {
        Vector<Double> dimensions = new Vector<>();
        dimensions.add(stage.getScene().getWidth());
        dimensions.add(stage.getScene().getHeight());
        return dimensions;
    }
}

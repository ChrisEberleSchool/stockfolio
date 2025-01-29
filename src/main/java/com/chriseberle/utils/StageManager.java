package com.chriseberle.utils;

import java.util.Vector;
import javafx.stage.Stage;

public class StageManager {
    
    /**
     * Set the default application settings for the JavaFX window.
     * @param stage the primary stage
     */
    public static void setDefaultApplicationSettings(Stage stage) {
        stage.setResizable(false);
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

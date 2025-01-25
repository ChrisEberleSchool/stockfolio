package com.chriseberle.utils;

import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.util.HashMap;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class SceneManager {
    /**
     * The primary stage.
     */
    private static Stage primaryStage;
    /**
     * A map of scenes.
     */
    private static HashMap<String, Scene> sceneMap = new HashMap<>();

    /**
     * Initialize the SceneManager with the primary stage.
     * @param stage the primary stage
     */
    public static void init(Stage stage) {
        primaryStage = stage;
        primaryStage.setResizable(false);

        // Preload scenes
        loadScene("loginRegister", "/fxml/loginRegister.fxml");
        loadScene("Register", "/fxml/register.fxml");
    }
    /**
     * Load a scene from an FXML file.
     * @param name the name of the scene
     * @param fxmlPath the path to the FXML file
     */
    public static void loadScene(String name, String fxmlPath) {
        try {   
            Parent root = FXMLLoader.load(SceneManager.class.getResource(fxmlPath));
            sceneMap.put(name, new Scene(root));
        }
        catch (Exception e) {
            System.out.println("[ERROR] Failed to load scene: " + e);
        }
        finally {
            System.out.println("[LOADED] Scene: " + name);
        }
        
    }

    /**
     * Switch to a scene by name.
     * @param name the name of the scene
     */
    public static void switchScene(String name) {
        Scene scene = sceneMap.get(name);
        if(scene != null) {
            primaryStage.setScene(scene);
            primaryStage.show();
        } else {
            throw new RuntimeException("[ERROR] Scene not found: " + name);
        }
    }
}

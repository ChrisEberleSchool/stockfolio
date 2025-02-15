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
     * The current scene.
     */
    private static Scene currentScene;

    /**
     * The entry scene.
     */
    private static String entrySceneKey = "loginRegister";

    /**
     * Initialize the SceneManager with the primary stage.
     * @param stage the primary stage
     */
    public static void init() {
        // set the class specific stage instance to the primary stage
        primaryStage = StageManager.getPrimaryStage();
    
        // Preload scenes
        loadScene("loginRegister", "/fxml/loginRegister.fxml");
        loadScene("Register", "/fxml/register.fxml");
        loadScene("Login", "/fxml/login.fxml");
        loadScene("Home", "/fxml/homePage.fxml");
        loadScene("test", "/fxml/tPage.fxml");
        // home scenes
        loadScene("CreatePortfolio", "/fxml/createPortfolioPage.fxml");
        loadScene("PortfolioGraph", "/fxml/portfolioGraphPage.fxml");
        loadScene("Settings", "/fxml/settingsPage.fxml");

        SceneManager.switchScene(getEntrySceneKey());
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
        currentScene = sceneMap.get(name);
        if(currentScene != null) {
            primaryStage.setScene(currentScene);
            primaryStage.show();
        } else {
            throw new RuntimeException("[ERROR] Scene not found: " + name);
        }
    }

    /**
     * Get the current scene.
     * @return the current scene
     */
    public static Scene getCurrentScene() {
        return currentScene;
    }

    /**
     * Get the entry scene.
     * @return the current scene
     */
    public static String getEntrySceneKey() {
        return entrySceneKey;
    }
}

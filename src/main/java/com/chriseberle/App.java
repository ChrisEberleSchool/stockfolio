package com.chriseberle;

import javafx.event.EventHandler;
import java.io.IOException;
import com.chriseberle.utils.SceneManager;
import com.chriseberle.utils.StageManager;
import javafx.application.Application;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class App extends Application {

    /**
     * The start method is the entry point for JavaFX applications.
     * @param stage the primary stage for this application, onto which the application scene can be set.
     * @throws IOException if an input or output exception occurred
     */
    @Override
    public void start(Stage stage) throws IOException 
    {
        // set the default stage settings
        StageManager.setDefaultApplicationSettings(stage);

        // initialize the scene manager
        SceneManager.init(stage);
        // set the entry scene
        SceneManager.switchScene(SceneManager.getEntrySceneKey());


        // key events listener
        SceneManager.getCurrentScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    System.out.println(StageManager.getSceneDimensions(stage)); break;
                    case DOWN:  System.out.println("DOWN"); break;
                    case LEFT:  System.out.println("LEFT"); break;
                    case RIGHT: System.out.println("RIGHT"); break;
                }
            }
        });
    }
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be launched
     * through deployment artifacts, e.g., in IDEs with limited FX support.
     * NetBeans ignores main().
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
}

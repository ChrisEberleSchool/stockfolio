package com.chriseberle;

import java.io.IOException;
import com.chriseberle.utils.SceneManager;
import javafx.application.Application;
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
        // initialize the scene manager
        SceneManager.init(stage);
        
        //load a scene
        SceneManager.switchScene("loginRegister");
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

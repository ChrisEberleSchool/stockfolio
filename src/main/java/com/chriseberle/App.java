package com.chriseberle;

//javafx imports
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.chriseberle.db.DBTableMethods.DBUser;
import com.chriseberle.db.H2Database;
import com.chriseberle.utils.SceneManager;
import com.chriseberle.utils.StageManager;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class App extends Application {

    /**
     * The start method is the entry point for JavaFX applications.
     * @param stage the primary stage for this application, onto which the application scene can be set.
     * @throws IOException if an input or output exception occurred
     */
    @Override
    public void start(Stage stage) throws SQLException 
    {
    
        // set the default stage settings
        StageManager.setLockedWindowSettings(stage);
        // initialize the scene manager
        SceneManager.init(stage);
        // set the entry scene
        SceneManager.switchScene(SceneManager.getEntrySceneKey());

        // database initialization
        ArrayList<String> sqlFiles = new ArrayList<String>();
        sqlFiles.add("db/stockfolioDDL.sql");
        // database creation
        H2Database.createDatabase("jdbc:h2:./target/db/mainDB", "", "", sqlFiles);

        // Register window close event to cleanly shutdown H2 database when app exits
        stage.setOnCloseRequest(event -> {
            // Shutdown the database when the application window is closed
            H2Database.shutdownHandler();
        });


        // key events listener
        SceneManager.getCurrentScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W:    
                        System.out.println(StageManager.getSceneDimensions(stage));
                        break;
                    case A:  
                        System.out.println("DOWN"); 
                        DBUser.insertUser(H2Database.getMainThreadConnection(), "Chris", "123hh", "ceber@bb.com");
                        break; 
                    case S:  
                        System.out.println("LEFT"); 
                        DBUser.printUserByUsername(H2Database.getMainThreadConnection(), "Chris");
                        break;
                    case D: 
                        System.out.println("RIGHT"); 
                        DBUser.printUsers(H2Database.getMainThreadConnection());
                        break;
                    default:
                        break;
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
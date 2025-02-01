package com.chriseberle;

//javafx imports
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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

    static Connection dbConnection;

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

        // create database after stage has been made
        String JDBC_URL = "jdbc:h2:./target/db/mainDB";
        String USERNAME = "";
        String PASSWORD = "";
        ArrayList<String> sqlFiles = new ArrayList<String>();
        sqlFiles.add("db/stockfolioDDL.sql");

        H2Database.createDatabase(JDBC_URL, USERNAME, PASSWORD, sqlFiles);
        dbConnection = DriverManager.getConnection(JDBC_URL);
        H2Database.shutdownHandlerJavaFX(dbConnection, stage);

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
                        DBUser.insertUser(dbConnection, "Chris", "123hh", "ceber@bb.com");
                        break; 
                    case S:  
                        System.out.println("LEFT"); 
                        break;
                    case D: 
                        System.out.println("RIGHT"); 
                        DBUser.printUsers(dbConnection);
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
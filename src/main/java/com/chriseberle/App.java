package com.chriseberle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import com.chriseberle.db.H2Database;
import com.chriseberle.utils.SceneManager;
import com.chriseberle.utils.StageManager;
import javafx.application.Application;
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
        // Initialize the stage
        StageManager.setPrimaryStage(stage);
        StageManager.setResizableWindow(stage);

        // database initialization and creation
        ArrayList<String> sqlFiles = new ArrayList<String>();
        sqlFiles.add("db/stockfolioDDL.sql");
        H2Database.createDatabase("jdbc:h2:./target/db/mainDB", "", "", sqlFiles);
        H2Database.shutdownHandler(stage);

        // initialize the scene manager
        SceneManager.init();
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
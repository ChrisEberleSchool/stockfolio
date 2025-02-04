package com.chriseberle.db;

//imports
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.stage.Stage;


/**
 * This class provides methods to interact with an H2 database.
 * 
 * 
 * FORMAT:
 * 
 * String JDBC_URL = "jdbc:h2:./target/db/testdb";
 * String USERNAME = "";
 * String PASSWORD = "";
 */
public class H2Database {
    // This will allow multiple connections to the database for seperate threads.
    public static DBConnectionPool connectionPool;
    private static final int MAX_CONNECTIONS = 5;
    // the connection that will be used on the main thread
    private static Connection mainThreadConnection;

    /**
     * Create a new H2 database.
     * 
     * sqlFile FORMAT: "db/file.sql" -> this file must be stored at src/main/resources
     * 
     * @param JDBC_URL the JDBC URL
     * @param DB_USERNAME the database username
     * @param DB_PASSWORD the database password
     * @param sqlFiles the SQL files to execute
     */
    public static void createDatabase(String JDBC_URL, String DB_USERNAME, String DB_PASSWORD, ArrayList<String> sqlFiles)
     {
        /*
        *  Initialize a connection pool for the database.
        *  This will run even if a database already exists
        */
        try {
            // create a connection pool for the database
            connectionPool = new DBConnectionPool(JDBC_URL, DB_USERNAME, DB_PASSWORD, MAX_CONNECTIONS);
            // get a connection from the pool
            mainThreadConnection = connectionPool.getConnection();
            // add more connections upon threading if ever
        }
        catch (Exception e) {   
            System.out.println("[CONNECTION ERROR] Failed to get a connection to the H2 Database: " + e.getMessage());
            // exit early if failed to connect to the database
            return;
        }

        // check if the target dir has a database dir. (create it if not) 
        File dbDirectory = new File("./target/db");
        if (!dbDirectory.exists()) {
            dbDirectory.mkdirs();  // This will create the target/db directory if it doesn't exist
            System.out.println("Directory created: " + dbDirectory.getAbsolutePath());
        }

        // check if the database dir has a JDBC mv.db file. (exit if so) 
        dbDirectory = new File("./target/db/"+JDBC_URL+".mv.db");
        if (!dbDirectory.exists()) {
            dbDirectory.mkdirs();  // This will create the target/db directory if it doesn't exist
            System.out.println("Directory created: " + dbDirectory.getAbsolutePath());
        } else {
            System.out.println("[SQL] Database Already Exists.");
            return;
        }

        // read in the database
        try {
            Statement stmt = mainThreadConnection.createStatement();
            for (String file : sqlFiles) {
                executeSqlFile(stmt, file);
            }   
        }
        catch (SQLException e) {   
            System.out.println("[SQL ERROR] Failed to create database statement. Database Will Not Created: " + e.getMessage());
            // exit early if failed to create a statement
            return;
        }
        catch (IOException e) {
            System.out.println("[SQL ERROR] SQL file not found. Database Will Not Created: " + e.getMessage());
            // exit early if failed to create a statement
            return;
        }
        System.out.println("Database initialized successfully!");
    }

    /*
     * Calls a listener to the javafx window to 
     * properly shutdown the database upon
     * exiting the application.
    */
    public static void shutdownHandler(Stage stage) {
        // Register window close event to cleanly shutdown H2 database when app exits
        stage.setOnCloseRequest(event -> {
            // TODO: Shutdown is not working correctly 
            try {
                if (mainThreadConnection != null && !mainThreadConnection.isClosed()) {
                    // return the mainthread connection to the pool
                    connectionPool.returnConnection(mainThreadConnection);
                    // shutdown the connection pool
                    connectionPool.shutdown();
                }
            } catch (SQLException e) {
                System.out.println("[H2 DB] ERROR: Failed to properly clean database upon termination of JavaFX: -> " + e);
            }
        });
    }

    /**
     * Execute an SQL script file.
     * 
     * only needs to be called once upon first run
     * @param stmt the statement object
     * @param sqlFilePath the path to the SQL file
     * @throws IOException if an I/O error occurs
     */
    private static void executeSqlFile(Statement stmt, String sqlFilePath) throws SQLException, IOException {
        // Ensure that the file path is correct and relative to the resources folder
        InputStream inputStream = H2Database.class.getClassLoader().getResourceAsStream(sqlFilePath);

        // check if our sql file exists
        if (inputStream == null) {
            throw new FileNotFoundException("SQL file not found: " + sqlFilePath);
        }

        //check if 
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            StringBuilder sql = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sql.append(line).append("\n");
            }
            stmt.execute(sql.toString()); // Execute the SQL commands from the file
        } catch (SQLException e) {
            System.err.println("[SQL] SQL file already exists. Ignoring new Creation.");
        }
    }

    /**
     * Get a connection from the connection pool.
     * 
     * @return the connection
     */
    public static Connection getMainThreadConnection() {
       return mainThreadConnection;
    }
}
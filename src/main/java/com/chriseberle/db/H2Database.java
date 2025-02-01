package com.chriseberle.db;

//imports
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
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

    
    // sqlFile FORMAT: "db/file.sql" -> this file must be stored at src/main/resources
    public static void createDatabase(String JDBC_URL, String DB_USERNAME, String DB_PASSWORD, ArrayList<String> sqlFiles)
     {
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

        // create a temp connection, create a statement
        Connection connection;
        try {
            connection = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
        }
        catch (SQLException e) {   
            System.out.println("[SQL ERROR] Failed to connect to the database. Database Will Not Created: " + e.getMessage());
            // exit early if failed to connect to the database
            return;
        }

        // read in the database
        try {
            Statement stmt = connection.createStatement();
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

    public static void shutdownHandlerJavaFX(Connection connection, Stage stage) {
        // TODO: Shutdown is not working correctly 
        stage.setOnCloseRequest(event -> {
            // Close the database connection properly
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
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
}
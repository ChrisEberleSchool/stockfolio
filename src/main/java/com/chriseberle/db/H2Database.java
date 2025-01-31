package com.chriseberle.db;

//imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * This class provides methods to interact with an H2 database.
 */
public class H2Database {

    // Database connection details
    private static final String JDBC_URL = "jdbc:h2:./target/db/testdb";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    /**
     * Get a connection to the database.
     * @return the database connection
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        }
        catch (SQLException e) {   
            System.out.println("[ERROR] Failed to connect to the database: " + e.getMessage());
            // Rethrow the exception to be handled by the caller
            throw e;  
        }
    }

    /**
     * Execute an SQL script file.
     * 
     * only needs to be called once upon first run
     * @param stmt the statement object
     * @param sqlFilePath the path to the SQL file
     * @throws IOException if an I/O error occurs
     */
    public static void executeSqlFile(Statement stmt, String sqlFilePath) throws SQLException, IOException {
        // Ensure that the file path is correct and relative to the resources folder
        InputStream inputStream = H2Database.class.getClassLoader().getResourceAsStream(sqlFilePath);

        // check if our sql file exists
        if (inputStream == null) {
            throw new FileNotFoundException("SQL file not found: " + sqlFilePath);
        }

        // TODO: Check if our tables already exist before executing the SQL file

        //check if 
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            StringBuilder sql = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sql.append(line).append("\n");
            }
            stmt.execute(sql.toString()); // Execute the SQL commands from the file
        } catch (SQLException e) {
            System.err.println("[SQL] Table already exists.");
        }
    }

    /**
     * Initialize the database by creating tables and inserting data.
     */
    public static void initializeDatabase() {
        
        // Check if the target/db directory exists, and create it if not
        File dbDirectory = new File("./target/db");
        if (!dbDirectory.exists()) {
            dbDirectory.mkdirs();  // This will create the target/db directory if it doesn't exist
            System.out.println("Directory created: " + dbDirectory.getAbsolutePath());
        }

        // Initialize the connection and execute setup files
        try (Connection connection = getConnection()) {
            Statement stmt = connection.createStatement();
            
            // Execute schema.sql (to create tables)
            executeSqlFile(stmt, "db/stockfolioDDL.sql");

            System.out.println("Database initialized successfully!");
        } catch (Exception e) {
            System.out.println("[SQL ERROR] occurred while attempting to initialize the database. [MSG] -> " + e.getMessage());
        }
    }
}

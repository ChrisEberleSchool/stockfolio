package com.chriseberle.db.DBTableMethods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;
import java.sql.Date;

public class DBUser {
    /**
     * Insert a user into the User table.
     * @param connection the database connection
     * @param userID the user ID
     * @param username the username
     * @param password the password
     * @param email the email
     */
    public static void insertUser(Connection connection, String username, String password, String email) {
        String insertSql = "INSERT INTO \"User\" (userID, username, password, email, dateCreated) VALUES (?, ?, ?, ?, ?)";
    
        try (PreparedStatement ps = connection.prepareStatement(insertSql)) {
            // populate user data
            ps.setInt(1, generateUniqueUserID());
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setString(4, email);
            ps.setDate(5, new Date(System.currentTimeMillis()));
            // execute the statement
            ps.executeUpdate();
            // print success
            System.out.println("User inserted successfully.");
        } catch (Exception e) {
            System.err.println("[SQL ERROR] occurred while attempting to insert a user into the database. [MSG] -> " + e);
        }
    }
    /**
     * Generate a unique user ID.
     * @return the unique user ID
     */
    private static int generateUniqueUserID() {
        // TODO: Implement a more robust method for generating unique user IDs.
        UUID uuid = UUID.randomUUID();
        long mostSigBits = uuid.getMostSignificantBits();
        // We can use the least significant 32 bits or most significant 32 bits as an int.
        return Math.abs((int) (mostSigBits ^ (mostSigBits >>> 32)));
    }

    /**
     * Query and print users from the User table.
     * @param connection the database connection
     */
    public static void printUsers(Connection connection) {
        String querySql = "SELECT * FROM \"User\"";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(querySql)) {

            System.out.println("Users in the User table:");
            while (rs.next()) {
                int userID = rs.getInt("userID");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String dateCreated = rs.getString("dateCreated");
                System.out.printf("ID: %d, Username: %s, Password: %s, Email: %s, Date Created: %s%n", userID, username, password, email, dateCreated);
            }
        } catch (Exception e) {
            System.out.println("[ERROR] Failed to print users from the User table: " + e);
        }
    }
}

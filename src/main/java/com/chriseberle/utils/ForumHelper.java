package com.chriseberle.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.chriseberle.db.H2Database;
import com.chriseberle.db.DBTableMethods.DBUser;


/**
 * Methods related to forum logic
 */
public class ForumHelper {

    /**
     * This method checks if the email matches a valid pattern.
     *
     * @param email The email to check.
     * @return true if the email matches a valid pattern, false otherwise.
     */
    public static boolean validEmail(String email) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * This method checks if the user matches the valid pattern.
     *
     * @param user The user to check.
     * @return true if the user matches a valid pattern, false otherwise.
     */
    public static boolean validUserernameRegister(String user) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9-_]{4,15}$");
        Matcher matcher = pattern.matcher(user);
        //check if there username already exists
        if(DBUser.doesUsernameExist(H2Database.getMainThreadConnection(), user)) {
            System.out.println("[Username Error] Username already exists in the database.");
            return false;
        }
        if(matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * This method checks if the user matches the valid pattern.
     *
     * @param user The user to check.
     * @return true if the user matches a valid pattern, false otherwise.
     */
    public static boolean validUserernameLogin(String user) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9-_]{4,15}$");
        Matcher matcher = pattern.matcher(user);
      
        //check if there username already exists
        if(!DBUser.doesUsernameExist(H2Database.getMainThreadConnection(), user)) {
            System.out.println("[Username Error] Username does not exist in the database.");
            return false;
        }
        if(matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * This method checks if the password matches the valid pattern.
     *
     * @param user The password to check.
     * @return true if the password matches a valid pattern, false otherwise.
     */
    public static boolean validPassword(String user) {
        Pattern pattern = Pattern.compile("^[\\S]{4,15}$");
        Matcher matcher = pattern.matcher(user);
        return matcher.matches();
    }

    /**
     * This method checks if the password matches the retype password
     *
     * @param pass        The password to check.
     * @param passConfirm The confirm password to check.
     * @return true if the passwords match, false otherwise.
     */
    public static boolean validPasswordMatch(String pass, String passConfirm) {
        return (pass.equals(passConfirm) && 
                validPassword(pass)) 
                ? true : false;
    }
}
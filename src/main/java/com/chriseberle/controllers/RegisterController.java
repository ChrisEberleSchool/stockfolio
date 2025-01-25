package com.chriseberle.controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The RegisterController class is a controller class that handles the registration view.
 */
public class RegisterController {

    // TODO: better naming conventions for the fields
    /**
     * The FXML annotation is used to inject the fields from the FXML file.
     */

    // buttons
    @FXML
    private Button registerButton;

    // password fields
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;

    // text fields
    @FXML
    private TextField emailField;
    @FXML
    private TextField usernameField;

    // these images display whether or not the fields have good data using icons.
    @FXML
    ImageView emailStatusImageView;
    @FXML
    ImageView userStatusImageView;
    @FXML
    ImageView passwordStatusImageView;
    @FXML
    ImageView retypePasswordStatusImageView;

    /**
     * This method is automatically called when the controller is initialized.
     */
    public void initialize() {

        // TODO: better structure and seperate the listeners into their own methods

        // set the default images for the image view
        initImageStatus();

        // EMAIL LISTENER
        emailField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Validate the email format
            if (isValidEmail(newValue)) {
                // set the status image to check
                setStatusImage(emailStatusImageView, true);
            } else {
                // Email is invalid
                // set the status image to check
                setStatusImage(emailStatusImageView, false);
            }
        });

        // USER LISTENER
        usernameField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Validate the email format
            if (isValidUser(newValue)) {
                // set the status image to check
                setStatusImage(userStatusImageView, true);
            } else {
                // Email is invalid
                // set the status image to check
                setStatusImage(userStatusImageView, false);
            }
        });

        // PASSWORD LISTENER
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Validate the email format
            if (isValidPassword(newValue)) {
                // set the status image to check
                setStatusImage(passwordStatusImageView, true);
            } else {
                // Email is invalid
                // set the status image to check
                setStatusImage(passwordStatusImageView, false);
            }
        });

        // RETYPE PASSWORD LISTENER
        confirmPasswordField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Validate the email format
            if (isMatchingPassword(newValue, passwordField.getText())) {
                // set the status image to check
                setStatusImage(retypePasswordStatusImageView, true);
            } else {
                // Email is invalid
                // set the status image to check
                setStatusImage(retypePasswordStatusImageView, false);
            }
        });
    }

    /**
     * This method is called when the user clicks the register button.
     * 
     * @param e The ActionEvent object.
     */
    public void register(ActionEvent e) {
        System.out.println("[CLICKED] Register button: " + emailField.getText());

        /* 
         * Check if the email, username, password and confirm password are valid.
         * If they are, then register the user.
         * 
         * Using try and catch to catch any exceptions that may occur if the input
         * fields are null or corruption occurs.
         */
        try {
            if( isValidEmail(emailField.getText()) && 
                isValidUser(usernameField.getText()) && 
                isValidPassword(passwordField.getText()) && 
                isMatchingPassword(passwordField.getText(), confirmPasswordField.getText()) 
              ) 
            { // if so print the success message
                System.out.println("Successfully Registered user: " + usernameField.getText());
            } else { // if not print the failure message
                System.out.println("Failed to Register user, Please enter Correct data: " + usernameField.getText());
            }
        }
        catch (Exception err) {
            System.out.println("[ERROR] COuld not register: " + err);
        }
    }

    /**
     * This method checks if the email matches a valid pattern.
     *
     * @param email The email to check.
     * @return true if the email matches a valid pattern, false otherwise.
     */
    private boolean isValidEmail(String email) {
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
    private boolean isValidUser(String user) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9-_]{4,15}$");
        Matcher matcher = pattern.matcher(user);
        return matcher.matches();
    }

    /**
     * This method checks if the password matches the valid pattern.
     *
     * @param user The password to check.
     * @return true if the password matches a valid pattern, false otherwise.
     */
    private boolean isValidPassword(String user) {
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
    private boolean isMatchingPassword(String pass, String passConfirm) {
        return (pass.equals(passConfirm) && 
                isValidPassword(pass)) 
                ? true : false;
    }

    /**
     * This method initializes the image status to false.
     * This is used to set the default image to a cross.
     */
    private void initImageStatus() {
        setStatusImage(emailStatusImageView, false);
        setStatusImage(userStatusImageView, false);
        setStatusImage(passwordStatusImageView, false);
        setStatusImage(retypePasswordStatusImageView, false);
    }

    /**
     * This method sets the status image to either a check or cross.
     *
     * @param imageView The image view to set the image on.
     * @param isValid   The boolean value to determine which image to set.
     */
    private void setStatusImage(ImageView imageView, boolean isValid) {
        if (isValid) {
            imageView.setImage(new Image(getClass().getResource("/icons/check.png").toExternalForm()));
        } else {
            imageView.setImage(new Image(getClass().getResource("/icons/cross.png").toExternalForm()));
        }
    }
}
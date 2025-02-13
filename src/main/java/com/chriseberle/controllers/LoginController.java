package com.chriseberle.controllers;

import com.chriseberle.db.H2Database;
import com.chriseberle.db.DBTableMethods.DBUser;
import com.chriseberle.utils.ForumHelper;
import com.chriseberle.utils.SceneManager;
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
public class LoginController {

    // TODO: better naming conventions for the fields
    /**
     * The FXML annotation is used to inject the fields from the FXML file.
     */

    // buttons
    @FXML
    private Button loginButton;
    @FXML
    private Button forgotPasswordButton;

    // password fields
    @FXML
    private PasswordField passwordField;

    // text fields
    @FXML
    private TextField usernameField;

    // these images display whether or not the fields have good data using icons.
    @FXML
    ImageView userStatusImageView;
    @FXML
    ImageView passwordStatusImageView;

    /**
     * This method is automatically called when the controller is initialized.
     */
    public void initialize() {

        // TODO: better structure and seperate the listeners into their own methods

        // set the default images for the image view
        initImageStatus();

        // USER LISTENER
        usernameField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Validate the email format
            if (ForumHelper.validUserernameLogin(newValue)) {
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
            if (ForumHelper.validPassword(newValue)) {
                // set the status image to check
                setStatusImage(passwordStatusImageView, true);
            } else {
                // Email is invalid
                // set the status image to check
                setStatusImage(passwordStatusImageView, false);
            }
        });
    }

    /**
     * This method is called when the user clicks the register button.
     * 
     * @param e The ActionEvent object.
     */
    public void login(ActionEvent e) {
        System.out.println("[CLICKED] Login button: " + usernameField.getText());

        /* 
         * Check if the email, username, password and confirm password are valid.
         * If they are, then register the user.
         * 
         * Using try and catch to catch any exceptions that may occur if the input
         * fields are null or corruption occurs.
         */
        try {
            if(DBUser.checkUserLogin(H2Database.getMainThreadConnection(), usernameField.getText(), passwordField.getText())) {
                //System.out.println("User Logged in: ");
                DBUser.printUserByUsername(H2Database.getMainThreadConnection(), usernameField.getText());
                SceneManager.switchScene("Home");
            } 
    
            else { // if not print the failure message
                System.out.println("Failed to login user, Please enter Correct data: " + usernameField.getText());
            }
            
        }
        catch (Exception err) {
            System.out.println("[ERROR] Could not login: " + err);
        }
    }

    /**
     * This method initializes the image status to false.
     * This is used to set the default image to a cross.
     */
    private void initImageStatus() {
        setStatusImage(userStatusImageView, false);
        setStatusImage(passwordStatusImageView, false);
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
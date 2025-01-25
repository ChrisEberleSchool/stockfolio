package com.chriseberle.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * The RegisterController class is a controller class that handles the registration view.
 */
public class RegisterController {
    /**
     * The FXML annotation is used to inject the button from the FXML file.
     */
    @FXML
    private Button registerButton;

    private PasswordField passwordField;
    private PasswordField confirmPasswordField;
    private TextField emailField;
    private TextField userField;

    /**
     * This method is called when the user clicks the register button.
     * 
     * @param e The ActionEvent object.
     */
    public void register(ActionEvent e) {
        System.out.println("[CLICKED] Register button.");
    }
}

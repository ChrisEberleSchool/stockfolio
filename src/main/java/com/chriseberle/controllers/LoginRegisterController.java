package com.chriseberle.controllers;
import com.chriseberle.utils.SceneManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * The LoginRegisterController class is a controller class that handles the login and registration view.
 */
public class LoginRegisterController {

    /**
     * The FXML annotation is used to inject the button from the FXML file.
     */
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    
    /**
     * This method is called when the user clicks the login button.
     * 
     * @param e The ActionEvent object.
     */
    public void login(ActionEvent e) {
        System.out.println("[CLICKED] Login button.");
        SceneManager.switchScene("Login");
    }

    /**
     * This method is called when the user clicks the register button.
     * 
     * @param e The ActionEvent object.
     */
    public void register(ActionEvent e) {
        System.out.println("[CLICKED] Register button.");
        SceneManager.switchScene("Register");
    }
}

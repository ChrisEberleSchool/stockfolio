package com.chriseberle.controllers;

import com.chriseberle.utils.SceneManager;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * The RegisterController class is a controller class that handles the registration view.
 */
public class HomeController {

    @FXML
    private ImageView settingsIcon;
    @FXML
    private ImageView homeIcon;
    @FXML
    private ImageView createPortfolioIcon;
    @FXML
    private ImageView portfolioGraphIcon;
    
    /**
     * This method is automatically called when the controller is initialized.
     */
    public void initialize() {
        //settingsIcon.setOnMouseClicked(event -> {
        //    System.out.println("Image clicked at X: " + event.getX() + ", Y: " + event.getY());
        //    // You can add more logic here depending on what you want to do on click
        //});
    }

    // This method will be called when the image is clicked
    public void handleSettingsIconClick(MouseEvent event) {
        SceneManager.switchScene("Settings");
    }
    public void handleHomeIconClick(MouseEvent event) {
        SceneManager.switchScene("Home");
    }
    public void handleCreatePortfolioIconClick(MouseEvent event) {
        SceneManager.switchScene("CreatePortfolio");
    }
    public void handlePortfolioGraphIconClick(MouseEvent event) {
        SceneManager.switchScene("PortfolioGraph");
    }

}
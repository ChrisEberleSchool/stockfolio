package com.chriseberle.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.*;

public class Controller0 {
    
    @FXML
    ImageView userNameImageView;
    @FXML
    ImageView passwordImageView;
    @FXML
    ImageView emailImageView;

    Image userIcon = new Image(getClass().getResourceAsStream("../images/user.png"));
    Image passIcon = new Image(getClass().getResourceAsStream("../images/pass.png"));
    Image emailIcon = new Image(getClass().getResourceAsStream("../images/mail.png"));

    public void displayImage() {
        //userNameImageView.setImage(userIcon);
        //passwordImageView.setImage(passIcon);
        //emailImageView.setImage(emailIcon);
    }
}

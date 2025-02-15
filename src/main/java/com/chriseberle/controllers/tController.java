package com.chriseberle.controllers;

import com.chriseberle.utils.StageManager;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.geometry.Dimension2D;
import javafx.scene.layout.AnchorPane;

public class tController {

    @FXML
    private AnchorPane parentAnchorPane;

    @FXML
    private AnchorPane innerAnchorPane;

    AnimationTimer animationTimer;

    @FXML
    public void initialize() {
        // Create and start the AnimationTimer
                animationTimer = new AnimationTimer() {
                    @Override
                    public void handle(long now) {
                        try {
                            updateAnchorConstraints();
                        }
                        catch (Exception e) {
                            System.out.println("oops");
                        }
                    }
                };
        animationTimer.start();

    }

    public void updateAnchorConstraints() {

        Dimension2D screenDimensions = StageManager.getSceneDimensions(StageManager.getPrimaryStage());
    
        double leftsideMargin = 0;
        double rightsideMargin = screenDimensions.getWidth() - (screenDimensions.getWidth()/8);
        double topsideMargin = 0;
        double bottomsideMargin = 0;

        AnchorPane.setTopAnchor(innerAnchorPane, topsideMargin);    
        AnchorPane.setLeftAnchor(innerAnchorPane, leftsideMargin);   
        AnchorPane.setRightAnchor(innerAnchorPane, rightsideMargin);  
        AnchorPane.setBottomAnchor(innerAnchorPane, bottomsideMargin); 
    }
}

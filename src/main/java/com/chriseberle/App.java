package com.chriseberle;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        
        stage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("./resources/launcherLoginPage.fxml"));
        stage.setTitle("Launcher");

        stage.setScene(new Scene(root, 600, 400));
        stage.show();
       
    }

    public static void main(String[] args) {
        launch();
    }
}

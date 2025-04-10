package com.example.abyssalfate_fx.main;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Abyssal Fate");
        primaryStage.setScene(scene);
        primaryStage.show();  // Make sure this line is present
    }


    public static void main(String[] args) {
        launch(args);
    }
}


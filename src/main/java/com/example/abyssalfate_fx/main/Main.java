package com.example.abyssalfate_fx.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/firstscreen.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 1820, 980);
        primaryStage.setTitle("Abyssal Fate");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
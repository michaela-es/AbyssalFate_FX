package com.example.abyssalfate_fx.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreditsScreen {

    private Stage primaryStage;

    public void setStage(Stage stage) {
        this.primaryStage = stage;
    }


    @FXML
    private void handleBackToMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/menu.fxml"));
            Parent root = loader.load();

            MenuScreen menuScreen = loader.getController();
            menuScreen.setStage(primaryStage);

            primaryStage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
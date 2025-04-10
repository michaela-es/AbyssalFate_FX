package com.example.abyssalfate_fx.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuScreen {

    @FXML
    private Button startButton;
    @FXML
    private Button creditsButton;
    @FXML
    private Button exitButton;

    @FXML
    private void handleStartGame() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/home.fxml"));
            VBox homeRoot = loader.load();
            Scene homeScene = new Scene(homeRoot, 1820, 980);

            Stage stage = (Stage) startButton.getScene().getWindow();
            stage.setScene(homeScene);
            stage.setFullScreen(true);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCredits() {
        //credits screen
    }

    @FXML
    private void handleExit() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}


package com.example.abyssalfate_fx.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuScreen {

    @FXML
    private Button startButton;
    @FXML
    private Button creditsButton;
    @FXML
    private Button exitButton;

    private Stage primaryStage;

    public void setStage(Stage stage) {
        this.primaryStage = stage;
    }

    @FXML
    private void handleStartGame() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/home.fxml"));
            VBox homeRoot = loader.load();
            Scene homeScene = new Scene(homeRoot, 1820, 980);

            HomeScreen homeController = loader.getController();

            Stage stage = (Stage) startButton.getScene().getWindow();
            homeController.setStage(stage);

            stage.setScene(homeScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCredits() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/credits.fxml"));
            VBox creditsRoot = loader.load();
            Scene creditsScene = new Scene(creditsRoot, 1820, 980);

            CreditsScreen creditsController = loader.getController();

            Stage stage = (Stage) creditsButton.getScene().getWindow();
            creditsController.setStage(stage);

            stage.setScene(creditsScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleExit() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
package com.example.abyssalfate_fx.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeScreen {

    @FXML
    private Button pvpButton;
    @FXML
    private Button pvaiButton;
    @FXML
    private Button exitButton;

    private Stage primaryStage;

    public void setStage(Stage stage) {
        this.primaryStage = stage;
    }

    @FXML
    private void handlePVP() {
        //pvp screen
    }

    @FXML
    private void handlePVAI() {
        //comp screen
    }

    @FXML
    private void handleBackToMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/menu.fxml"));
            Parent root = loader.load();

            MenuScreen menuScreen = loader.getController();
            menuScreen.setStage(primaryStage);

            Stage stage = (Stage) pvpButton.getScene().getWindow();
            stage.setScene(new Scene(root, 1820, 980));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
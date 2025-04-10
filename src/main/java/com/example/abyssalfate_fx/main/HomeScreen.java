package com.example.abyssalfate_fx.main;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeScreen {

    @FXML
    private Button pvpButton;
    @FXML
    private Button pvaiButton;
    @FXML
    private Button exitButton;

    @FXML
    private void handlePVP() {
        //pvp screen
    }

    @FXML
    private void handlePVAI() {
        //comp screen
    }

    @FXML
    private void handleExit() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}


package com.example.abyssalfate_fx.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EndScreenController {

    @FXML
    private Label lblOutcome;

    public void setOutcome(boolean playerWon) {
        if (playerWon) {
            lblOutcome.setText("Congratulations! You have won the battle!");
        } else {
            lblOutcome.setText("You have been defeated. Better luck next time!");
        }
    }

    public void handleReturnToMenu(ActionEvent actionEvent) {

    }

    public void handleRestartBattle(ActionEvent actionEvent) {
    }
}
package com.example.abyssalfate_fx.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ResultScreenController {

    @FXML
    private Label lblOutcome;

    public void setOutcome(boolean player1Won) {
        if (player1Won) {
            lblOutcome.setText("Congratulations! Player 1 has won the battle!");
        } else {
            lblOutcome.setText("Player 2 has won the battle! Better luck next time!");
        }
    }

    @FXML
    public void handleReturnToMenu() {
    }
}
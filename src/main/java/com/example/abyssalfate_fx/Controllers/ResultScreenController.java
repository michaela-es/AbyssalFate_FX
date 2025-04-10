package com.example.abyssalfate_fx.Controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ResultScreenController {
    @FXML
    private Label lblOutcome;

    public void setOutcome(boolean player1Won) {
        if (player1Won) {
            lblOutcome.setText("Player 1 has won the battle!");
        } else {
            lblOutcome.setText("Player 2 has won the battle!");
        }
    }

    @FXML
    public void handleReturnToMenu(ActionEvent actionEvent) {
        try {
            // Close the current result screen
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.close();

            // Load the character selection menu
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/select_menu.fxml"));
            Parent root = loader.load();

            Stage selectStage = new Stage();
            Scene scene = new Scene(root, 1280, 720);
            scene.getStylesheets().add(getClass().getResource("/css/selectMenu.css").toExternalForm());

            SelectMenuController controller = loader.getController();
            controller.setStage(selectStage);

            selectStage.setTitle("Character Selection");
            selectStage.setScene(scene);
            selectStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Platform.exit();
        }
    }
}
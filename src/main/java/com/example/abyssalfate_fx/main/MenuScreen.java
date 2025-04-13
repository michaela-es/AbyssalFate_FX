package com.example.abyssalfate_fx.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

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
            Stage stage = (Stage) startButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/loading.fxml"));
            Parent loadingRoot = loader.load();

            LoadingScreen loadingController = loader.getController();
            Scene loadingScene = new Scene(loadingRoot, stage.getScene().getWidth(), stage.getScene().getHeight());
            stage.setScene(loadingScene);
            loadingController.startLoading(stage);

        } catch (IOException e) {
            System.err.println("Failed to load loading.fxml: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred in handleStartGame: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCredits() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/credits.fxml"));
            Parent creditsRoot = loader.load();
            Scene currentScene = creditsButton.getScene();
            Scene creditsScene = new Scene(creditsRoot, currentScene.getWidth(), currentScene.getHeight()); // Use current size

            CreditsScreen creditsController = loader.getController();
            Stage stage = (Stage) currentScene.getWindow();

            if (stage == null) {
                System.err.println("Stage could not be determined in handleCredits.");
                return;
            }

            if (creditsController != null) {
                // Pass the stage reference to the CreditsScreen controller
                creditsController.setStage(stage);
            } else {
                System.err.println("Warning: CreditsScreen controller instance is null.");
            }
            // --- End NPE Fix ---

            stage.setScene(creditsScene);

        } catch (IOException e) {
            System.err.println("Failed to load credits.fxml: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred in handleCredits: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @FXML
    private void handleExit() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        if (stage != null) {
            stage.close();
        } else {
            System.err.println("Stage is null in handleExit.");
        }
    }
}
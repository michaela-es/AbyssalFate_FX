package com.example.abyssalfate_fx.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import java.io.IOException;
//for sounds
import javafx.fxml.Initializable;
import javafx.scene.media.AudioClip;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuScreen implements Initializable{

    @FXML
    private Button startButton;
    @FXML
    private Button creditsButton;
    @FXML
    private Button exitButton;

    private Stage primaryStage;

    private AudioClip buttonClickSound;

    public void setStage(Stage stage) {
        this.primaryStage = stage;
    }

    @FXML
    private void handleStartGame() {
        playSoundEffect();
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
        playSoundEffect();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/credits.fxml"));
            Parent creditsRoot = loader.load();
            Scene currentScene = creditsButton.getScene();
            Scene creditsScene = new Scene(creditsRoot, currentScene.getWidth(), currentScene.getHeight());

            CreditsScreen creditsController = loader.getController();
            Stage stage = (Stage) currentScene.getWindow();

            if (stage == null) {
                System.err.println("Stage could not be determined in handleCredits.");
                return;
            }

            if (creditsController != null) {
                creditsController.setStage(stage);
            } else {
                System.err.println("Warning: CreditsScreen controller instance is null.");
            }

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
        playSoundEffect();
        Stage stage = (Stage) exitButton.getScene().getWindow();
        if (stage != null) {
            stage.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            URL sound = getClass().getResource("/sounds/click.wav");

            if (sound != null) {
                buttonClickSound = new AudioClip(sound.toExternalForm());
            } else {
                System.err.println("Error: Could not find button click sound file in " + getClass().getSimpleName() + "!");
            }
        } catch (Exception e) {
            System.err.println("Error loading button click sound in " + getClass().getSimpleName() + ": " + e.getMessage());
        }
    }

    private void playSoundEffect() {
        if (buttonClickSound != null) {
            buttonClickSound.play();
        }
    }
}
package com.example.abyssalfate_fx.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import java.io.IOException;
//for sounds
import javafx.fxml.Initializable;
import javafx.scene.media.AudioClip;
import java.net.URL;
import java.util.ResourceBundle;

public class CreditsScreen implements Initializable{

    private Stage primaryStage;

    private AudioClip buttonClickSound;

    public void setStage(Stage stage) {
        this.primaryStage = stage;
    }

    @FXML
    private void handleBackToMenu() {
        playSoundEffect();
        try {
            if (primaryStage == null) {
                System.err.println("Error: primaryStage is null in CreditsScreen.handleBackToMenu.");
                return;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/menu.fxml"));
            Parent root = loader.load();

            MenuScreen menuScreen = loader.getController();
            if (menuScreen != null) {
                menuScreen.setStage(primaryStage);
            } else {
                System.err.println("Warning: MenuScreen controller not found.");
            }

            Scene currentScene = primaryStage.getScene();
            Scene menuScene = new Scene(root, currentScene.getWidth(), currentScene.getHeight());
            primaryStage.setScene(menuScene);

        } catch (IOException e) {
            System.err.println("Failed to load menu.fxml: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred in handleBackToMenu: " + e.getMessage());
            e.printStackTrace();
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
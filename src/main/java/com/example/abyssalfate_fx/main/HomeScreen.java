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

public class HomeScreen implements Initializable{

    @FXML
    private Button pvpButton;
    @FXML
    private Button pvaiButton;
    @FXML
    private Button backButton;

    private Stage primaryStage;

    private AudioClip buttonClickSound;

    public void setStage(Stage stage) {
        this.primaryStage = stage;
    }

    @FXML
    private void handlePVP() {
        playSoundEffect();
        System.out.println("PVP button clicked - Implement navigation");
        // TODO: Implement PVP screen loading logic
        // Example: loadScene("/com/example/fxml/pvp_screen.fxml");
    }

    @FXML
    private void handlePVAI() {
        playSoundEffect();
        System.out.println("PVAI button clicked - Implement navigation");
        // TODO: Implement PVAI screen loading logic
        // Example: loadScene("/com/example/fxml/pvai_screen.fxml");
    }

    @FXML
    private void handleBackToMenu() {
        playSoundEffect();
        try {
            Stage stage = this.primaryStage;
            if (stage == null) {
                stage = (Stage) backButton.getScene().getWindow();
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/menu.fxml"));
            Parent root = loader.load();

            MenuScreen menuScreen = loader.getController();
            if (menuScreen != null) {
                menuScreen.setStage(stage);
            }

            Scene menuScene = new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight());
            stage.setScene(menuScene);

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
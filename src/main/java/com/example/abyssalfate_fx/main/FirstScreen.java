package com.example.abyssalfate_fx.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
//for sounds
import javafx.fxml.Initializable;
import javafx.scene.media.AudioClip;
import java.net.URL;
import java.util.ResourceBundle;

public class FirstScreen implements Initializable{

    private AudioClip buttonClickSound;

    @FXML
    private BorderPane rootPane;

    @FXML
    public void handleScreenTap(MouseEvent event) {
        playSoundEffect();
        Stage stage = null;
        try {
            if (rootPane != null && rootPane.getScene() != null) {
                stage = (Stage) rootPane.getScene().getWindow();
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/register.fxml"));
            Parent registerRoot = loader.load();

            RegisterScreen registerController = loader.getController();

            if(registerController != null) {
                registerController.setStage(stage);
            }

            Scene registerScene = new Scene(registerRoot, rootPane.getScene().getWidth(), rootPane.getScene().getHeight());
            stage.setScene(registerScene);
            stage.setTitle("Register");

        } catch (IOException e) {
            System.err.println("Failed to load register.fxml: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred in handleScreenTap: " + e.getMessage());
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
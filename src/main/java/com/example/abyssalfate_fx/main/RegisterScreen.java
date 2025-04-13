package com.example.abyssalfate_fx.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
//for sounds
import javafx.fxml.Initializable;
import javafx.scene.media.AudioClip;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterScreen implements Initializable{

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Button registerButton;
    @FXML private Button loginButton;
    @FXML private Label titleLabel;

    private AudioClip buttonClickSound;

    private Stage primaryStage;

    public void setStage(Stage stage) {
        this.primaryStage = stage;
    }

    @FXML
    private void handleRegister() {
        playSoundEffect();
        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        String confirmPass = confirmPasswordField.getText();

        if (username.isEmpty() || password.isEmpty() || confirmPass.isEmpty()) {
            showAlert("All fields must be filled!");
            return;
        }
        if (!password.equals(confirmPass)) {
            showAlert("Password does not match!");
            return;
        }

        if (UserManager.registerUser(username, password, confirmPass)) {
            showAlert("Your registration was successful!");
            navigateToLoginScreen();
        } else {
            showAlert("Username already exists. Please input new username.");
        }
    }

    @FXML
    public void handleLogin() {
        playSoundEffect();
        navigateToLoginScreen();
    }

    private void navigateToLoginScreen() {
        Stage stageToUse = this.primaryStage;

        if (stageToUse == null) {
            if (loginButton != null && loginButton.getScene() != null) {
                stageToUse = (Stage) loginButton.getScene().getWindow();
            }
            if (stageToUse == null && registerButton != null && registerButton.getScene() != null) {
                stageToUse = (Stage) registerButton.getScene().getWindow();
            }
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/login.fxml"));
            Parent loginRoot = loader.load();
            Scene currentScene = stageToUse.getScene();
            Scene loginScene = new Scene(loginRoot, currentScene.getWidth(), currentScene.getHeight());

            stageToUse.setScene(loginScene);
            stageToUse.setTitle("Login");

        } catch (IOException e) {
            showAlert("Error", "Error loading login screen: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            showAlert("Error", "Unexpected error navigating to login: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Register");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
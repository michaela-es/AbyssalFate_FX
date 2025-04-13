package com.example.abyssalfate_fx.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreen {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button goToRegisterButton;

    @FXML
    private Label titleLabel;
    public void initialize(URL location, ResourceBundle resources) {
        titleLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20;");
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Please fill in both fields.");
            return;
        }

        if (UserManager.validateLogin(username, password)) {
            showAlert("Login successful!");
            navigateToMenuScreen();
        } else {
            showAlert("Invalid username or password.");
        }
    }

    @FXML
    private void handleGoToRegister() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/register.fxml"));
            VBox registerRoot = loader.load();
            Scene registerScene = new Scene(registerRoot);

            Stage stage = (Stage) goToRegisterButton.getScene().getWindow();
            stage.setScene(registerScene);
            stage.setTitle("Register");
            stage.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Navigation Error");
            alert.setContentText("Could not load the Register screen.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    private void navigateToMenuScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/menu.fxml"));
            VBox menuRoot = loader.load();
            Scene menuScene = new Scene(menuRoot);

            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(menuScene);
            stage.setTitle("Abyssal Fate");
            stage.show();
        } catch (Exception e) {
            showAlert("Error loading menu screen: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Login");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

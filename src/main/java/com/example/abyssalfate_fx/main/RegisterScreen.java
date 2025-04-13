package com.example.abyssalfate_fx.main;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterScreen extends Application {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Button registerButton;

    @FXML
    private Label titleLabel;


    @FXML
    private void handleRegister() {
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
            showAlert("Registration successful!");

            navigateToLoginScreen();
        } else {
            showAlert("Username already exists.");
        }
    }

    public void handleLogin() {
        navigateToLoginScreen();
    }

    private void navigateToLoginScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/login.fxml"));
            VBox loginRoot = loader.load();
            Scene loginScene = new Scene(loginRoot);

            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.setScene(loginScene);
            stage.setTitle("Login");
            stage.show();

        } catch (Exception e) {
            showAlert("Error loading login screen: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Registration");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/register.fxml"));
        VBox root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Register");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}

package com.example.abyssalfate_fx.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class CreditsScreen {

    private Stage primaryStage;

    public void setStage(Stage stage) {
        this.primaryStage = stage;
    }


    @FXML
    private void handleBackToMenu() {
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

            // --- MODIFIED: Use current scene dimensions ---
            Scene currentScene = primaryStage.getScene();
            Scene menuScene = new Scene(root, currentScene.getWidth(), currentScene.getHeight());
            // --- End Modification ---

            primaryStage.setScene(menuScene);

        } catch (IOException e) {
            System.err.println("Failed to load menu.fxml: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred in handleBackToMenu: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
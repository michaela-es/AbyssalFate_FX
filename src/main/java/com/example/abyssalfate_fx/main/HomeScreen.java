package com.example.abyssalfate_fx.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class HomeScreen {

    @FXML
    private Button pvpButton;
    @FXML
    private Button pvaiButton;
    @FXML
    private Button exitButton; // Note: Button links to handleBackToMenu

    private Stage primaryStage;

    public void setStage(Stage stage) {
        this.primaryStage = stage;
    }

    @FXML
    private void handlePVP() {
        System.out.println("PVP button clicked - Implement navigation");
        // TODO: Implement PVP screen loading logic
        // Example: loadScene("/com/example/fxml/pvp_screen.fxml");
    }

    @FXML
    private void handlePVAI() {
        System.out.println("PVAI button clicked - Implement navigation");
        // TODO: Implement PVAI screen loading logic
        // Example: loadScene("/com/example/fxml/pvai_screen.fxml");
    }

    @FXML
    private void handleBackToMenu() {
        try {
            Stage stage = this.primaryStage;
            if (stage == null) {
                stage = (Stage) exitButton.getScene().getWindow();
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

    // Optional helper method for loading scenes (DRY principle)
    /*
    private void loadScene(String fxmlPath) {
        try {
            Stage stage = this.primaryStage;
             if (stage == null) {
                 stage = (Stage) pvpButton.getScene().getWindow(); // Use any button
             }
            if (stage == null) return;

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            // If the loaded scene's controller needs the stage:
            // Object controller = loader.getController();
            // if (controller instanceof YourControllerClass) {
            //    ((YourControllerClass) controller).setStage(stage);
            // }

            Scene scene = new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight());
            stage.setScene(scene);

        } catch (IOException e) {
            System.err.println("Failed to load scene: " + fxmlPath + " - " + e.getMessage());
            e.printStackTrace();
        }
    }
    */
}
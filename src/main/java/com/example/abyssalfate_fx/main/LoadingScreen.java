package com.example.abyssalfate_fx.main;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;

public class LoadingScreen {

    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label progressLabel;
    @FXML
    private Label titleLabel;

    private Stage primaryStage;
    private Timeline timeline;

    @FXML
    public void initialize() {
        progressBar.setProgress(0);
        progressLabel.setText("Entering the abyss... 0%");
    }

    public void startLoading(Stage stage) {
        if (stage == null) {
            System.err.println("Error: Stage provided to startLoading is null.");
            progressLabel.setText("Error: Cannot start loading.");
            return;
        }
        this.primaryStage = stage;

        if (timeline != null && timeline.getStatus() == Timeline.Status.RUNNING) {
            timeline.stop();
        }

        timeline = new Timeline();
        KeyValue kv = new KeyValue(progressBar.progressProperty(), 1.0);
        KeyFrame kf = new KeyFrame(Duration.seconds(3), kv);

        timeline.getKeyFrames().add(kf);

        progressBar.progressProperty().addListener((observable, oldValue, newValue) -> {
            int percentage = (int) (newValue.doubleValue() * 100);
            progressLabel.setText("Entering the abyss... " + percentage + "%");
        });

        timeline.setOnFinished(event -> {
            loadHomeScreen();
        });

        timeline.play();
    }

    private void loadHomeScreen() {
        try {
            if (primaryStage == null) {
                System.err.println("Error: primaryStage is null in loadHomeScreen.");
                progressLabel.setText("Error loading game!");
                return;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/home.fxml"));
            Parent homeRoot = loader.load();

            HomeScreen homeController = loader.getController();
            if (homeController != null) {
                homeController.setStage(primaryStage);
            } else {
                System.err.println("Warning: HomeScreen controller not found.");
            }

            Scene homeScene = new Scene(homeRoot, primaryStage.getScene().getWidth(), primaryStage.getScene().getHeight());
            primaryStage.setScene(homeScene);

        } catch (IOException e) {
            System.err.println("Failed to load home.fxml: " + e.getMessage());
            e.printStackTrace();
            progressLabel.setText("Error loading game!");
        } catch (Exception e) {
            System.err.println("An unexpected error occurred in loadHomeScreen: " + e.getMessage());
            e.printStackTrace();
            progressLabel.setText("Error loading game!");
        }
    }
}
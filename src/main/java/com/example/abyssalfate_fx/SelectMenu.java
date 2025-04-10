package com.example.abyssalfate_fx;

import com.example.abyssalfate_fx.Classes.*;
import com.example.abyssalfate_fx.Controllers.SelectMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class SelectMenu extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SelectMenu.class.getResource("/com/example/fxml/select_menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/selectMenu.css")).toExternalForm());

        SelectMenuController controller = fxmlLoader.getController();
        controller.setStage(primaryStage);
        primaryStage.setTitle("Character Selection");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
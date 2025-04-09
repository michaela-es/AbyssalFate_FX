package com.example.abyssalfate_fx;

import com.example.abyssalfate_fx.Classes.*;
import com.example.abyssalfate_fx.Controllers.ComputerBattleController;
import com.example.abyssalfate_fx.Helper.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        CharacterSetter entity = CharacterSetter.getInstance();
//        CharacterClass player = entity.player;
//        CharacterClass enemy = entity.enemy;

        CharacterClass player = new Rogue();
        CharacterClass enemy =  new Rogue();

        player.setEnemy(enemy);
        enemy.setEnemy(player);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/fxml/pve_battle.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/styles.css")).toExternalForm());
        ComputerBattleController controller = fxmlLoader.getController();
        controller.setupBattle(player, enemy);

        stage.setTitle("Computer Battle");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

package com.example.abyssalfate_fx;

import com.example.abyssalfate_fx.Classes.*;
import com.example.abyssalfate_fx.Controllers.PVPController;
import com.example.abyssalfate_fx.Helper.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class OpenPVP extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        CharacterSetter entity = CharacterSetter.getInstance();
        CharacterClass player1 = entity.player;
        CharacterClass player2 = entity.enemy;

        if (player1 == null)
            player1 = new Rogue();
        if (player2 == null)
            player2 =  new Rogue();

        player1.setEnemy(player2);
        player2.setEnemy(player1);

        FXMLLoader fxmlLoader = new FXMLLoader(OpenPVE.class.getResource("/com/example/fxml/pvp_battle.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/pvpstyles.css")).toExternalForm());
        PVPController controller = fxmlLoader.getController();
        controller.setPlayers(player1, player2);

        stage.setTitle("PVP Battle");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

package com.example.abyssalfate_fx.Controllers;

import com.example.abyssalfate_fx.Classes.CharacterClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    private CharacterClass player;
    private CharacterClass enemy;

    @FXML
    private Label playerName;
    @FXML
    private Label enemyName;

    @FXML
    private ProgressBar barPlayerHP;
    @FXML
    private ProgressBar barPlayerMP;
    @FXML
    private ProgressBar barEnemyHP;
    @FXML
    private ProgressBar barEnemyMP;

    public HelloController() {}

    public void setCharacters(CharacterClass player, CharacterClass enemy) {
        this.player = player;
        this.enemy = enemy;
        updateUI();
    }

    private void updateUI() {
        if (player != null && enemy != null) {
            playerName.setText(player.getClass().getSimpleName());
            enemyName.setText("Enemy: " + enemy.getClass().getSimpleName());

            double playerProgress = (double) player.getHp() / player.getMaxHp();
            double enemyProgress = (double) enemy.getHp() / enemy.getMaxHp();

            barPlayerHP.setProgress(playerProgress);
            barEnemyHP.setProgress(enemyProgress);

            double playerMana = (double) player.getMana() / player.getMaxMana();
            double enemyMana = (double) enemy.getMana() / enemy.getMaxMana();

            barPlayerMP.setProgress(playerMana);
            barEnemyMP.setProgress(enemyMana);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // No need to update UI here
    }

    // Unused action handlers
    public void handleSkill1Action(ActionEvent actionEvent) {
    }

    public void handleSkill2Action(ActionEvent actionEvent) {
    }

    public void handleSkill3Action(ActionEvent actionEvent) {
    }
}

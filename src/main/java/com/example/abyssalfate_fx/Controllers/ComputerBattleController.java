package com.example.abyssalfate_fx.Controllers;

import com.example.abyssalfate_fx.Classes.CharacterClass;
import com.example.abyssalfate_fx.Helper.Combat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.application.Platform;

import java.io.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class ComputerBattleController implements Initializable {

    @FXML
    public Label enemyName;

    @FXML
    public ProgressBar barEnemyHP;

    @FXML
    public ProgressBar barEnemyMP;

    @FXML
    public Label playerName;

    @FXML
    public ProgressBar barPlayerHP;

    @FXML
    public ProgressBar barPlayerMP;

    @FXML
    public TextArea txtEvents;

    @FXML
    public TextArea txtSkillHover;

    @FXML
    public Button btnSkill1;

    @FXML
    public Button btnSkill2;

    @FXML
    public Button btnSkill3;

    public CharacterClass player;
    public CharacterClass enemy;
    public Combat combat = new Combat();
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setupBattle(CharacterClass player, CharacterClass enemy) {
        this.player = player;
        this.enemy = enemy;
        setupUI();
        hoverFX();
    }

    public void setupUI() {
        enemyName.setText(enemy.getClass().getSimpleName() + "(Enemy)");
        playerName.setText(player.getClass().getSimpleName());

        btnSkill1.setText(player.nameSkill1);
        btnSkill2.setText(player.nameSkill2);
        btnSkill3.setText(player.nameSkill3);

        writeBattleLog("PVE Battle" + "Player is " + player.getClass().getSimpleName() + "\nEnemy is " + enemy.getClass().getSimpleName());
        updateProgressBars();
    }
    public void writeBattleLog(String logMessage) {
        String filePath = "src/main/java/com/example/abyssalfate_fx/logs/BattleLog";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(logMessage);
            writer.newLine(); // Adds a new line
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    private void updateProgressBars() {
        Platform.runLater(() -> {

            double playerHp = Math.max(0, Math.min(player.getHp(), player.getMaxHp()));
            double enemyHp = Math.max(0, Math.min(enemy.getHp(), enemy.getMaxHp()));
            double playerMana = Math.max(0, Math.min(player.getMana(), player.getMaxMana()));
            double enemyMana = Math.max(0, Math.min(enemy.getMana(), enemy.getMaxMana()));

            double playerProgress = (double) playerHp / player.getMaxHp();
            double enemyProgress = (double) enemyHp / enemy.getMaxHp();
            double playerManaProgress = (double) playerMana / player.getMaxMana();
            double enemyManaProgress = (double) enemyMana / enemy.getMaxMana();

            barPlayerHP.setProgress(playerProgress);
            barEnemyHP.setProgress(enemyProgress);
            barPlayerMP.setProgress(playerManaProgress);
            barEnemyMP.setProgress(enemyManaProgress);
        });
    }


    public void speakEvent(String event) {
        writeBattleLog(event);
        final int MAX_LINES = 10;

        txtEvents.appendText(event + "\n");
        String[] lines = txtEvents.getText().split("\n");

        if (lines.length > MAX_LINES) {
            StringBuilder newText = new StringBuilder();

            for (int i = lines.length - MAX_LINES; i < lines.length; i++) {
                newText.append(lines[i]).append("\n");
            }

            txtEvents.setText(newText.toString());
        }
    }

    @FXML
    private void handleSkill1Action() {
        int baseroll = player.rollToHit();
        combat.checkCrit(baseroll);
        int totalRoll = combat.totalRoll(baseroll, player.getHitBonus());
        boolean isHit = combat.isHit(totalRoll, enemy.getAC());

        if (isHit) {
            int damage = player.skill1();
            enemy.loseHP(damage);
            speakEvent("Player hits with " + player.nameSkill1 + " for " + damage + " damage.");
        } else {
            speakEvent("Player misses their attack.");
        }
        updateProgressBars();
        checkBattleOutcome();
        enemyTurn();
    }

    @FXML
    private void handleSkill2Action() {


        if (player.getMana() >= player.costSkill2) {

            player.loseMana(player.costSkill2);

            int baseroll = player.rollToHit();
            combat.checkCrit(baseroll);
            int totalRoll = combat.totalRoll(baseroll, player.getHitBonus());
            boolean isHit = combat.isHit(totalRoll, enemy.getAC());

            if (isHit) {
                int damage = player.skill2();
                enemy.loseHP(damage);
                speakEvent("Player hits with " + player.nameSkill2 + " for " + damage + " damage.");
            } else {
                speakEvent("Player misses their attack.");
            }
        } else {
            speakEvent("Not enough mana for Skill 2.");
        }
        updateProgressBars();
        checkBattleOutcome();
        enemyTurn();
    }

    @FXML
    private void handleSkill3Action() {

        if (player.getMana() >= player.costSkill3) {
            player.loseMana(player.costSkill3);
            int baseroll = player.rollToHit();
            combat.checkCrit(baseroll);
            int totalRoll = combat.totalRoll(baseroll, player.getHitBonus());
            boolean isHit = combat.isHit(totalRoll, enemy.getAC());

            if (isHit) {
                int damage = player.skill3();
                enemy.loseHP(damage);
                speakEvent("Player hits with " + player.nameSkill3 + " for " + damage + " damage.");
            } else {
                speakEvent("Player misses their attack.");
            }
        } else {
            speakEvent("Not enough mana for Skill 3.");
        }
        updateProgressBars();
        checkBattleOutcome();
        enemyTurn();
    }

    private void enemyTurn() {
        int baseroll = enemy.rollToHit();
        combat.checkCrit(baseroll);
        int totalRoll = combat.totalRoll(baseroll, enemy.getHitBonus());
        boolean isHit = combat.isHit(totalRoll, player.getAC());

        if (isHit) {
            int damage = combat.calcDamage(enemy.chooseSkill());
            player.loseHP(damage);
            speakEvent("Enemy hits player with " + enemy.getSkillName() + " for " + damage + " damage.");
        } else {
            speakEvent("Enemy misses.");
        }
        updateProgressBars();
        checkBattleOutcome();
    }

    private void checkBattleOutcome() {
        if (player.getHp() <= 0) {
            // Player has lost
            writeBattleLog("Player lost");
            showBattleEndScreen(stage, false);
        } else if (enemy.getHp() <= 0) {
            // Player has won
            writeBattleLog("Player won");
            showBattleEndScreen(stage, true);
        }
    }
    private void showBattleEndScreen(Stage stage, boolean playerWon) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/EndScreen.fxml"));
            Parent root = loader.load();

            EndScreenController endScreenController = loader.getController();
            endScreenController.setOutcome(playerWon);

            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to load the end screen.", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void hoverFX() {
        btnSkill1.setOnMouseEntered(e -> showSkillDescription(player.descSkill1));
        btnSkill1.setOnMouseExited(e -> clearSkillDescription());

        btnSkill2.setOnMouseEntered(e -> showSkillDescription(player.descSkill2));
        btnSkill2.setOnMouseExited(e -> clearSkillDescription());

        btnSkill3.setOnMouseEntered(e -> showSkillDescription(player.descSkill3));
        btnSkill3.setOnMouseExited(e -> clearSkillDescription());
    }

    private void showSkillDescription(String description) {
        txtSkillHover.setText(description);
    }

    private void clearSkillDescription() {
        txtSkillHover.setText("");
    }
}

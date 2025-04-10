package com.example.abyssalfate_fx.Controllers;

import com.example.abyssalfate_fx.Classes.*;
import com.example.abyssalfate_fx.Helper.Combat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.*;
import java.io.IOException;
import java.net.URL;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.ResourceBundle;

public class PVPController implements Initializable {
    @FXML
    public ProgressBar healthBarPlayer2;
    @FXML
    public ProgressBar manaBarPlayer2;
    @FXML
    public ProgressBar healthBarPlayer1;
    @FXML
    public ProgressBar manaBarPlayer1;
    @FXML
    public TextArea hoverPlayer1;
    @FXML
    public Label imagePlayer1;
    @FXML
    public Label imagePlayer2;
    @FXML
    public TextArea hoverPlayer2;
    @FXML
    public Button btnP2Skill1;
    @FXML
    public Button btnP2Skill2;
    @FXML
    public Button btnP2Skill3;
    @FXML
    public Button btnP1Skill1;
    @FXML
    public Button btnP1Skill2;
    @FXML
    public Button btnP1Skill3;

    @FXML
    public TextArea nameP1;

    @FXML
    public TextArea nameP2;

    @FXML
    public TextArea eventLog;

    CharacterClass player1;
    CharacterClass player2;
    Combat combat = new Combat();
    boolean player1Turn = true;
    private String event;
    public void setPlayers(CharacterClass player1, CharacterClass player2) {
        this.player1 = player1;
        this.player2 = player2;
        setupUI();
        hoverFX();
    }

    public void setupUI() {
        btnP1Skill1.setText(player1.nameSkill1);
        btnP1Skill2.setText(player1.nameSkill2);
        btnP1Skill3.setText(player1.nameSkill3);

        btnP2Skill1.setText(player2.nameSkill1);
        btnP2Skill2.setText(player2.nameSkill2);
        btnP2Skill3.setText(player2.nameSkill3);

        nameP1.setText(player1.getClass().getSimpleName());
        nameP2.setText(player2.getClass().getSimpleName());

        healthBarPlayer1.getStyleClass().add("progress-bar");
        manaBarPlayer1.getStyleClass().add("progress-bar-mana");
        healthBarPlayer2.getStyleClass().add("progress-bar");
        manaBarPlayer2.getStyleClass().add("progress-bar-mana");

        updateProgressBars();
    }

    private void hoverFX() {
        // Player 1 Skills
        btnP1Skill1.setOnMouseEntered(event -> hoverPlayer1.setText(player1.descSkill1));
        btnP1Skill1.setOnMouseExited(event -> hoverPlayer1.setText(""));

        btnP1Skill2.setOnMouseEntered(event -> hoverPlayer1.setText(player1.descSkill2));
        btnP1Skill2.setOnMouseExited(event -> hoverPlayer1.setText(""));

        btnP1Skill3.setOnMouseEntered(event -> hoverPlayer1.setText(player1.descSkill3));
        btnP1Skill3.setOnMouseExited(event -> hoverPlayer1.setText(""));

        // Player 2 Skills
        btnP2Skill1.setOnMouseEntered(event -> hoverPlayer2.setText(player2.descSkill1));
        btnP2Skill1.setOnMouseExited(event -> hoverPlayer2.setText(""));

        btnP2Skill2.setOnMouseEntered(event -> hoverPlayer2.setText(player2.descSkill2));
        btnP2Skill2.setOnMouseExited(event -> hoverPlayer2.setText(""));

        btnP2Skill3.setOnMouseEntered(event -> hoverPlayer2.setText(player2.descSkill3));
        btnP2Skill3.setOnMouseExited(event -> hoverPlayer2.setText(""));
    }

    private void updateProgressBars() {
        double healthP1 = (double) player1.getHp() / player1.getMaxHp();
        double healthP2 = (double) player2.getHp() / player2.getMaxHp();

        healthBarPlayer1.setProgress(healthP1);
        healthBarPlayer2.setProgress(healthP2);

        double manaP1 = (double) player1.getMana() / player1.getMaxMana();
        double manaP2 = (double) player2.getMana() / player2.getMaxMana();

        manaBarPlayer1.setProgress(manaP1);
        manaBarPlayer2.setProgress(manaP2);

        checkForGameOver();
    }

    @FXML
    private void handleP1Skill1Action() {
        if (player1Turn) {
            handlePlayer1Attack(1);
        }
    }

    @FXML
    private void handleP1Skill2Action() {
        if (player1Turn && player1.getMana() >= player1.costSkill2) {
            handlePlayer1Attack(2);
        } else {
            speakEvent("Not enough mana for Skill 2.");
        }
    }

    @FXML
    private void handleP1Skill3Action() {
        if (player1Turn && player1.getMana() >= player1.costSkill3) {
            handlePlayer1Attack(3);
        } else {
            speakEvent("Not enough mana for Skill 3.");
        }
    }

    private void handlePlayer1Attack(int skillNumber) {
        int baseroll = player1.rollToHit();
        combat.checkCrit(baseroll);
        int totalRoll = combat.totalRoll(baseroll, player1.getHitBonus());
        boolean isHit = combat.isHit(totalRoll, player2.getAC());

        int damage = 0;
        switch (skillNumber) {
            case 1:
                damage = combat.calcDamage(player1.skill1());
                break;
            case 2:
                damage = combat.calcDamage(player1.skill2());
                player1.loseMana(player1.costSkill2);
                break;
            case 3:
                damage = combat.calcDamage(player1.skill3());
                player1.loseMana(player1.costSkill3);
                break;
        }

        if (isHit) {
            player2.loseHP(damage);
            speakEvent("Player 1 hits Player 2 with Skill " + player1.getSkillName() + " for " + damage + " damage.");
        } else {
            speakEvent("Player 1 misses Player 2.");
        }
        updateProgressBars();
        player1Turn = false;

    }

    @FXML
    private void handleP2Skill1Action() {
        if (!player1Turn) {
            handlePlayer2Attack(1);
        }
    }

    @FXML
    private void handleP2Skill2Action() {
        if (!player1Turn && player2.getMana() >= player2.costSkill2) {
            handlePlayer2Attack(2);
        } else {
            speakEvent("Not enough mana for Skill 2.");
        }
    }

    @FXML
    private void handleP2Skill3Action() {
        if (!player1Turn && player2.getMana() >= player2.costSkill3) {
            handlePlayer2Attack(3);
        } else {
            speakEvent("Not enough mana for Skill 3.");
        }
    }

    private void handlePlayer2Attack(int skillNumber) {
        int baseroll = player2.rollToHit();
        combat.checkCrit(baseroll);
        int totalRoll = combat.totalRoll(baseroll, player2.getHitBonus());
        boolean isHit = combat.isHit(totalRoll, player1.getAC());

        int damage = 0;
        switch (skillNumber) {
            case 1:
                damage = combat.calcDamage(player2.skill1());
                break;
            case 2:
                damage = combat.calcDamage(player2.skill2());
                player2.loseMana(player2.costSkill2);
                break;
            case 3:
                damage = combat.calcDamage(player2.skill3());
                player2.loseMana(player2.costSkill3);
                break;
        }

        if (isHit) {
            player1.loseHP(damage);
            speakEvent("Player 2 hits Player 1 with Skill " + player2.getSkillName() + " for " + damage + " damage.");
        } else {
            speakEvent("Player 2 misses Player 1.");
        }
        updateProgressBars();
        player1Turn = true;
    }

    public void speakEvent(String event){
        eventLog.appendText(event+"\n");
    }

    private void showResultScreen(boolean player1Won) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/result_screen.fxml"));
            Parent root = loader.load();

            ResultScreenController resultController = loader.getController();
            resultController.setOutcome(player1Won);

            // Get the current stage and set the new scene
            Stage stage = (Stage) eventLog.getScene().getWindow();
            stage.setScene(new Scene(root, 1280, 720));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkForGameOver() {
        if (player1.getHp() <= 0) {
            speakEvent("Player 1 has been defeated!");
            showResultScreen(false); // Player 1 lost
        } else if (player2.getHp() <= 0) {
            speakEvent("Player 2 has been defeated!");
            showResultScreen(true); // Player 1 won
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Any initialization that doesn't depend on player and enemy
    }
}

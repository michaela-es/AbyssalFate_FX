package com.example.abyssalfate_fx.Controllers;

import com.example.abyssalfate_fx.Classes.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.*;

import java.util.Objects;

public class PVPController {
    @FXML
    public ProgressBar healthBarPlayer2;
    @FXML
    public ProgressBar manaBarPlayer2;
    @FXML
    public ProgressBar healthBarPlayer1;
    @FXML
    public ProgressBar manaBarPlayer1;
    @FXML
    public TextField hoverPlayer1;
    @FXML
    public Label imagePlayer1;
    @FXML
    public Label imagePlayer2;
    @FXML
    public TextField hoverPlayer2;
    @FXML
    public Button btnP2Skill2;
    @FXML
    public Button btnP2Skill1;
    @FXML
    public Button btnP2Skill3;
    @FXML
    public Button btnP1Skill1;
    @FXML
    public Button btnP1Skill2;
    @FXML
    public Button btnP1Skill3;

    CharacterClass player1;
    CharacterClass player2;
    public void setPlayers(CharacterClass player1,CharacterClass player2){
        this.player1 = player1;
        this.player2 = player2;
        setupUI();
      //  hoverFX();
    }
    public void setupUI(){
        btnP1Skill1.setText(player1.nameSkill1);
        btnP1Skill2.setText(player1.nameSkill2);
        btnP1Skill3.setText(player1.nameSkill3);

        btnP2Skill1.setText(player2.nameSkill1);
        btnP2Skill2.setText(player2.nameSkill2);
        btnP2Skill3.setText(player2.nameSkill3);

        healthBarPlayer1.getStyleClass().add("progress-bar");
        manaBarPlayer1.getStyleClass().add("progress-bar-mana");
        healthBarPlayer2.getStyleClass().add("progress-bar");
        manaBarPlayer2.getStyleClass().add("progress-bar-mana");

        updateProgressBars();
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
    }

}

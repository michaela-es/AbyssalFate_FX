package com.example.abyssalfate_fx.Controllers;

import com.example.abyssalfate_fx.Classes.*;
import com.example.abyssalfate_fx.Helper.CharacterSetter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class SelectMenuController {
    @FXML public ToggleButton btnPVE;
    @FXML public ToggleButton btnPVP;
    @FXML public Button btnLock;
    @FXML public TextArea textCharStory;
    @FXML public Text txtClassName;
    @FXML public Button btnBack;
    @FXML public Button btnNext;

    private final CharacterList charList = new CharacterList();
    private int currentCharacterIndex = 0;
    private CharacterClass currentCharacter;
    private boolean isPlayer1Selecting = true;
    private Stage stage;
    private CharacterClass player1Selection;
    private CharacterClass player2Selection;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        if (charList.getCharacterCount() > 0) {
            currentCharacter = charList.getCharacter(0);
            updateCharacterDisplay();
        }

        btnNext.setOnAction(event -> nextCharacter());
        btnBack.setOnAction(event -> previousCharacter());
        btnLock.setOnAction(event -> lockInCharacter());

        // Initialize toggle buttons
        btnPVE.setSelected(true);
        btnPVE.setOnAction(event -> {
            btnPVE.setSelected(true);
            btnPVP.setSelected(false);
            resetSelectionState();
        });

        btnPVP.setOnAction(event -> {
            btnPVP.setSelected(true);
            btnPVE.setSelected(false);
            resetSelectionState();
        });
    }

    private void resetSelectionState() {
        isPlayer1Selecting = true;
        currentCharacterIndex = 0;
        currentCharacter = charList.getCharacter(0);
        updateCharacterDisplay();
    }

    private void nextCharacter() {
        if (charList.getCharacterCount() == 0) return;
        currentCharacterIndex = (currentCharacterIndex + 1) % charList.getCharacterCount();
        currentCharacter = charList.getCharacter(currentCharacterIndex);
        updateCharacterDisplay();
    }

    private void previousCharacter() {
        if (charList.getCharacterCount() == 0) return;
        currentCharacterIndex = (currentCharacterIndex - 1 + charList.getCharacterCount()) % charList.getCharacterCount();
        currentCharacter = charList.getCharacter(currentCharacterIndex);
        updateCharacterDisplay();
    }

    private void updateCharacterDisplay() {
        if (currentCharacter != null) {
            txtClassName.setText(currentCharacter.getClass().getSimpleName());
            textCharStory.setText(currentCharacter.backstory);

            // Update UI to show which player is selecting in PVP mode
            if (btnPVP.isSelected()) {
                txtClassName.setText((isPlayer1Selecting ? "Player 1: " : "Player 2: ") +
                        currentCharacter.getClass().getSimpleName());
            }
        }
    }

    private void lockInCharacter() {
        if (currentCharacter == null) return;

        if (btnPVE.isSelected()) {
            // PVE mode - start game with selected character vs random AI
            startPVEGame(currentCharacter);
        } else if (btnPVP.isSelected()) {
            if (isPlayer1Selecting) {
                // Store player 1's selection and switch to player 2
                player1Selection = currentCharacter;
                isPlayer1Selecting = false;
                currentCharacterIndex = 0;
                currentCharacter = charList.getCharacter(0);
                updateCharacterDisplay();
            } else {
                // Store player 2's selection and start PVP game
                player2Selection = currentCharacter;
                startPVPGame(player1Selection, player2Selection);
            }
        }
    }

    private void startPVEGame(CharacterClass player) {
        try {
            CharacterClass enemy = charList.getRandomChar();

            // Set characters in CharacterSetter if needed
            CharacterSetter entity = CharacterSetter.getInstance();
            entity.player = player;
            entity.enemy = enemy;

            // Set enemies
            player.setEnemy(enemy);
            enemy.setEnemy(player);

            // Load PVE battle screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/pve_battle.fxml"));
            Stage battleStage = new Stage();
            Scene scene = new Scene(loader.load(), 1280, 720);
            scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());

            ComputerBattleController controller = loader.getController();
            controller.setStage(battleStage);
            controller.setupBattle(player, enemy);

            // Close selection screen
            stage.close();

            battleStage.setTitle("PVE Battle");
            battleStage.setScene(scene);
            battleStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startPVPGame(CharacterClass player1, CharacterClass player2) {
        try {
            // Set characters in CharacterSetter if needed
            CharacterSetter entity = CharacterSetter.getInstance();
            entity.player = player1;
            entity.enemy = player2;

            // Set enemies
            player1.setEnemy(player2);
            player2.setEnemy(player1);

            // Load PVP battle screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/pvp_battle.fxml"));
            Stage battleStage = new Stage();
            Scene scene = new Scene(loader.load(), 1280, 720);
            scene.getStylesheets().add(getClass().getResource("/css/pvpstyles.css").toExternalForm());

            PVPController controller = loader.getController();
            controller.setPlayers(player1, player2);

            stage.close();

            battleStage.setTitle("PVP Battle");
            battleStage.setScene(scene);
            battleStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
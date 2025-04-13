package com.example.abyssalfate_fx.main;

import com.example.abyssalfate_fx.Classes.CharactersSelection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class CharacterSelectScreen extends JFrame {
    private ArrayList<CharactersSelection> characters = new ArrayList<>();
    private MediaPlayer mediaPlayer;

    public CharacterSelectScreen() {
        // Initialize JavaFX for MediaPlayer
        new JFXPanel();

        setTitle("Choose Your FATE");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(900, 600));

        BackgroundPanel backgroundPanel = new BackgroundPanel("/abyss.gif");
        backgroundPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("CHOOSE YOUR FATE");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        backgroundPanel.add(titleLabel, BorderLayout.NORTH);

        initCharacters();

        JPanel cardPanel = drawCharacterCards();
        backgroundPanel.add(cardPanel, BorderLayout.CENTER);

        setContentPane(backgroundPanel);

        // Play selection music
        playMusic("selection.mp3");
    }

    private void initCharacters() {
        characters.add(new CharactersSelection("Heathcliff", "/Mage.png", true, 4, 10000, 600, 700, "Hailing..."));
        characters.add(new CharactersSelection("Green Guy", "/mage.gif", true, 4, 12000, 550, 800, "Known..."));
        characters.add(new CharactersSelection("Mage Girl", "/girl.png", true, 5, 9500, 750, 600, "This bright..."));
        characters.add(new CharactersSelection("Blaze", "/blaze.png", true, 4, 10500, 700, 550, "Blaze lives..."));
        characters.add(new CharactersSelection("Nova", "/nova.png", true, 5, 9800, 650, 750, "Drawing power..."));
        characters.add(new CharactersSelection("Titan", "/titan.png", true, 5, 15000, 500, 950, "Forged in..."));
        characters.add(new CharactersSelection("Voltaire", "/voltaire.png", true, 5, 11000, 700, 600, "Born of storm..."));
        characters.add(new CharactersSelection("Shade", "/Mage.png", true, 4, 10200, 650, 720, "Legends persist..."));
    }

    private JPanel drawCharacterCards() {
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.setOpaque(false);

        JPanel cardPanel = new JPanel(new GridLayout(2, 4, 20, 20));
        cardPanel.setOpaque(false);

        for (CharactersSelection c : characters) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            card.setBackground(new Color(15, 15, 35, 200));
            card.setPreferredSize(new Dimension(160, 180));

            ImageIcon icon = new ImageIcon(c.getImagePath());
            Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imageLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

            JLabel charName = new JLabel(c.getName());
            charName.setHorizontalAlignment(SwingConstants.CENTER);
            charName.setForeground(new Color(210, 210, 220));
            charName.setFont(new Font("Arial", Font.BOLD, 14));
            charName.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

            card.add(imageLabel, BorderLayout.CENTER);
            card.add(charName, BorderLayout.SOUTH);

            card.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    card.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
                }

                public void mouseExited(MouseEvent e) {
                    card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                }

                public void mouseClicked(MouseEvent e) {
                    new CharacterDetailsScreen(c).setVisible(true);
                    if (mediaPlayer != null) {
                        mediaPlayer.stop(); // stop music on transition
                    }
                    dispose();
                }
            });

            cardPanel.add(card);
        }

        wrapperPanel.add(cardPanel);
        return wrapperPanel;
    }

    private void playMusic(String fileName) {
        try {
            String path = getClass().getResource("/music/" + fileName).toExternalForm();
            Media media = new Media(path);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop music
            mediaPlayer.play();
        } catch (Exception e) {
            System.out.println("Error playing music: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CharacterSelectScreen().setVisible(true));
    }
}

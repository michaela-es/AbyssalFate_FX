package com.example.abyssalfate_fx.main;

import com.example.abyssalfate_fx.Classes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CharacterSelectScreen extends JFrame {
    private ArrayList<CharactersSelection> characters = new ArrayList<>();
    private CharacterList characterList;

    public CharacterSelectScreen() {
        characterList = new CharacterList();
        initCharacters();

        setTitle("Choose Your FATE");
        setResizable(false);
        setSize(1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(900, 600));

        setLayout(new BorderLayout());

        // Set background
        JPanel backgroundPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(new Color(100, 170, 255));
            }
        };
        add(backgroundPanel);

        // Title label
        JLabel titleLabel = new JLabel("CHOOSE YOUR FATE");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        backgroundPanel.add(titleLabel, BorderLayout.NORTH);

        backgroundPanel.add(drawCharacterCards(), BorderLayout.CENTER);
    }

    private void initCharacters() {
        for (int i = 0; i < characterList.getCharacterCount(); i++) {
            CharacterClass characterClass = characterList.getCharacter(i);
            // Create a CharactersSelection instance for each character class
            CharactersSelection characterSelection = new CharactersSelection(characterClass, "This is the story of " + characterClass.getClass().getSimpleName() + ".");
            characters.add(characterSelection);
        }
    }

    private JPanel drawCharacterCards() {
        JPanel wrapperPanel = new JPanel(new GridBagLayout()); // Centers the grid
        wrapperPanel.setOpaque(false); // Transparent so we see background

        JPanel cardPanel = new JPanel(new GridLayout(2, 4, 20, 20));
        cardPanel.setOpaque(false);

        for (CharactersSelection c : characters) {
            JPanel card = new JPanel();
            card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            card.setBackground(Color.WHITE);
            card.setPreferredSize(new Dimension(160, 100));
            card.setLayout(new BorderLayout());

            // Use getName() to get the character's name
            JLabel charName = new JLabel(c.getName());
            charName.setHorizontalAlignment(SwingConstants.CENTER);
            charName.setFont(new Font("Arial", Font.BOLD, 14));
            card.add(charName, BorderLayout.CENTER);

            // Mouse listener for hover effect
            card.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    card.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
                }

                public void mouseExited(MouseEvent e) {
                    card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                }

                public void mouseClicked(MouseEvent e) {
                    // Open CharacterDetailsScreen with the selected character
                    new CharacterDetailsScreen(c).setVisible(true);
                    dispose(); // Close the character selection screen
                }
            });

            cardPanel.add(card);
        }

        wrapperPanel.add(cardPanel); // Center the grid in the panel
        return wrapperPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CharacterSelectScreen().setVisible(true));
    }
}
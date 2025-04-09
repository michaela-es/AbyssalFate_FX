package com.example.abyssalfate_fx.main;

import com.example.abyssalfate_fx.Classes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectCharComputer extends JFrame {

    private CharacterList characterList;
    private int currentIndex = 0;
    private JTextField nameField;
    private JTextArea backstoryArea;
    private JLabel characterLabel;

    public SelectCharComputer() {
        characterList = new CharacterList();
        createGUI();
    }

    private void createGUI() {
        // Set up the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Character Selection");
        this.setSize(1280, 720); // Frame size
        this.setLayout(new BorderLayout());

        // Create panels
        JPanel topPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JPanel randomPanel = new JPanel();

        // Top panel for navigation buttons
        JButton prevButton = new JButton("Previous");
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentIndex > 0) {
                    currentIndex--;
                    updateCharacterInfo();
                }
            }
        }
        );

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentIndex < characterList.getCharacterCount() - 1) {
                    currentIndex++;
                    updateCharacterInfo();
                }
            }
        });

        topPanel.add(prevButton);
        topPanel.add(nextButton);

        // Center panel for character info
        characterLabel = new JLabel("Character: ");
        nameField = new JTextField(20);
        nameField.setEditable(false);
        backstoryArea = new JTextArea(20, 40); // Larger JTextArea for backstory
        backstoryArea.setEditable(false);
        backstoryArea.setLineWrap(true); // Enable line wrapping
        backstoryArea.setWrapStyleWord(true); // Wrap at word boundaries

        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(characterLabel);
        centerPanel.add(nameField);
        centerPanel.add(new JScrollPane(backstoryArea)); // Use JScrollPane for JTextArea

        // Bottom panel for choose and random buttons
        JButton chooseButton = new JButton("Choose Character");
        chooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CharacterClass character = characterList.getCharacter(currentIndex);
                if (character != null) {
                    JOptionPane.showMessageDialog(SelectCharComputer.this, "You have chosen: " + character.getClass().getSimpleName());
                }
            }
        });

        JButton randomButton = new JButton("Random");
        randomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CharacterClass randomCharacter = characterList.getRandomChar();
                if (randomCharacter != null) {
                    JOptionPane.showMessageDialog(SelectCharComputer.this, "Random character: " + randomCharacter.toString());
                }
            }
        });

        bottomPanel.add(chooseButton);
        randomPanel.add(randomButton);

        // Add components to the frame
        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(randomPanel, BorderLayout.EAST);

        // Initialize character info
        updateCharacterInfo();

        this.setVisible(true); // Removed pack() to keep the specified size
    }

    private void updateCharacterInfo() {
        CharacterClass character = characterList.getCharacter(currentIndex);
        if (character != null) {
            nameField.setText(character.getClass().getSimpleName()); // Assuming toString() provides the character name
            backstoryArea.setText(character.backstory); // Replace with actual backstory logic
            characterLabel.setText("Character: " + (currentIndex + 1) + "/" + characterList.getCharacterCount());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SelectCharComputer();
            }
        });
    }
}

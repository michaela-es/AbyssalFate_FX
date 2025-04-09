package com.example.abyssalfate_fx.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends JFrame {

    public HomeScreen() {
        setTitle("Abyssal Fate");
        setSize(600, 500);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        BackgroundPanel backgroundLabel = new BackgroundPanel("/game_background.gif");
        backgroundLabel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel welcomeLabel = new JLabel("Welcome to Abyssal Fate!");
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 30));
        welcomeLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        backgroundLabel.add(welcomeLabel, gbc);

        JButton pvpButton = new JButton("Player vs Player");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        backgroundLabel.add(pvpButton, gbc);

        JButton pvaiButton = new JButton("Player vs AI");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        backgroundLabel.add(pvaiButton, gbc);

        setContentPane(backgroundLabel);
        setVisible(true);

        pvpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); //for now :)
                //new screen for PvP
            }
        });

        pvpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the home screen
                SwingUtilities.invokeLater(() -> new CharacterSelectScreen().setVisible(true)); // Open character selection screen
            }
        });

    }
}

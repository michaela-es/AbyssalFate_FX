package com.example.abyssalfate_fx.main;

import com.example.abyssalfate_fx.Classes.*;

import javax.swing.*;
import java.awt.*;

public class CharacterDetailsScreen extends JFrame {
    private CharactersSelection character;

    // Constructor
    public CharacterDetailsScreen(CharactersSelection character) {
        this.character = character;

        // Basic Frame Setup
        setupFrame();

        // Create UI Panels
        JPanel leftPanel = createLeftPanel();
        JPanel rightPanel = createRightPanel(); // This will contain all details

        // Main Content Panel (Holds Left and Right Columns)
        JPanel mainContentPanel = createMainContentPanel(leftPanel, rightPanel);

        // Add Main Panel to Frame
        add(mainContentPanel, BorderLayout.CENTER);
    }

    // --- Private Helper Methods ---
    private void setupFrame() {
        setTitle("Character Details: " + character.getName());
        setSize(1280, 720); // Set size to 1280 x 720
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 0));
        getContentPane().setBackground(new Color(230, 235, 240));
    }

    private JPanel createMainContentPanel(JPanel leftPanel, JPanel rightPanel) {
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 10, 0)); // 1 row, 2 columns, gap
        mainPanel.setOpaque(false);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        return mainPanel;
    }

    private JPanel createLeftPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        ImageIcon icon = new ImageIcon(character.getImagePath());
        Image scaledImage = icon.getImage().getScaledInstance(400, -1, Image.SCALE_SMOOTH); // Adjusted size
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(imageLabel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createRightPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Vertical layout
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Padding

        // 1. Name Panel
        panel.add(createNamePanel());
        panel.add(Box.createRigidArea(new Dimension(0, 15))); // Spacer

        // 2. Attributes/Stats Panel
        panel.add(createAttributesPanel());
        panel.add(Box.createRigidArea(new Dimension(0, 15))); // Spacer

        // 3. Profile/Story Panel
        panel.add(createProfilePanel());

        // 4. Buttons Panel (push to bottom)
        panel.add(Box.createVerticalGlue()); // Pushes buttons down
        panel.add(createButtonPanel());

        return panel;
    }

    private JPanel createNamePanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setOpaque(false);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50)); // Prevent stretching vertically

        // Name Label
        JLabel nameLabel = new JLabel(character.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 26));
        panel.add(nameLabel, BorderLayout.WEST);

        return panel;
    }

    private JPanel createAttributesPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Vertical list of stats
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createTitledBorder("Attributes"));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align panel itself left

        // Add stats using helper method
        panel.add(createStatRow("Max HP:", String.valueOf(character.getCharacter().getMaxHp())));
        panel.add(Box.createRigidArea(new Dimension(0, 5))); // Spacer
        panel.add(createStatRow("Mana:", String.valueOf(character.getCharacter().getMana())));
        panel.add(Box.createRigidArea(new Dimension(0, 5))); // Spacer
        panel.add(createStatRow("Armor Class:", String.valueOf(character.getCharacter().getAC())));
        panel.add(createStatRow("Hit Bonus: +", String.valueOf(character.getCharacter().getHitBonus())));


        return panel;
    }

    private JPanel createStatRow(String labelText, String valueText) {
        JPanel rowPanel = new JPanel(new BorderLayout(10, 0));
        rowPanel.setOpaque(false);
        rowPanel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align row left
        rowPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30)); // Limit height

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        rowPanel.add(label, BorderLayout.WEST);

        JLabel value = new JLabel(valueText);
        value.setFont(new Font("Arial", Font.PLAIN, 16));
        rowPanel.add(value, BorderLayout.CENTER); // Or EAST

        return rowPanel;
    }

    private JScrollPane createProfilePanel() {
        JTextArea storyArea = new JTextArea(character.getCharacter().backstory);
        storyArea.setEditable(false);
        storyArea.setLineWrap(true);
        storyArea.setWrapStyleWord(true);
        storyArea.setFont(new Font("Arial", Font.PLAIN, 14));
        storyArea.setOpaque(false);
        storyArea.setFocusable(false);
        storyArea.setBackground(getContentPane().getBackground());
        storyArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JScrollPane scrollPane = new JScrollPane(storyArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Backstory"));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT); // Align scroll pane left
        scrollPane.setPreferredSize(new Dimension(400, 150)); // Adjust preferred height
        scrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200)); // Limit max height

        return scrollPane;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5)); // Align buttons right
        panel.setOpaque(false);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align panel itself left
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50)); // Limit height

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> new CharacterSelectScreen().setVisible(true));
            dispose();
        });
        panel.add(backButton);

        JButton selectButton = new JButton("Select Character");
        selectButton.setFont(new Font("Arial", Font.BOLD, 14));
        selectButton.setBackground(new Color(100, 200, 100));
        selectButton.setForeground(Color.WHITE);
        selectButton.addActionListener(e -> {
            String confirmationMessage = "Selected: " + character.getName() + "!";
            JOptionPane.showMessageDialog(
                    CharacterDetailsScreen.this, confirmationMessage,
                    "Character Confirmed", JOptionPane.INFORMATION_MESSAGE);
        });
        panel.add(selectButton);

        return panel;
    }
}
//package com.example.abyssalfate_fx.main;
//import com.example.abyssalfate_fx.Classes.CharactersSelection;
//import javax.swing.*;
//// Removed BevelBorder import as status bar is removed
//import java.awt.*;
//public class CharacterDetailsScreen extends JFrame {
//// No longer need statusBar member variable
//// private JLabel statusBar;
//
//    private CharactersSelection character;
//
//// /Constructor/
//
//    public CharacterDetailsScreen(CharactersSelection character) {
//
//        this.character = character;
//
//// Basic Frame Setup
//        setupFrame();
//
//
//// Create UI Panels
//
//        JPanel leftPanel = createLeftPanel();
//
//        JPanel rightPanel = createRightPanel(); // This will contain all details
//
//
//// Main Content Panel (Holds Left and Right Columns)
//
//        JPanel mainContentPanel = createMainContentPanel(leftPanel, rightPanel);
//
//
//// Add Main Panel to Frame
//
//        add(mainContentPanel, BorderLayout.CENTER);
//
//// Status bar is removed
//
//    }
//
//// --- Private Helper Methods ---
//
//
//    private void setupFrame() {
//
//        setTitle("Character Details: " + character.getName());
//
//// Adjusted size
//
//        setSize(900, 600); // Adjusted size
//
//        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//
//        setLocationRelativeTo(null);
//
//        setLayout(new BorderLayout(0, 0));
//
//        getContentPane().setBackground(new Color(230, 235, 240));
//
//    }
//
//
//    private JPanel createMainContentPanel(JPanel leftPanel, JPanel rightPanel) {
//
//        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 10, 0)); // 1 row, 2 columns, gap
//
//        mainPanel.setOpaque(false);
//
//        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
//
//        mainPanel.add(leftPanel);
//
//        mainPanel.add(rightPanel);
//
//        return mainPanel;
//
//    }
//
//
//
//    private JPanel createLeftPanel() {
//
//        JPanel panel = new JPanel(new BorderLayout());
//
//        panel.setOpaque(false);
//
//
//
//        ImageIcon icon = new ImageIcon(character.getImagePath());
//
//// Adjust image scaling as needed
//
//        Image scaledImage = icon.getImage().getScaledInstance(300, -1, Image.SCALE_SMOOTH);
//
//        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
//
//        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
//
//        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
//
//        panel.add(imageLabel, BorderLayout.CENTER);
//
//
//
//        return panel;
//
//    }
//
//
//
//    /**
//
//     * Creates the right panel containing vertically arranged details.
//
//     */
//
//    private JPanel createRightPanel() {
//
//// Use BoxLayout for vertical stacking of components
//
//        JPanel panel = new JPanel();
//
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Vertical layout
//
//        panel.setOpaque(false);
//
//        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Padding
//
//
//
//// 1. Name and Rarity Panel
//
//        panel.add(createNameRarityPanel());
//
//        panel.add(Box.createRigidArea(new Dimension(0, 15))); // Spacer
//
//
//
//// 2. Attributes/Stats Panel
//
//        panel.add(createAttributesPanel());
//
//        panel.add(Box.createRigidArea(new Dimension(0, 15))); // Spacer
//
//
//
//// 3. Profile/Story Panel
//
//        panel.add(createProfilePanel());
//
//
//
//// 4. Buttons Panel (push to bottom)
//
//        panel.add(Box.createVerticalGlue()); // Pushes buttons down
//
//        panel.add(createButtonPanel());
//
//
//        return panel;
//
//    }
//
//
//    /**
//
//     * Creates the panel with Character Name and Rarity Stars.
//
//     */
//
//    private JPanel createNameRarityPanel() {
//
//        JPanel panel = new JPanel(new BorderLayout(10, 0));
//
//        panel.setOpaque(false);
//
//        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50)); // Prevent stretching vertically
//
//
//
//// Name Label
//
//        JLabel nameLabel = new JLabel(character.getName());
//
//        nameLabel.setFont(new Font("Arial", Font.BOLD, 26));
//
//        panel.add(nameLabel, BorderLayout.WEST);
//
//
//
//        return panel;
//
//    }
//
//
//
//    /**
//
//     * Creates the panel displaying character attributes (stats).
//
//     */
//
//    private JPanel createAttributesPanel() {
//
//// Use GridBagLayout for more control over alignment if needed, or simpler layout
//
//        JPanel panel = new JPanel();
//
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Vertical list of stats
//
//        panel.setOpaque(false);
//
//        panel.setBorder(BorderFactory.createTitledBorder("Attributes"));
//
//        panel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align panel itself left
//
//
//
//// Add stats using helper method
//
//        panel.add(createStatRow("Max HP:", String.valueOf(character.getMaxHp())));
//
//        panel.add(Box.createRigidArea(new Dimension(0, 5))); // Spacer
//
//        panel.add(createStatRow("ATK:", String.valueOf(character.getAttack())));
//
//        panel.add(Box.createRigidArea(new Dimension(0, 5))); // Spacer
//
//        panel.add(createStatRow("DEF:", String.valueOf(character.getDefense())));
//
//// Add more stats here if needed
//
//        return panel;
//
//    }
//
//
//
//    private JPanel createStatRow(String labelText, String valueText) {
//
//        JPanel rowPanel = new JPanel(new BorderLayout(10, 0));
//
//        rowPanel.setOpaque(false);
//
//        rowPanel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align row left
//
//        rowPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30)); // Limit height
//
//
//
//        JLabel label = new JLabel(labelText);
//
//        label.setFont(new Font("Arial", Font.BOLD, 16));
//
//// Optional: Set fixed size for label alignment
//
//// label.setPreferredSize(new Dimension(100, 25));
//
//        rowPanel.add(label, BorderLayout.WEST);
//
//
//
//        JLabel value = new JLabel(valueText);
//
//        value.setFont(new Font("Arial", Font.PLAIN, 16));
//
//        rowPanel.add(value, BorderLayout.CENTER); // Or EAST
//
//
//
//        return rowPanel;
//
//    }
//
//
//
//
//
//    /**
//
//     * Creates the scrollable text area for the character profile/story.
//
//     */
//
//    private JScrollPane createProfilePanel() { // Renamed from createStoryPanel
//
//        JTextArea storyArea = new JTextArea(character.getStory());
//
//        storyArea.setEditable(false);
//
//        storyArea.setLineWrap(true);
//
//        storyArea.setWrapStyleWord(true);
//
//        storyArea.setFont(new Font("Arial", Font.PLAIN, 14));
//
//        storyArea.setOpaque(false);
//
//        storyArea.setFocusable(false);
//
//// Use background color of container for better blending if opaque=false isn't perfect
//
//        storyArea.setBackground(getContentPane().getBackground());
//
//// Padding inside the scrollpane border
//
//        storyArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
//
//
//
//        JScrollPane scrollPane = new JScrollPane(storyArea);
//
//        scrollPane.setBorder(BorderFactory.createTitledBorder("Profile"));
//
//        scrollPane.setOpaque(false);
//
//        scrollPane.getViewport().setOpaque(false);
//
//        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT); // Align scroll pane left
//
//
//
//// Set preferred/max size to control vertical space better with BoxLayout
//
//        scrollPane.setPreferredSize(new Dimension(400, 150)); // Adjust preferred height
//
//        scrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200)); // Limit max height
//
//
//
//        return scrollPane;
//
//    }
//
//
//    /**
//
//     * Creates the panel containing the action buttons (Back, Select).
//
//     */
//
//    private JPanel createButtonPanel() {
//
//        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5)); // Align buttons right
//
//        panel.setOpaque(false);
//
//        panel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align panel itself left
//
//        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50)); // Limit height
//
//
//
//        JButton backButton = new JButton("Back");
//
//        backButton.setFont(new Font("Arial", Font.BOLD, 14));
//
//        backButton.addActionListener(e -> {
//
//            SwingUtilities.invokeLater(() -> new CharacterSelectScreen().setVisible(true));
//
//            dispose();
//
//        });
//
//        panel.add(backButton);
//
//
//
//        JButton selectButton = new JButton("Select Character");
//
//        selectButton.setFont(new Font("Arial", Font.BOLD, 14));
//
//        selectButton.setBackground(new Color(100, 200, 100));
//
//        selectButton.setForeground(Color.WHITE);
//
//        selectButton.addActionListener(e -> {
//            String confirmationMessage = "Selected: " + character.getName() + "!";
//            JOptionPane.showMessageDialog(
//                    CharacterDetailsScreen.this, confirmationMessage,
//                    "Character Confirmed", JOptionPane.INFORMATION_MESSAGE);
//
//            System.out.println("Selected character: " + character.getName() + ".");
//
//            SwingUtilities.invokeLater(() -> new CharacterSelectScreen().setVisible(true));
//            dispose(); // Close the details screen
//        });
//
//
//        panel.add(selectButton);
//
//
//
//        return panel;
//
//    }
//
//
//
//// --- Main Method ---
//
//    public static void main(String[] args) {
//
//        try {
//
//// Ensure class name and constructor match your setup
//
//
//
//            try {
//
//                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//
//            } catch (Exception e) { System.err.println("Couldn't set system look and feel."); }
//
//
//
//
//
//        } catch (NoClassDefFoundError e) { System.err.println("Error: Could not find CharactersSelection class."); e.printStackTrace(); }
//
//        catch (Exception e) { System.err.println("An error occurred during testing."); e.printStackTrace(); }
//
//    }
//
//}
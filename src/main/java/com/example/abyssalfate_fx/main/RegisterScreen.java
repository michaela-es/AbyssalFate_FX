package com.example.abyssalfate_fx.main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;

public class RegisterScreen extends JFrame {

    private JTextField nameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton registerButton;
    private Font gameFont;

    public RegisterScreen() {
        setTitle("Register - Abyssal Fate");
        setSize(600, 500);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        gameFont = loadCustomFont("/fonts/titleFont.ttf", 30f);

        BackgroundPanel backgroundPanel = new BackgroundPanel("/game_background.gif");
        backgroundPanel.setLayout(new GridBagLayout());

        JPanel registerPanel = new JPanel();
        registerPanel.setBackground(new Color(0, 0, 0, 160));
        registerPanel.setBorder(new EmptyBorder(20, 30, 20, 30));
        registerPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 12, 10, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        GlowingLabel titleLabel = new GlowingLabel("Create Your Fate");
        titleLabel.setFont(gameFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        registerPanel.add(titleLabel, gbc);

        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setForeground(Color.LIGHT_GRAY);
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        registerPanel.add(nameLabel, gbc);

        nameField = new JTextField(15);
        nameField.setBorder(new LineBorder(Color.DARK_GRAY));
        gbc.gridx = 1;
        registerPanel.add(nameField, gbc);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.LIGHT_GRAY);
        gbc.gridy = 2;
        gbc.gridx = 0;
        registerPanel.add(usernameLabel, gbc);

        usernameField = new JTextField(15);
        usernameField.setBorder(new LineBorder(Color.DARK_GRAY));
        gbc.gridx = 1;
        registerPanel.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.LIGHT_GRAY);
        gbc.gridy = 3;
        gbc.gridx = 0;
        registerPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(15);
        passwordField.setBorder(new LineBorder(Color.DARK_GRAY));
        gbc.gridx = 1;
        registerPanel.add(passwordField, gbc);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setForeground(Color.LIGHT_GRAY);
        gbc.gridy = 4;
        gbc.gridx = 0;
        registerPanel.add(confirmPasswordLabel, gbc);

        confirmPasswordField = new JPasswordField(15);
        confirmPasswordField.setBorder(new LineBorder(Color.DARK_GRAY));
        gbc.gridx = 1;
        registerPanel.add(confirmPasswordField, gbc);

        registerButton = new JButton("Forge Destiny");
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.setBackground(new Color(0, 75, 90));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        registerButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                registerButton.setBackground(new Color(209, 67, 104, 255));
            }

            public void mouseExited(MouseEvent evt) {
                registerButton.setBackground(new Color(0, 75, 90));
            }
        });


        //nav to login
        JLabel label = new JLabel("Already have an account?");
        label.setForeground(Color.LIGHT_GRAY);

        JButton goToLoginBtn = new JButton("Login here");
        goToLoginBtn.setBorderPainted(false);
        goToLoginBtn.setFocusPainted(false);
        goToLoginBtn.setContentAreaFilled(false);
        goToLoginBtn.setForeground(Color.CYAN);
        goToLoginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        goToLoginBtn.setFont(new Font("Arial", Font.PLAIN, 12));

        goToLoginBtn.addActionListener(e -> {
            playSound("/sounds/click.wav");
            this.dispose();
            new LoginScreen();
        });

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setOpaque(false);
        bottomPanel.add(label);
        bottomPanel.add(goToLoginBtn);

        gbc.gridy = 6;
        registerPanel.add(bottomPanel, gbc);


        registerButton.addActionListener(e -> {
            playSound("/sounds/click.wav");
            handleRegistration();
        });

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        registerPanel.add(registerButton, gbc);

        backgroundPanel.add(registerPanel);
        setContentPane(backgroundPanel);
        setVisible(true);
    }

    //val
    private void handleRegistration() {
        String name = nameField.getText().trim();
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirm = new String(confirmPasswordField.getPassword());

        if(name.isEmpty() || username.isEmpty() || password.isEmpty() || confirm.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        if (!password.equals(confirm)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.");
            return;
        }

        boolean success = UserManager.registerUser(username, password);
        if (success) {
            JOptionPane.showMessageDialog(this, "Account created! Proceed to login.");
            this.dispose();
            new LoginScreen();
        } else {
            JOptionPane.showMessageDialog(this, "Username already taken.");
        }
    }


    private Font loadCustomFont(String path, float size) {
        try {
            InputStream is = getClass().getResourceAsStream(path);
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            return font.deriveFont(size);
        } catch (Exception e) {
            e.printStackTrace();
            return new Font("Serif", Font.BOLD, (int) size);
        }
    }

    private void playSound(String path) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource(path));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new RegisterScreen();
    }
}

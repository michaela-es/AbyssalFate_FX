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

public class LoginScreen extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private Font gameFont;

    public LoginScreen() {
        setTitle("Abyssal Fate");
        setSize(600, 450);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        gameFont = loadCustomFont("/fonts/titleFont.ttf", 32f);

        BackgroundPanel backgroundPanel = new BackgroundPanel("/game_background.gif");
        backgroundPanel.setLayout(new GridBagLayout());

        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(new Color(0, 0, 0, 160));
        loginPanel.setBorder(new EmptyBorder(20, 30, 20, 30));
        loginPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        GlowingLabel titleLabel = new GlowingLabel("Abyssal Fate");
        titleLabel.setFont(gameFont);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        loginPanel.add(titleLabel, gbc);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.LIGHT_GRAY);
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        loginPanel.add(usernameLabel, gbc);

        usernameField = new JTextField(15);
        usernameField.setBorder(new LineBorder(Color.DARK_GRAY));
        gbc.gridx = 1;
        loginPanel.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.LIGHT_GRAY);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(15);
        passwordField.setBorder(new LineBorder(Color.DARK_GRAY));
        gbc.gridx = 1;
        loginPanel.add(passwordField, gbc);

        //login
        loginButton = new JButton("Enter the Abyss");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(new Color(0, 75, 90));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        //hover
        loginButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                loginButton.setBackground(new Color(0, 105, 130));
            }

            public void mouseExited(MouseEvent evt) {
                loginButton.setBackground(new Color(0, 75, 90));
            }
        });

        //back sa register
        JLabel label = new JLabel("Don't have an account?");
        label.setForeground(Color.LIGHT_GRAY);

        JButton goToRegisterBtn = new JButton("Register here");
        goToRegisterBtn.setBorderPainted(false);
        goToRegisterBtn.setFocusPainted(false);
        goToRegisterBtn.setContentAreaFilled(false);
        goToRegisterBtn.setForeground(Color.CYAN);
        goToRegisterBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        goToRegisterBtn.setFont(new Font("Arial", Font.PLAIN, 12));

        goToRegisterBtn.addActionListener(e -> {
            playSound("/sounds/click.wav");
            this.dispose();
            new RegisterScreen();
        });

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setOpaque(false);
        bottomPanel.add(label);
        bottomPanel.add(goToRegisterBtn);

        gbc.gridy = 6;
        loginPanel.add(bottomPanel, gbc);

        loginButton.addActionListener(e -> {
            playSound("/sounds/click.wav");
            validateLogin();
        });

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        loginPanel.add(loginButton, gbc);

        backgroundPanel.add(loginPanel);
        setContentPane(backgroundPanel);
        setVisible(true);
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

    //validate
    public void validateLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if(username.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        if (UserManager.validateLogin(username, password)) {
            this.dispose();
            new HomeScreen();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Credentials. Try Again.");
        }
    }

}

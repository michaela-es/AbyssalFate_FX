package com.example.abyssalfate_fx.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class LoadingScreen extends JFrame {

    private Font gameFont;
    private MediaPlayer mediaPlayer;

    public LoadingScreen() {
        // Initialize JavaFX runtime (required for Media)
        new JFXPanel(); // Initializes JavaFX environment

        setTitle("Abyssal Fate - Loading...");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        gameFont = loadCustomFont("/fonts/titleFont.ttf", 80f);

        BackgroundPanel background = new BackgroundPanel("/abyssal fate.gif");
        background.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Abyssal Fate");
        titleLabel.setFont(gameFont);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        background.add(titleLabel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 120, 50));

        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setPreferredSize(new Dimension(1000, 10));
        progressBar.setStringPainted(true);
        progressBar.setBackground(new Color(50, 50, 50));
        progressBar.setForeground(new Color(255, 102, 0));
        progressBar.setUI(new javax.swing.plaf.basic.BasicProgressBarUI() {
            @Override
            protected void paintDeterminate(Graphics g, JComponent c) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int width = progressBar.getWidth();
                int height = progressBar.getHeight();
                Insets b = progressBar.getInsets();
                int barRectWidth = width - (b.right + b.left);
                int barRectHeight = height - (b.top + b.bottom);
                if (barRectWidth <= 0 || barRectHeight <= 0) return;
                int amountFull = getAmountFull(b, barRectWidth, barRectHeight);

                g2d.setColor(progressBar.getBackground());
                g2d.fillRoundRect(b.left, b.top, barRectWidth, barRectHeight, barRectHeight, barRectHeight);
                g2d.setColor(progressBar.getForeground());
                g2d.fillRoundRect(b.left, b.top, amountFull, barRectHeight, barRectHeight, barRectHeight);

                if (progressBar.isStringPainted()) {
                    paintString(g, b.left, b.top, barRectWidth, barRectHeight, amountFull, b);
                }
            }
        });

        JPanel progressWrapper = new JPanel(new GridBagLayout());
        progressWrapper.setOpaque(false);
        progressWrapper.add(progressBar);
        bottomPanel.add(progressWrapper, BorderLayout.CENTER);

        JLabel progressLabel = new JLabel("Entering the abyss... 0%", SwingConstants.CENTER);
        if (gameFont != null) {
            progressLabel.setFont(gameFont.deriveFont(18f));
        } else {
            progressLabel.setFont(new Font("Arial", Font.BOLD, 16));
        }
        progressLabel.setForeground(Color.WHITE);
        progressLabel.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));
        bottomPanel.add(progressLabel, BorderLayout.SOUTH);

        background.add(bottomPanel, BorderLayout.SOUTH);
        setContentPane(background);
        setVisible(true);

        // Start background music
        playBackgroundMusic("/music/Abyss.mp3");

        Timer timer = new Timer(100, new ActionListener() {
            int counter = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (counter < 100) {
                    counter++;
                    progressBar.setValue(counter);
                    progressLabel.setText("Entering the abyss... " + counter + "%");
                } else {
                    ((Timer) e.getSource()).stop();
                    dispose(); // Close the loading screen
                    if (mediaPlayer != null) {
                        mediaPlayer.stop(); // Stop music when done
                    }

                    // Launch JavaFX HomeScreen
                    Platform.runLater(() -> {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/home.fxml"));
                            Parent root = loader.load();
                            Stage stage = new Stage();
                            stage.setTitle("Abyssal Fate - Home");
                            stage.setScene(new Scene(root));
                            stage.show();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error loading HomeScreen.fxml", "FXML Error", JOptionPane.ERROR_MESSAGE);
                        }
                    });
                }
            }
        });
        timer.start();
    }

    private Font loadCustomFont(String path, float size) {
        try {
            InputStream is = getClass().getResourceAsStream(path);
            if (is == null) throw new IOException("Cannot load font resource: " + path);
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            is.close();
            return font.deriveFont(size);
        } catch (Exception e) {
            e.printStackTrace();
            return new Font("Serif", Font.BOLD, (int) size);
        }
    }

    private void playBackgroundMusic(String path) {
        Platform.runLater(() -> {
            try {
                URL resource = getClass().getResource(path);
                if (resource == null) {
                    System.err.println("Audio file not found: " + path);
                    return;
                }
                Media media = new Media(resource.toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop
                mediaPlayer.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        // Launch JavaFX Toolkit
        Platform.startup(() -> {});
        SwingUtilities.invokeLater(() -> new LoadingScreen());
    }
}

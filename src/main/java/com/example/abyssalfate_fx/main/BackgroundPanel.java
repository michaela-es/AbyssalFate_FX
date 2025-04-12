package com.example.abyssalfate_fx.main;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class BackgroundPanel extends JPanel {

    private ImageIcon backgroundIcon;

    public BackgroundPanel(String imagePath) {
        // Load the image icon
        URL imageURL = getClass().getResource(imagePath);
        if (imageURL != null) {
            backgroundIcon = new ImageIcon(imageURL);
        } else {
            System.err.println("Couldn't find file: " + imagePath);
            backgroundIcon = null; // Handle error appropriately
        }
        // Important: Set opaque to false if you want parent background to show through
        // If this IS the main background, setOpaque(true) is usually fine.
        // setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Paint the default background (important if opaque)

        if (backgroundIcon != null) {
            // Draw the image, stretching it to fill the panel bounds.
            // Passing 'this' as the ImageObserver is crucial for animation.
            // The ImageIcon will notify 'this' panel when a new frame needs drawing.
            g.drawImage(backgroundIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
        } else {
            // Optionally draw a fallback background if the image failed to load
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.RED);
            g.drawString("Background image not found!", 10, 20);
        }
    }

    // Optional: Override getPreferredSize if needed, though layout managers often handle this.
    // @Override
    // public Dimension getPreferredSize() {
    //     if (backgroundIcon != null) {
    //         return new Dimension(backgroundIcon.getIconWidth(), backgroundIcon.getIconHeight());
    //     } else {
    //         return new Dimension(400, 300); // Default size
    //     }
    // }
}
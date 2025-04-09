package com.example.abyssalfate_fx.main;

import javax.swing.*;
import java.awt.*;

public class GlowingLabel extends JLabel {

    public GlowingLabel(String text) {
        super(text);
        setForeground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setFont(getFont());
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int x = 0;
        int y = getHeight() - 8;

        // Shadow
        g2.setColor(new Color(0, 0, 0, 180));
        g2.drawString(getText(), x + 2, y + 2);

        // Glow
        g2.setColor(new Color(0, 255, 255, 100));
        g2.drawString(getText(), x + 1, y + 1);

        // Text
        g2.setColor(getForeground());
        g2.drawString(getText(), x, y);
        g2.dispose();
    }
}

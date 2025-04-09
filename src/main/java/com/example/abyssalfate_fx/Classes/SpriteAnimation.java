package com.example.abyssalfate_fx.Classes;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class SpriteAnimation extends JPanel {
    private BufferedImage spriteSheet;
    private int frameWidth;
    private int frameHeight;
    private int currentFrame = 0;

    // These will hold the frame ranges for different skills, animations, and hurt/take hit
    private int[] idleFrames;
    private int[] skill1Frames;
    private int[] skill2Frames;
    private int[] skill3Frames;
    private int[] hurtFrames;
    private int[] deathFrames;

    private String currentAnimation = "idle";  // Default animation

    // Constructor accepts paths for different animations (idle, skills, hurt, death)
    public SpriteAnimation(String path, int frameWidth, int frameHeight,
                           int[] idleFrames, int[] skill1Frames,
                           int[] skill2Frames, int[] skill3Frames,
                           int[] hurtFrames, int[] deathFrames) {
        try {
            spriteSheet = ImageIO.read(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.idleFrames = idleFrames;
        this.skill1Frames = skill1Frames;
        this.skill2Frames = skill2Frames;
        this.skill3Frames = skill3Frames;
        this.hurtFrames = hurtFrames;
        this.deathFrames = deathFrames;

        Timer timer = new Timer(100, e -> {
            currentFrame = (currentFrame + 1) % getCurrentAnimationFrameCount();
            repaint();
        });
        timer.start();
    }

    // Update the current animation type (idle, skill1, skill2, skill3, hurt, death)
    public void setCurrentAnimation(String animation) {
        currentAnimation = animation;
    }

    // Get the current frame count for the selected animation
    private int getCurrentAnimationFrameCount() {
        switch (currentAnimation) {
            case "idle": return idleFrames.length;
            case "skill1": return skill1Frames.length;
            case "skill2": return skill2Frames.length;
            case "skill3": return skill3Frames.length;
            case "hurt": return hurtFrames.length;
            case "death": return deathFrames.length;
            default: return 0;
        }
    }

    // Get the current frame number based on the animation
    private int getCurrentAnimationFrame() {
        switch (currentAnimation) {
            case "idle": return idleFrames[currentFrame];
            case "skill1": return skill1Frames[currentFrame];
            case "skill2": return skill2Frames[currentFrame];
            case "skill3": return skill3Frames[currentFrame];
            case "hurt": return hurtFrames[currentFrame];
            case "death": return deathFrames[currentFrame];
            default: return 0;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = getCurrentAnimationFrame() * frameWidth;  // Use the frame for the current animation
        BufferedImage frame = spriteSheet.getSubimage(x, 0, frameWidth, frameHeight);

        // Adjust the scale factor (6 times the original size, you can modify this value)
        int scaleFactor = 6;
        int scaleWidth = frameWidth * scaleFactor;
        int scaleHeight = frameHeight * scaleFactor;

        // Drawing the scaled image
        g.drawImage(frame, 50, 50, scaleWidth, scaleHeight, null);
    }

    public static void main(String[] args) {
        // Define frame sequences for Paladin
        int[] idleFramesPaladin = {0, 1, 2, 3, 4, 5, 6};
        int[] skill1FramesPaladin = {7, 8, 9, 10, 11, 12};
        int[] skill2FramesPaladin = {13, 14, 15, 16, 17};
        int[] skill3FramesPaladin = {18, 19, 20, 21, 22, 23};
        int[] hurtFramesPaladin = {24, 25, 26, 27};  // Hurt/take hit frames
        int[] deathFramesPaladin = {28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39}; // Death frames

        // Define frame sequences for Wizard
        int[] idleFramesWizard = {0, 1, 2, 3};
        int[] skill1FramesWizard = {4, 5, 6, 7};
        int[] skill2FramesWizard = {8, 9, 10, 11};
        int[] skill3FramesWizard = {12, 13, 14, 15};
        int[] hurtFramesWizard = {16, 17, 18};  // Hurt/take hit frames
        int[] deathFramesWizard = {19, 20, 21}; // Death frames

        // Define frame sequences for Warlock
        int[] idleFramesWarlock = {0, 1, 2, 3};
        int[] skill1FramesWarlock = {4, 5, 6, 7};
        int[] skill2FramesWarlock = {8, 9, 10, 11};
        int[] skill3FramesWarlock = {12, 13, 14, 15};
        int[] hurtFramesWarlock = {16, 17, 18};  // Hurt/take hit frames
        int[] deathFramesWarlock = {19, 20, 21}; // Death frames

        // Define frame sequences for Sorcerer
        int[] idleFramesSorcerer = {0, 1, 2, 3};
        int[] skill1FramesSorcerer = {4, 5, 6, 7};
        int[] skill2FramesSorcerer = {8, 9, 10, 11};
        int[] skill3FramesSorcerer = {12, 13, 14, 15};
        int[] hurtFramesSorcerer = {16, 17, 18};  // Hurt/take hit frames
        int[] deathFramesSorcerer = {19, 20, 21}; // Death frames

        // Create JFrame to show animations
        JFrame frame = new JFrame("Sprite Animation");

        // Create an animation for the Paladin character
        SpriteAnimation animPaladin = new SpriteAnimation(
                "res/char1_paladin/IDLE.png", 96, 84,
                idleFramesPaladin, skill1FramesPaladin,
                skill2FramesPaladin, skill3FramesPaladin,
                hurtFramesPaladin, deathFramesPaladin
        );

        // Create an animation for the Wizard character
        SpriteAnimation animWizard = new SpriteAnimation(
                "res/wizard/idle.png", 96, 84,
                idleFramesWizard, skill1FramesWizard,
                skill2FramesWizard, skill3FramesWizard,
                hurtFramesWizard, deathFramesWizard
        );

        // Create an animation for the Warlock character
        SpriteAnimation animWarlock = new SpriteAnimation(
                "res/warlock/idle.png", 96, 84,
                idleFramesWarlock, skill1FramesWarlock,
                skill2FramesWarlock, skill3FramesWarlock,
                hurtFramesWarlock, deathFramesWarlock
        );

        // Create an animation for the Sorcerer character
        SpriteAnimation animSorcerer = new SpriteAnimation(
                "res/sorcerer/idle.png", 96, 84,
                idleFramesSorcerer, skill1FramesSorcerer,
                skill2FramesSorcerer, skill3FramesSorcerer,
                hurtFramesSorcerer, deathFramesSorcerer
        );

        // Set the JFrame to maximize, but show the title bar and taskbar
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.add(animPaladin);  // You can change this to animWizard, animWarlock, or animSorcerer
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Example usage of switching between skills
        animPaladin.setCurrentAnimation("idle");  // Start with idle for Paladin
        // Call `animPaladin.setCurrentAnimation("skill1");` to switch to skill1 for Paladin
        // Call `animPaladin.setCurrentAnimation("hurt");` to play the hurt animation for Paladin
        // Call `animPaladin.setCurrentAnimation("death");` to play the death animation for Paladin
    }
}

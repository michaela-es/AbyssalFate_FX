// Assuming this file is in the 'Classes' package based on your import
package com.example.abyssalfate_fx.Classes; // Or 'main' if it's in the main package

public class CharactersSelection {
    private String name;
    private String imagePath;
    private boolean unlocked;
    private int power;
    private int defense;
    private int speed;
    private String story; // <-- ADD THIS FIELD

    // MODIFY THE CONSTRUCTOR
    public CharactersSelection(String name, String imagePath, boolean unlocked, int power, int defense, int speed, String story) {
        this.name = name;
        this.imagePath = imagePath;
        this.unlocked = unlocked;
        this.power = power;
        this.defense = defense;
        this.speed = speed;
        this.story = story; // <-- ASSIGN THE STORY
    }

    // /Getters
    public String getName() { return name; }
    public String getImagePath() { return imagePath; }
    public boolean isUnlocked() { return unlocked; }
    public int getPower() { return power; }
    public int getDefense() { return defense; }
    public int getSpeed() { return speed; }
    public String getStory() { return story; } // <-- ADD GETTER FOR STORY
}
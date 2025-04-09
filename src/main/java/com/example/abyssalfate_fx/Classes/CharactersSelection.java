package com.example.abyssalfate_fx.Classes;

public class CharactersSelection {
    private CharacterClass character; // Hold a reference to a CharacterClass instance
    private String story;

    // MODIFY THE CONSTRUCTOR
    public CharactersSelection(CharacterClass character, String story) {
        this.character = character;
        this.story = story;
    }

    // Getters
    public String getName() {
        return character.getClass().getSimpleName(); // Get the simple class name
    }

    public String getImagePath() {
        // You can return a default image path or implement a method in CharacterClass to get an image path
        return "res/default.png"; // Placeholder
    }

    public int getMaxHp() {
        return character.getMaxHp(); // Get max HP from CharacterClass
    }

    public int getAttack() {
        return character.getHitBonus(); // Assuming hit bonus is used as attack
    }

    public int getDefense() {
        return character.getAC(); // Get Armor Class as defense
    }

    public String getStory() {
        return story; // Return the story
    }

    public CharacterClass getCharacter() {
        return character; // Return the CharacterClass instance
    }
}
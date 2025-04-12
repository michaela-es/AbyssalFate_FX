package com.example.abyssalfate_fx.Classes;



public class CharactersSelection {
    private String name;
    private String imagePath;
    private boolean unlocked;
    private int rarity;
    private int maxHp;
    private int attack;
    private int defense;
    private String story;

    public CharactersSelection(String name, String imagePath, boolean unlocked, int rarity,
                               int maxHp, int attack, int defense, String story) {
        this.name = name;
        this.imagePath = imagePath;
        this.unlocked = unlocked;
        this.rarity = rarity;
        this.maxHp = maxHp;
        this.attack = attack;
        this.defense = defense;
        this.story = story;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public String getStory() {
        return story;
    }
}
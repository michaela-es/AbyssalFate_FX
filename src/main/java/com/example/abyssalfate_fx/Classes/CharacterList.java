package com.example.abyssalfate_fx.Classes;

import java.util.ArrayList;
import java.util.Random;

public class CharacterList {

    ArrayList<CharacterClass> characters = new ArrayList<>();
    Random random = new Random();
    public CharacterList() {
        characters.add(new Fighter());
        characters.add(new Paladin());
        characters.add(new Ranger());
        characters.add(new Rogue());
        characters.add(new Warlock());
        characters.add(new Wizard());
        characters.add(new Sorcerer());
        characters.add(new Barbarian());

    }

    public CharacterClass getCharacter(int index) {
        if (index >= 0 && index < characters.size()) {
            return characters.get(index);
        } else {
            System.out.println("Index out of bounds.");
            return null;
        }
    }

    public int getCharacterCount() {
        return characters.size();
    }

    public CharacterClass getRandomChar(){
        int index = random.nextInt(characters.size());
        return characters.get(index);
    }
}

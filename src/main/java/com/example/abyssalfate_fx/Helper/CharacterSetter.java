package com.example.abyssalfate_fx.Helper;

import com.example.abyssalfate_fx.Classes.*;

public class CharacterSetter {
    private static CharacterSetter instance;
    CharacterList charSelect = new CharacterList();
    public CharacterClass player;
    public CharacterClass enemy;
    public  CharacterClass p1;
    public  CharacterClass p2;

    public static CharacterSetter getInstance() {
        if (instance == null) {
            instance = new CharacterSetter();
        }
        return instance;
    }

    public void setPlayer(CharacterClass character) {
        this.player = character;
        this.enemy = charSelect.getRandomChar();
    }

    public CharacterClass getPlayerPVE(){
        return player;
    }

    public CharacterClass getEnemy(){
        return enemy;
    }


    public CharacterClass getPlayer1(){
        return p1;
    }
    public CharacterClass getPlayer2(){
        return p2;
    }
}

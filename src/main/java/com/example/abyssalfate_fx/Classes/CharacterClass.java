package com.example.abyssalfate_fx.Classes;

import java.util.Random;
public abstract class CharacterClass {

    // Stats
    private int hp, maxHp;
    private int mana, maxMana;
    private int armorClass, baseAC;
    private int savingDC;
    private int hitBonus;

    public int costSkill2;
    public int costSkill3;

    public String backstory = "Backstory TBA";

    private String skillName;

    public String nameSkill1;
    public String nameSkill2;
    public String nameSkill3;

    public String descSkill1;
    public String descSkill2;
    public String descSkill3;

    boolean hasAdvantage = false;
    Random roller = new Random();
    CharacterClass enemy;

    public int damage = 0;

    public CharacterClass(int maxHp, int maxMana, int armorClass, int savingDC, int hitBonus){

        this.hp = maxHp;
        this.maxHp = maxHp;

        this.mana = maxMana;
        this.maxMana = maxMana;

        this.armorClass = armorClass;
        this.baseAC = armorClass;
        this.savingDC = savingDC;
        this.hitBonus = hitBonus;
    }

    // concrete methods
    public boolean stillAlive(){
        return hp > 0;
    }

    public int rollToHit(){
        if (hasAdvantage){
            return rollWithAdvantage();
        } else
            return roller.nextInt(20)+1;
    }

    public boolean savingThrow(int enemyDC){
        return roller.nextInt(20)+1 >= enemyDC;
    }

    public int rollWithAdvantage(){
        useAdvantage();
        return Math.max(rollToHit(), rollToHit());
    }

    public int rollDamage(int diceNum, int diceSize) {
        int totalDamage = 0;
        for (int i = 0; i < diceNum; i++)
            totalDamage += roller.nextInt(diceSize) + 1;
        return totalDamage;
    }

    public void gainAdvantage(){
        hasAdvantage = true;
    }

    public void useAdvantage(){
        hasAdvantage = false;
    }

    public void loseHP(int damage){
        hp = Math.max(0, hp-=damage);
    }


    public void loseMana(int damage){
        mana = Math.max(0, mana - damage);
    }

    public void gainMana(int manaGain){
        mana = Math.min(maxMana, mana + manaGain);
    }
    // Getters
    public int getHp(){
        return hp;
    }

    public int getMana(){
        return mana;
    }

    public int getAC(){
        return armorClass;
    }

    public int getMaxHp(){
        return maxHp;
    }

    public int getMaxMana(){
        return maxMana;
    }

    public int getSavingDC(){ return savingDC; }
    // Setters

    public void increaseAC (int ac){
        armorClass+=ac;
    }

    public void clearAC(){
        this.armorClass = baseAC;
    }

    public void setEnemy(CharacterClass enemy){
        this.enemy = enemy;
    }

    // abstract methods
    abstract public int skill1();
    abstract public int skill2();
    abstract public int skill3();

    public String getSkillName(){
        return skillName;
    }

    public int getHitBonus(){
        return hitBonus;
    }

    public void setSkillName(int num){

        switch (num){
            case 1:
                skillName = nameSkill1;
                break;
            case 2:
                skillName = nameSkill2;
                break;
            case 3:
                skillName = nameSkill3;
                break;
        }
    }

    public int chooseSkill(){

        int choice = roller.nextInt(2);

        setSkillName(1);

        switch (choice){
            case 0:
                setSkillName(2);
                if (getMana()>=costSkill2)
                    damage = skill2();
                else
                    damage = skill1();
                break;
            case 1:
                setSkillName(3);
                if (getMana()>=costSkill3)
                    damage = skill3();
                else
                    damage = skill1();
                break;
        }

        return damage;
    }
}

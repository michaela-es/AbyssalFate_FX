package com.example.abyssalfate_fx.Classes;

public class Sorcerer extends CharacterClass {

    public Sorcerer() {
        super(70, 120, 13, 16, 9);

        costSkill2 = 40;
        costSkill3 = 80;

        nameSkill1 = "Draconic Origin";
        nameSkill2 = "Dragon's Rage";
        nameSkill3= "Overchannel";


        backstory = "The Sorcerer's draconic heritage made them naturally attuned to magic. But hunters and pillagers made them flee their homeland." +
                "They wander the world under a human glamour, yet upon feeling their hands, you will graze hardened scales.";
        descSkill1 = "Deals 1d4 damage. Restores 20 - 25 mana.";
        descSkill2 = "Deals 10d6 damage. Opponent saves against 15 to take half damage. " + costSkill2 + " mana cost.";
        descSkill3 = "Deals 12d8 damage. Opponent can save against 16 to take half damage. " + costSkill3 + " mana cost.";

    }

    @Override
    public int skill1() {
        gainMana(roller.nextInt(5)+20);

        setSkillName(1);
        return rollDamage(1,4);
    }

    @Override
    public int skill2() {
        damage = rollDamage(10, 6);

        damage = finalDamage(damage);

        loseMana(costSkill2);
        setSkillName(2);
        return damage;
    }

    public int finalDamage(int damage){

        if (!enemy.savingThrow(getSavingDC()) ){
            damage = damage;
        } else {
            damage = (int) damage/2;
        }

        return damage;

    }

    @Override
    public int skill3() {
        damage = rollDamage(12, 8);

        damage = finalDamage(damage);

        loseMana(costSkill3);
        setSkillName(3);
        return damage;
    }


}
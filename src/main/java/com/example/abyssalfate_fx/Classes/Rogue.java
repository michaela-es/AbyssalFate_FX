package com.example.abyssalfate_fx.Classes;

public class Rogue extends CharacterClass {

    boolean isHidden = false;

    public Rogue() {
        super(80, 75, 15, 14, 7);

        costSkill2 = 15;
        costSkill3 = 50;

        nameSkill1 = "Graze";
        nameSkill2 = "Sneak Attack";
        nameSkill3= "Killing Blow";

        descSkill1 = "Deals 1d6 damage. Gains “Hidden” state. Restores 10 - 15 mana.";
        descSkill2 = "Attacks opponent for 2d6 damage. Additional 4d6 damage if hidden." + costSkill2 + " mana cost.";
        descSkill3 = "Deals 6d6 damage. If opponent is below 40% health and in “Hidden” state, deals 10d6 damage instead. " + costSkill3 +" mana cost";

    }

    @Override
    public int skill1() {

        isHidden = true;
        gainMana(roller.nextInt(5)+11);

        setSkillName(1);
        return rollDamage(1,6);
    }

    @Override
    public int skill2() {
        damage = rollDamage(2, 6);

        if (isHidden) {
            damage += rollDamage(4, 6);
            isHidden = false;
        }

        loseMana(costSkill2);
        setSkillName(2);
        return damage;
    }

    @Override
    public int skill3() {
        if (isHidden && enemy.getHp() < enemy.getMaxHp() * .40) {
            damage += rollDamage(10, 6);
            isHidden = false;
        } else {
            damage = rollDamage(6, 6);
        }

        setSkillName(3);
        loseMana(costSkill3);
        return damage;
    }
}
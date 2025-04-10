package com.example.abyssalfate_fx.Classes;

public class Rogue extends CharacterClass {

    boolean isHidden = false;

    public Rogue() {
        super(80, 75, 15, 14, 7);

        costSkill2 = 15;
        costSkill3 = 40;

        nameSkill1 = "Graze";
        nameSkill2 = "Sneak Attack";
        nameSkill3= "Killing Blow";

        descSkill1 = "Deals 1d6 damage. Gains 'Hidden' state. Restores 10 - 15 mana.";
        descSkill2 = "Attacks opponent for 4d6 damage. Additional 4d6 damage if in 'Hidden' state." + costSkill2 + " mana cost.";
        descSkill3 = "Deals 6d6 damage. If opponent is below 40% health and in “Hidden” state, deals 12d6 damage instead. " + costSkill3 +" mana cost";

        backstory = "Born in the slums of Duskwatch, Kaevan never knew nobility, only survival. Orphaned as a child, he was taken in by the Crimson Veil, an elite guild of assassins that operated in the shadows of Elarion. The guild did not serve coins alone; they were the hidden dagger of the Elarion, eliminating threats to the kingdom long before they reached the throne. Under the ruthless training of Lady Nyx, Kaevan became a master of silent death, excelling in stealth, deception, and the art of the blade. \n";
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
        damage = rollDamage(4, 6);

        if (isHidden) {
            damage += rollDamage(4, 6);
            isHidden = false;
        }

        setSkillName(2);
        return damage;
    }

    @Override
    public int skill3() {
        if (isHidden && enemy.getHp() < enemy.getMaxHp() * .40) {
            damage += rollDamage(12, 6);
            isHidden = false;
        } else {
            damage = rollDamage(6, 6);
        }

        setSkillName(3);
        return damage;
    }
}
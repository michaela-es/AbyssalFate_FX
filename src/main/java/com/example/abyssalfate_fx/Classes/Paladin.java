package com.example.abyssalfate_fx.Classes;

public class Paladin extends CharacterClass {

    public Paladin() {
        super(100, 60, 18, 14, 6);

        costSkill2 = 12;
        costSkill3 = 30;

        nameSkill1 = "Cleave";
        nameSkill2 = "Heroic Form";
        nameSkill3= "Divine Smite";

        descSkill1 = "Deals 1d8 damage. Restores 10 mana.";
        descSkill2 = "Deals 2d10 damage to opponent. Grants advantage. " + costSkill2 + " mana cost.";
        descSkill3 = "Deals 5d8 damage." + costSkill3 +" mana cost";

        backstory = "A devout servant of the righteous path, the Paladin strikes down those who aim to harm the innocent. But beneath their armor and bravado lies a shivering child, whose salvation came as a hero dressed much like they are now. Armed with a gleaming golden axe, they mow down their foes and radiate hope.";

    }

    @Override
    public int skill1() {

        gainMana(10);

        setSkillName(1);
        return rollDamage(1,8);
    }

    @Override
    public int skill2() {
        damage = rollDamage(2, 10);
        gainAdvantage();

        setSkillName(2);
        return damage;
    }

    @Override
    public int skill3() {
        damage = rollDamage(5, 8);

        setSkillName(3);
        return damage;
    }
}
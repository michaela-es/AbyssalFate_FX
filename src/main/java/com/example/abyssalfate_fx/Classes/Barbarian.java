package com.example.abyssalfate_fx.Classes;

public class Barbarian extends CharacterClass {

    boolean rage = false;
    public Barbarian() {
        super(150, 60, 13, 14, 5);

        costSkill2 = 20;
        costSkill3 = 30;

        nameSkill1 = "Rage";
        nameSkill2 = "Reckless Attack";
        nameSkill3 = "BEHEMOTH UNBOUND";

        descSkill1 = "Deals 1d12 damage and enters 'Rage'. Restores 14 mana.";
        descSkill2 = "Deals 7d6 damage to opponent. Grants advantage if raging. " + costSkill2 + " mana cost.";
        descSkill3 = "Deals 5d12 damage. Additional 3d8 damage if raging" + costSkill3 + " mana cost";

        backstory = "As a champion of rage, the Barbarian fights with reckless abandon. Some view their temperament as a curse, but this unyielding fervor has helped them against the tyrannical enemies of their homeland. With the pungent smell of ale and heavy footsteps, this lumbering warrior is guaranteed to turn a few heads.";
    }

    public void rage(){
        rage = !rage;
    }

    @Override
    public int skill1() {

        gainMana(14);
        rage();


        setSkillName(1);
        return rollDamage(1,12);
    }

    @Override
    public int skill2() {
        damage = rollDamage(7, 6);
        if(rage) {
            gainAdvantage();
            rage();
        }
        loseMana(costSkill2);
        setSkillName(2);
        return damage;
    }

    @Override
    public int skill3() {
        damage = rollDamage(5, 12);
        if(rage) {
            damage += rollDamage(3,8);
            rage();
        }
        setSkillName(3);
        loseMana(costSkill3);
        return damage;
    }

}
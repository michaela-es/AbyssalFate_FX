package com.example.abyssalfate_fx.Classes;

public class Fighter extends CharacterClass {

    public Fighter() {
        super(100, 80, 18, 14, 4);

        costSkill2 = 20;
        costSkill3 = 25;

        nameSkill1 = "Action Surge";
        nameSkill2 = "Battle Maneuver";
        nameSkill3= "Defensive Flourish";

        descSkill1 = "Deals 2d8 damage. Restores 15 mana.";
        descSkill2 = "Deals 4d8 damage to opponent. Grants advantage. " + costSkill2 + " mana cost.";
        descSkill3 = "Deals 2d12 damage. Gains +4 AC. " + costSkill3 +" mana cost";

        backstory = "Nothing stirs the fighter more than the thrill of the battle. As for why they are here, perhaps it is the strange, whispering crimson sword in their hand's doing.";
    }

    @Override
    public int skill1() {

        gainMana(15);

        setSkillName(1);
        return rollDamage(2,8);
    }

    @Override
    public int skill2() {
        damage = rollDamage(4, 8);
        gainAdvantage();

        setSkillName(2);
        return damage;
    }

    @Override
    public int skill3() {
        damage = rollDamage(2, 12);

        increaseAC(4);
        setSkillName(3);
        return damage;
    }

    @Override
    public void loseHP(int damage){
        clearAC();

        int hp = getHp();
        Math.max(0, hp-=damage);
    }

}
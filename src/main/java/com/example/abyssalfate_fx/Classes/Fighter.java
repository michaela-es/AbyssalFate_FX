package com.example.abyssalfate_fx.Classes;

public class Fighter extends CharacterClass {

    public Fighter() {
        super(120, 80, 19, 14, 8);

        costSkill2 = 20;
        costSkill3 = 25;

        nameSkill1 = "Action Surge";
        nameSkill2 = "Battle Maneuver";
        nameSkill3= "Defensive Flourish";

        descSkill1 = "Deals 2d8 damage. Restores 15 mana.";
        descSkill2 = "Deals 4d8 damage to opponent. Grants advantage. " + costSkill2 + " mana cost.";
        descSkill3 = "Deals 2d12 damage. Gains +4 AC. " + costSkill3 +" mana cost";

        backstory = "A devout servant of the righteous path, the Paladin strikes down those who aim to harm the innocent. But beneath their armor and bravado lies a shivering child, whose salvation came as a hero dressed much like they are now. Armed with a gleaming golden axe, they mow down their foes and radiate hope.";

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

        loseMana(costSkill2);
        setSkillName(2);
        return damage;
    }

    @Override
    public int skill3() {
        damage = rollDamage(2, 12);

        increaseAC(4);
        setSkillName(3);
        loseMana(costSkill3);
        return damage;
    }

    @Override
    public void loseHP(int damage){
        clearAC();

        int hp = getHp();
        Math.max(0, hp-=damage);
    }

}
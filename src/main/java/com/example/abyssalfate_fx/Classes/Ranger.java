package com.example.abyssalfate_fx.Classes;

public class Ranger extends CharacterClass {

    int huntersMark;

    public Ranger() {
        super(70, 80, 16, 14, 8);

        costSkill2 = 30;
        costSkill3 = 45;

        nameSkill1 = "Arcane Shot";
        nameSkill2 = "Tempest's Arrow";
        nameSkill3= "Nature's Reckoning";

        descSkill1 = "Deals 1d8 damage to opponent. Restores 12 - 17 mana. Increases Hunter’s Mark by 3d6.";
        descSkill2 = "Deals 2d12 + Hunter’s Mark damage. " + costSkill2 + " mana cost.";
        descSkill3 = "Deals 4d12 + Hunter’s Mark damage." + costSkill3 +" mana cost";

        backstory = "The Ranger moves with speed and grace, emulating the animals they once explored the wilderness with. But after trifling with a powerful enemy, they lost control and destroyed their loved ones. As time marches forward, their skill in archery expands, but their path only" +
                " grows lonelier.";
    }

    public void clearMark (){
        huntersMark = 0;
    }
    @Override
    public int skill1() {

        gainMana(roller.nextInt(5)+12);
        huntersMark += rollDamage(3,6);

        setSkillName(1);
        return rollDamage(1,8);
    }

    @Override
    public int skill2() {
        damage = rollDamage(2, 12);
        damage += huntersMark;

        clearMark();

        setSkillName(2);
        return damage;
    }

    @Override
    public int skill3() {
        damage = rollDamage(4, 12);
        damage += huntersMark;

        clearMark();
        setSkillName(3);
        return damage;
    }
}
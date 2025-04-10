package com.example.abyssalfate_fx.Classes;

public class Warlock extends CharacterClass {

    int Grimblight = 0;

    public Warlock() {
        super(80, 55, 14, 16, 6);

        costSkill2 = 10;
        costSkill3 = 18;

        nameSkill1 = "Siphoning Howl";
        nameSkill2 = "Eldritch Blast";
        nameSkill3= "Ruination Channeling";

        backstory="The Warlock was once imprisoned for their strange gifts, but after making a pact with a demon, their chains rusted away and they left their old life in cinders. If you hear the faint echoes of sorrowful wraiths, or see shadows shaped like daggers — they may be close by.";
        descSkill1 = "Deals 1d5 damage. Increases 'Grimblight' by 2. Regenerates 10-15 mana.";
        descSkill2 = "Deals 1d10 + additional damage based on 'Grimblight'. " + costSkill2 + " mana cost.";
        descSkill3 = "Increases Grimblight by 10-15. " + costSkill3 + " mana cost.";

    }

    @Override
    public int skill1() {
        Grimblight+=2;
        gainMana(roller.nextInt(5)+10);

        setSkillName(1);
        return rollDamage(1,5);
    }

    @Override
    public int skill2() {
        damage = rollDamage(1, 10);
        damage += Grimblight;

        setSkillName(2);
        return damage;
    }

    @Override
    public int skill3() {

        Grimblight += roller.nextInt(15)+10;

        setSkillName(3);
        return damage;
    }
}
package com.example.abyssalfate_fx.Classes;

public class Wizard extends CharacterClass {

    public Wizard() {
        super(65, 90, 12, 16, 11);

        costSkill2 = 45;
        costSkill3 = 30;

        nameSkill1 = "Esoteric Ritual";
        nameSkill2 = "Shadow Piercer";
        nameSkill3= "Ravaging Tide";

        descSkill1 = "Deals 1d4 damage. Restore 15 - 20 mana.";
        descSkill2 = "Deals 5d12 damage. Opponent saves against 16 to take half damage. " + costSkill2 + " mana cost.";
        descSkill3 = "Deals 8d6 damage. Opponent can save against 16 to take half damage. " + costSkill3 + " mana cost.";

        backstory = "Born with a natural affinity for arcane energies, the Wizard dedicated themself to the study of magic from a young age. They devoured ancient texts and practiced intricate spells, their power growing with each passing year. Their talent for manipulating the elements and weaving illusions quickly set them apart. However, a catastrophic magical event or a profound discovery led them to leave their former scholarly life behind. Now, they travel, seeking greater understanding of the arcane arts or perhaps using their potent abilities to aid others in need.";
    }

    @Override
    public int skill1() {
        gainMana(roller.nextInt(5)+15);

        setSkillName(1);
        return rollDamage(1,4);
    }

    @Override
    public int skill2() {
        damage = rollDamage(5, 12);

        damage = finalDamage(damage);

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
        damage = rollDamage(8, 6);

        damage = finalDamage(damage);

        setSkillName(3);
        return damage;
    }


}
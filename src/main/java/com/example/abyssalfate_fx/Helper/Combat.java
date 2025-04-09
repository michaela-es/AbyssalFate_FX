package com.example.abyssalfate_fx.Helper;

public class Combat {

    private int critMod = 1;

    public void checkCrit(int baseroll) {
        if (baseroll == 20)
            critMod = 2;
    }

    public int totalRoll(int baseroll, int hitBonus){
        return baseroll + hitBonus;
    }

    public boolean isHit(int totalRoll, int targetAC){
        if (totalRoll>=targetAC)
            return true;
        return false;
    }

    private int calcDamage(int damage){
        int totalDamage = damage * critMod;
        critMod = 1;
        return totalDamage;
    }


}

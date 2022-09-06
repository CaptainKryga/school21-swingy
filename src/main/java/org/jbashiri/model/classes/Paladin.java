package org.jbashiri.model.classes;

public class Paladin extends HeroClass {
    public Paladin() {
        className = getHeroClass("Paladin");
        maxHp = 150;
        maxAtk = 50;
        maxDef = 35;
        maxLuck = 15;

        hp = maxHp;
        atk = maxAtk;
        def = maxDef;
        luck = maxLuck;
    }
}

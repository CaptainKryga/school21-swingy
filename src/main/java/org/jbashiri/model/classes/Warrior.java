package org.jbashiri.model.classes;

public class Warrior extends HeroClass {
    public Warrior() {
        className = "Warrior";
        maxHp = 200;
        maxAtk = 50;
        maxDef = 40;
        maxLuck = 5;

        hp = maxHp;
        atk = maxAtk;
        def = maxDef;
        luck = maxLuck;
    }
}

package org.jbashiri.model.classes;

public class Paladin extends Class {
    public Paladin() {
        name = "Paladin";
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

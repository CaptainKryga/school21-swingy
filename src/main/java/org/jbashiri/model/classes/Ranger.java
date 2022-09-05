package org.jbashiri.model.classes;

public class Ranger extends Class {
    public Ranger() {
        name = "Ranger";
        maxHp = 100;
        maxAtk = 75;
        maxDef = 25;
        maxLuck = 20;

        hp = maxHp;
        atk = maxAtk;
        def = maxDef;
        luck = maxLuck;
    }
}

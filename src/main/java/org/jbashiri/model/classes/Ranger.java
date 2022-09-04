package org.jbashiri.model.classes;

public class Ranger extends Class {
    public Ranger() {
        name = "Ranger";
        maxHp = 100;
        maxAttack = 75;
        maxDefence = 25;
        maxLuck = 20;

        hp = maxHp;
        atk = maxAttack;
        def = maxDefence;
        luck = maxLuck;
    }
}

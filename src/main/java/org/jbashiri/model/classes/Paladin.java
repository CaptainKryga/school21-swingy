package org.jbashiri.model.classes;

public class Paladin extends Class {
    public Paladin() {
        name = "Paladin";
        maxHp = 150;
        maxAttack = 50;
        maxDefence = 35;
        maxLuck = 15;

        hp = maxHp;
        atk = maxAttack;
        def = maxDefence;
        luck = maxLuck;
    }
}

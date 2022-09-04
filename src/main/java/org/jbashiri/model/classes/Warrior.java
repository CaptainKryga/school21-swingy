package org.jbashiri.model.classes;

public class Warrior extends Class {
    public Warrior() {
        name = "Warrior";
        maxHp = 200;
        maxAttack = 50;
        maxDefence = 40;
        maxLuck = 5;

        hp = maxHp;
        atk = maxAttack;
        def = maxDefence;
        luck = maxLuck;
    }
}

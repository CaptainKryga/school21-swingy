package org.jbashiri.model.classes;

import org.jbashiri.utils.CustomLogger;

public class Mage extends Class {
    public Mage() {
        name = "Mage";
        maxHp = 50;
        maxAttack = 100;
        maxDefence = 10;
        maxLuck = 30;

        hp = maxHp;
        atk = maxAttack;
        def = maxDefence;
        luck = maxLuck;

        CustomLogger.singleton.printLog("Create class", 2);
    }
}

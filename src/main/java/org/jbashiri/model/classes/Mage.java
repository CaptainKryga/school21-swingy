package org.jbashiri.model.classes;

import org.jbashiri.utils.CustomLogger;

public class Mage extends HeroClass {
    public Mage() {
        className = getHeroClass("Mage");
        maxHp = 50;
        maxAtk = 100;
        maxDef = 10;
        maxLuck = 30;

        hp = maxHp;
        atk = maxAtk;
        def = maxDef;
        luck = maxLuck;

        CustomLogger.singleton.printLog("Create class", 2);
    }
}

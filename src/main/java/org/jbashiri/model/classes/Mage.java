package org.jbashiri.model.classes;

import org.jbashiri.utils.CustomLogger;

public class Mage extends Class {
    public Mage() {
        name = "Mage";
        hp = 50;
        attack = 0;
        defence = 10;
        luck = 20;

        CustomLogger.singleton.printLog("Create class", 2);
    }
}

package org.jbashiri.model.artifats;

import org.jbashiri.utils.CustomLogger;

import java.util.concurrent.ThreadLocalRandom;

import static org.jbashiri.utils.CustomMath.getRandom;

public class Artifact {
    protected String name;
    protected String type;
    protected int bonus;

    private int chanceDrop = 100;

    private String S = "QWRTPSDFGHKLZXCVBNMJ";
    private String G = "EYUIOA";

    public Artifact(int level, int luck) {
        int type = getRandom(0, 3);

        if (type == 0) {
            this.type = "Weapon";
            name = getNewName();
            bonus = getRandom(1, luck / 5 * level);
            CustomLogger.singleton.printLog(name + "\nbonusAttack = " + bonus, 2);
        } else if (type == 1) {
            this.type = "Chest";
            name = getNewName();
            bonus = getRandom(1, luck / 5 * level);
            CustomLogger.singleton.printLog(name + "\nbonusDefence = " + bonus, 2);
        } else if (type == 2) {
            this.type = "Head";
            name = getNewName();
            bonus = getRandom(1, luck / 5 * level);
            CustomLogger.singleton.printLog(name + "\nbonusHP = " + bonus, 2);
        }
    }

    public Artifact() {
        name = "-1";
        type = "-1";
    }

    private String getNewName() {
        StringBuilder result = new StringBuilder();
        int words = ThreadLocalRandom.current().nextInt(4, 10);
        while (words-- > 0) {
            if (words % 2 == 0) {
                result.append(S.charAt(ThreadLocalRandom.current().nextInt(0, S.length() - 1)));
            } else {
                result.append(G.charAt(ThreadLocalRandom.current().nextInt(0, G.length() - 1)));
            }
        }
        return result.toString();
    }

    public String getNameAndStats() {
        if (type.equals("Weapon"))
            return name + " bonus attack +" + bonus;
        else if (type.equals("Chest"))
            return name + " bonus defence +" + bonus;
        else
            return name + " bonus hp +" + bonus;
    }

    public int getBonus() {
        return bonus;
    }

    public String getType() {
        return type;
    }
    public String getName() {
        return name;
    }

    public int getChanceDrop() {
        return chanceDrop;
    }
}

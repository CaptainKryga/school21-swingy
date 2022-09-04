package org.jbashiri.model.artifats;

import org.jbashiri.utils.CustomLogger;

import java.util.concurrent.ThreadLocalRandom;

import static org.jbashiri.utils.CustomMath.getRandom;

public class Artifact {
    protected String name;
    protected String type;
    protected int bonusHp;
    protected int bonusAttack;
    protected int bonusDefence;

    private int chanceDrop = 100;

    private String S = "QWRTPSDFGHKLZXCVBNMJ";
    private String G = "EYUIOA";

    public Artifact(int level, int luck) {
        int type = getRandom(0, 3);

        if (type == 0) {
            this.type = "Weapon";
            name = getNewName();
            bonusHp = 0;
            bonusAttack = getRandom(luck * level, luck * level + 50);
            bonusDefence = 0;
            CustomLogger.singleton.printLog(name + "\nbonusAttack = " + bonusAttack, 2);
        } else if (type == 1) {
            this.type = "Chest";
            name = getNewName();
            bonusHp = 0;
            bonusAttack = 0;
            bonusDefence = getRandom(luck * level, luck * level + 50);
            CustomLogger.singleton.printLog(name + "\nbonusDefence = " + bonusDefence, 2);
        } else if (type == 2) {
            this.type = "Head";
            name = getNewName();
            bonusHp = getRandom(luck * level, luck * level + 50);
            bonusAttack = 0;
            bonusDefence = 0;
            CustomLogger.singleton.printLog(name + "\nbonusHP = " + bonusHp, 2);
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
            return name + " bonus attack +" + bonusAttack;
        else if (type.equals("Chest"))
            return name + " bonus defence +" + bonusDefence;
        else
            return name + " bonus hp +" + bonusHp;
    }

    public int getBonusAttack() {
        return bonusAttack;
    }

    public int getBonusHp() {
        return bonusHp;
    }

    public int getBonusDefence() {
        return bonusDefence;
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

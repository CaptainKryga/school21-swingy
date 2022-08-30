package org.jbashiri.model.artifats;

import org.jbashiri.utils.CustomLogger;
import org.jbashiri.utils.CustomMath;

import java.util.concurrent.ThreadLocalRandom;

import static org.jbashiri.utils.CustomMath.getRandom;

public class Artifact {
    protected String name;
    protected String type;
    protected int bonusHp;
    protected int bonusAttack;
    protected int bonusDefence;
    protected int bonusAgility;
    protected int bonusMagic;
    protected int bonusLuck; //max 100

    private String S = "QWRTPSDFGHKLZXCVBNMJ";
    private String G = "EYUIOA";

    public Artifact(int level, int luck) {
        int type = getRandom(0, 3);

        if (type == 0) {
            name = "Weapon: " + getName();
            CustomLogger.singleton.printLog(name, 2);
            bonusHp = 0;
            bonusAttack = getRandom(luck * level, luck * level + 50);
            bonusDefence = 0;
            bonusAgility = getRandom(level, luck * level);
            bonusMagic = getRandom(luck * level, luck * level + 50);
            bonusLuck = 0;
            CustomLogger.singleton.printLog("\nbonusHP = " + bonusHp + "\nbonusAttack = " + bonusAttack +
                    "\nbonusDefence = " + bonusDefence + "\nbonusMagic = " + bonusMagic + "\nbonusLuck = " +
                    bonusLuck, 3);
        } else if (type == 1) {
            name = "Chest: " + getName();
            CustomLogger.singleton.printLog(name, 2);
            bonusHp = getRandom(luck * level, luck * level + 50);
            bonusAttack = 0;
            bonusDefence = getRandom(luck * level, luck * level + 50);
            bonusAgility = getRandom(luck * level, luck * level + 50);
            bonusMagic = 0;
            bonusLuck = 0;
            CustomLogger.singleton.printLog("\nbonusHP = " + bonusHp + "\nbonusAttack = " + bonusAttack +
                    "\nbonusDefence = " + bonusDefence + "\nbonusMagic = " + bonusMagic + "\nbonusLuck = " +
                    bonusLuck, 3);
        } else if (type == 2) {
            name = "Head: " + getName();
            CustomLogger.singleton.printLog(name, 2);
            bonusHp = getRandom(level, luck + 50);
            bonusAttack = 0;
            bonusDefence = getRandom(level, luck + 50);
            bonusAgility = getRandom(level, luck + 50);
            bonusMagic = getRandom(level, luck + 50);
            bonusLuck = getRandom(level, 100);
            CustomLogger.singleton.printLog("\nbonusHP = " + bonusHp + "\nbonusAttack = " + bonusAttack +
                    "\nbonusDefence = " + bonusDefence + "\nbonusMagic = " + bonusMagic + "\nbonusLuck = " +
                    bonusLuck, 3);
        }
    }

    private String getName() {
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
}

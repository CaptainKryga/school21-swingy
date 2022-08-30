package org.jbashiri.model.artifats;

import org.jbashiri.utils.CustomLogger;
import org.jbashiri.utils.CustomRandom;

import java.util.concurrent.ThreadLocalRandom;

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
        int type = ThreadLocalRandom.current().nextInt(0, 3);

        if (type == 0) {
            name = "Weapon: " + getName();
            CustomLogger.singleton.printLog(name, 2);
            bonusHp = 0;
            bonusAttack = CustomRandom.getRandom(bonusLuck * level, bonusLuck * level + 50);
            bonusDefence = 0;
            bonusAgility = CustomRandom.getRandom(level, bonusLuck * level);
            bonusMagic = CustomRandom.getRandom(bonusLuck * level, bonusLuck * level + 50);
            bonusLuck = 0;
            CustomLogger.singleton.printLog("\nbonusHP = " + bonusHp + "\nbonusAttack = " + bonusAttack +
                    "\nbonusDefence = " + bonusDefence + "\nbonusMagic = " + bonusMagic + "\nbonusLuck = " +
                    bonusLuck, 3);
        } else if (type == 1) {
            name = "Chest: " + getName();
            CustomLogger.singleton.printLog(name, 2);
            bonusHp = CustomRandom.getRandom(bonusLuck * level, bonusLuck * level + 50);
            bonusAttack = 0;
            bonusDefence = CustomRandom.getRandom(bonusLuck * level, bonusLuck * level + 50);
            bonusAgility = CustomRandom.getRandom(bonusLuck * level, bonusLuck * level + 50);
            bonusMagic = 0;
            bonusLuck = 0;
            CustomLogger.singleton.printLog("\nbonusHP = " + bonusHp + "\nbonusAttack = " + bonusAttack +
                    "\nbonusDefence = " + bonusDefence + "\nbonusMagic = " + bonusMagic + "\nbonusLuck = " +
                    bonusLuck, 3);
        } else if (type == 2) {
            name = "Head: " + getName();
            CustomLogger.singleton.printLog(name, 2);
            bonusHp = CustomRandom.getRandom(level, bonusLuck + 50);
            bonusAttack = 0;
            bonusDefence = CustomRandom.getRandom(level, bonusLuck + 50);
            bonusAgility = CustomRandom.getRandom(level, bonusLuck + 50);
            bonusMagic = CustomRandom.getRandom(level, bonusLuck + 50);
            bonusLuck = CustomRandom.getRandom(level, 100);
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

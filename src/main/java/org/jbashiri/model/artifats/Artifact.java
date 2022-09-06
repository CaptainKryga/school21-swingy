package org.jbashiri.model.artifats;

import org.jbashiri.model.CustomEnums;
import org.jbashiri.utils.CustomLogger;

import java.util.concurrent.ThreadLocalRandom;

import static org.jbashiri.utils.CustomMath.getRandom;

public class Artifact {
    protected String artName;
    protected CustomEnums.TypeArt type;
    protected int bonus;

    private static int chanceDrop = 100;

    private String S = "QWRTPSDFGHKLZXCVBNMJ";
    private String G = "EYUIOA";

    public Artifact(int level, int luck) {
        int type = getRandom(0, 3);

        if (type == 0) {
            this.type = CustomEnums.TypeArt.Weapon;
            artName = getNewName();
            bonus = getRandom(1, luck / 5 * level);
            CustomLogger.singleton.printLog(artName + "\nbonusAttack = " + bonus, 2);
        } else if (type == 1) {
            this.type = CustomEnums.TypeArt.Chest;
            artName = getNewName();
            bonus = getRandom(1, luck / 5 * level);
            CustomLogger.singleton.printLog(artName + "\nbonusDefence = " + bonus, 2);
        } else if (type == 2) {
            this.type = CustomEnums.TypeArt.Head;
            artName = getNewName();
            bonus = getRandom(1, luck / 5 * level);
            CustomLogger.singleton.printLog(artName + "\nbonusHP = " + bonus, 2);
        }
    }

    public Artifact() {
        artName = "-1";
        type = CustomEnums.TypeArt.Null;
    }

    public Artifact(String artName, String type, int bonus) {
        this.artName = artName;
        this.type = CustomEnums.getTypeArt(type);
        this.bonus = bonus;
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
        if (type == CustomEnums.TypeArt.Weapon)
            return artName + " bonus attack +" + bonus;
        else if (type == CustomEnums.TypeArt.Chest)
            return artName + " bonus defence +" + bonus;
        else
            return artName + " bonus hp +" + bonus;
    }

    public int getBonus() {
        return bonus;
    }

    public CustomEnums.TypeArt getType() {
        return type;
    }
    public String getArtName() {
        return artName;
    }

    public int getChanceDrop() {
        return chanceDrop;
    }
}

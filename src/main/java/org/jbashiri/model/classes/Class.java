package org.jbashiri.model.classes;

import static org.jbashiri.utils.CustomStrings.getLengthNumber;
import static org.jbashiri.utils.CustomStrings.getSpaces;
import static org.jbashiri.utils.CustomMath.Abs;

public class Class {
    protected String name;
    public int hp;
    protected int maxHp;
    public int atk;
    protected int maxAtk;
    public int def;
    protected int maxDef;
    public int luck;
    protected int maxLuck; //max 75

    public Class loadClass(String clas) {
        return this;
    }

    public String getAllDefaultStats() {
        String result = "";
        result += name + getSpaces(Abs(10 - name.length()));
        result += hp + getSpaces(Abs(5 - getLengthNumber(hp)));
        result += atk + getSpaces(Abs(5 - getLengthNumber(atk)));
        result += def + getSpaces(Abs(5 - getLengthNumber(def)));
        result += luck + getSpaces(Abs(5 - getLengthNumber(luck)));
        return result;
    }

    public void levelUp(int level) {
        updateMaxHp(true, maxHp * level / 10);
        updateMaxAttack(maxAtk * level / 10);
        updateMaxDefence(maxDef * level / 10);
        updateMaxLuck(maxLuck * level / 10);
    }

    public String getName() {
        return name;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getMaxAtk() {
        return maxAtk;
    }

    public int getMaxDef() {
        return maxDef;
    }

    public int getMaxLuck() {
        return maxLuck;
    }

    public void updateHp(int add) {
        hp += add;
        if (hp > maxHp)
            hp = maxHp;
    }

    public void updateMaxHp(boolean isLevelUp, int add) {
        maxHp += add;
        hp += add;

        if (isLevelUp)
            hp = maxHp;
    }

    public void updateMaxDefence(int add) {
        maxDef += add;
        def = maxDef;
    }

    public void updateMaxAttack(int add) {
        maxAtk += add;
        atk = maxAtk;
    }

    public void updateMaxLuck(int add) {
        maxLuck += add;
        if (maxLuck >= 75) {
            maxLuck = 75;
        }
        luck = maxLuck;
    }
}

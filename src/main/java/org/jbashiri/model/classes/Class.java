package org.jbashiri.model.classes;

import static org.jbashiri.utils.CustomStrings.getLengthNumber;
import static org.jbashiri.utils.CustomStrings.getSpaces;
import static org.jbashiri.utils.CustomMath.Abs;

public class Class {
    protected String name;
    public int hp;
    protected int maxHp;
    public int atk;
    protected int maxAttack;
    public int def;
    protected int maxDefence;
    public int luck;
    protected int maxLuck;

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
        updateMaxAttack(maxAttack * level / 10);
        updateMaxDefence(maxDefence * level / 10);
        updateMaxLuck(maxLuck * level / 10);
    }

    public String getName() {
        return name;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public int getLuck() {
        return luck;
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
        maxDefence += add;
        def = maxDefence;
    }

    public void updateMaxAttack(int add) {
        maxAttack += add;
        atk = maxAttack;
    }

    public void updateMaxLuck(int add) {
        maxLuck += add;
        luck = maxLuck;
    }
}

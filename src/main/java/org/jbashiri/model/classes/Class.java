package org.jbashiri.model.classes;

import static org.jbashiri.utils.CustomStrings.getLengthNumber;
import static org.jbashiri.utils.CustomStrings.getSpaces;
import static org.jbashiri.utils.CustomMath.Abs;

public class Class {
    protected String name;
    protected int hp;
    protected int attack;
    protected int defence;
    protected int luck;

    public String getAllDefaultStats() {
        String result = "";
        result += name + getSpaces(Abs(10 - name.length()));
        result += hp + getSpaces(Abs(5 - getLengthNumber(hp)));
        result += attack + getSpaces(Abs(5 - getLengthNumber(attack)));
        result += defence + getSpaces(Abs(5 - getLengthNumber(defence)));
        result += luck + getSpaces(Abs(5 - getLengthNumber(luck)));
        return result;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getLuck() {
        return luck;
    }
}

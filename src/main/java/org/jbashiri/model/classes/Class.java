package org.jbashiri.model.classes;

import static org.jbashiri.utils.CustomStrings.getLengthNumber;
import static org.jbashiri.utils.CustomStrings.getSpaces;
import static org.jbashiri.utils.CustomMath.Abs;

public class Class {
    protected String name;
    protected int hp;
    protected int attack;
    protected int defence;
    protected int agility;
    protected int magic;
    protected int luck;//max 100

    public String getAllDefaultStats() {
        String result = "";
        result += name + getSpaces(Abs(10 - name.length()));
        result += hp + getSpaces(Abs(5 - getLengthNumber(hp)));
        result += attack + getSpaces(Abs(5 - getLengthNumber(attack)));
        result += defence + getSpaces(Abs(5 - getLengthNumber(defence)));
        result += agility + getSpaces(Abs(5 - getLengthNumber(agility)));
        result += magic + getSpaces(Abs(5 - getLengthNumber(magic)));
        result += luck + getSpaces(Abs(5 - getLengthNumber(luck)));
        return result;
    }
}
